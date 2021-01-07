package com.dawei.test.demo.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * 资源异步调度器。可以用来异步调度一批对象的处理，或者集中控制某一类型的调用频率
 *
 * @param <T>
 *            需要处理对象的类型
 * @param <R>
 *            返回结果的类型
 */
public class ResourceScheduler<T, R> {
	// 在需要等待返回结果的时候，等待结果返回的最大时间
	private static final int MaxTimeToWaitForResult = 60000;
	// 在需要等待返回结果的时候，检查等待结果返回的最大时间
	private final static long MaxTimeToCheckResult = 60000;
	// 每XX毫秒去检查结果是否返回
	private final static long WaitTimeInMsPerCheckResult = 100;

	private Logger logger = LoggerFactory.getLogger(ResourceScheduler.class);

	// 使用这个调度器必须支持的方法
	public interface Method<T, R> {
		Object[] execute(int threadIndex, List<T> obj) throws Throwable;
	}

	// 调用出错的时候的错误处理
	public interface ErrorProcessor<T> {
		void process(T obj, Throwable ex);
	}

	// 每60秒调整一次线程
	private static final int TimeStampToManageThreads = 60000;
	// 当列表里面有多于这个值的数未处理的时候，主动增加线程数 (默认值，根据传入的最多Queue-size调整)
	private static int ThresholdToIncreaseThread = 1500;
	// 当列表里面有少于这个值的数未处理的时候，主动减少线程数 (默认值，根据传入的最多Queue-size调整)
	private static int ThresholdToDecreaseThread = 1000;
	// 每次批量处理的数据数量
	public static int BatchSizePerThread = 100;

	// 存储等待处理的对象
	private ArrayBlockingQueue<T> queue;
	// 缓存处理的结果
	private ConcurrentMap<Object, Object> resultMap = new ConcurrentHashMap<>();
	// 表示Null对象－用来存储在resultMap中表示处理的结果就是null （这样找不到的时候表示未处理）
	private final static Object NullObject = new Object();

	// 调度器名字
	private String name;
	// 最大线程数
	private int maxThreads;
	// 处理对象的方法
	private Method<T, R> method;
	// 是否存储结果
	private boolean storeResult;
	// 出错处理的方法
	private ErrorProcessor<T> errorProcessor;
	// 每秒最大可以处理的数量（避免对目标资源造成瞬间峰值）
	private long maxNumPerSec;

	// 上次处理的时间
	private long lastTimestamp = -1;
	// 限流的计数器（计算已经处理的对象数量）
	private long curThrottleTotal = 0;

	// 每次增减线程的时候的数量，避免一次增加／减少太多线程
	private int stepToIncreaseThreads;
	// 最小的活跃线程数
	private int minActiveThreads;
	// 当前活跃的线程数
	private int curActiveThreadNum;
	// 上传调整线程的时间
	private long lastAdjustThreadTimestamp;
	// 本次调整线程区间最大的Queue size
	private long lastMaxQueueSize;
	// 本次调整线程区间最小的Queue size
	private long lastMinQueueSize;
	// 线程终止自身的时候的锁－每次线程跑完后会检查一下自己的index是否比maxThread大，如果是，就直接退出
	private Object activeMaxThreadsLock = new Object();

	// 如果当前Queue里面的数据<这个值，表示当前基本没有数据需要处理的，这时候会等待一段时间，直到Queue>sizeToConsiderQueueExitEmpty或者超过了最大等待时间
	// 可以减少每次批处理都是处理很小量的数据，达不到批处理的效果
	private int sizeToConsiderQueueAlmostEmpty = 100;
	private int sizeToConsiderQueueExitEmpty = 1000;

	// 处理输入值的线程 - 可能有好多个
	private class ResourceRunningThread extends Thread {
		// 如果Queue基本为空的时候，每次循环等待的时间
		private static final int TimeToWaitIfQueueIsAlmostEmpty = 200;
		// 被限流后每个线程最大的等待时间
		private static final int MaxWaitTimeAfterThrottledPerThread = 10000;
		// 如果当前的Queue为空，等待500ms
		private static final int TimeInMSBeforeNextTryIfEmpty = 500;
		// 当前线程的序号
		private int index;
		// 当前线程需要处理的对象
		private List<T> curObjects = new ArrayList<>();

		public ResourceRunningThread(int index) {
			this.index = index;
		}


		@Override
		public void run() {
			while (true) {
				// 限流或者设置相关的数据
				if (maxNumPerSec > 0) {
					if (lastTimestamp == -1) {
						lastTimestamp = System.currentTimeMillis();
					} else {
						throttleIfOver();
					}
				}

				// 由第０个线程来管理线程的增删 (肯定会留下第０个线程)
				if (this.index == 0) {
					manageThreadsIfNecessary();
				}

				int queueSize = queue.size();
//				PerfCounter.setHistogramValue(name + "-queue-size", queueSize);
//				PerfCounter.setHistogramValue(name + "-thread",
//						curActiveThreadNum);

				if (queueSize > 0) {
					if (logger.isDebugEnabled()) {
						logger.debug(name + " Thread {}, queue.size() = {}",
								index, queueSize);
					}
				}

				curObjects.clear();
				int drain = queue.drainTo(curObjects, BatchSizePerThread);

				if (drain == 0) {
					// 如果当前没有可以用的元素，休息一段时间
					waitWhenQueueIsEmpty(queueSize);
					continue;
				}

				// 计算当前限流的数
				if (maxNumPerSec > 0) {
					curThrottleTotal += drain;
				}

			//	PerfCounter.count(name + "-process-num", objSize);

				try {
					// 批处理对象
					Object[] results;
					long startTime = System.currentTimeMillis();
					try {
						results = method.execute(index, curObjects);
					} finally {
						long elapsedTime = System.currentTimeMillis()
								- startTime;
//						PerfCounter.setHistogramValue(name + "-execute-time",
//								elapsedTime);
					}

					if (results != null) {
						if (logger.isDebugEnabled()) {
							logger.debug(name + " Thread {}, get {} results",
									index, results.length);
						}
						for (int i = 0; i < results.length; i++) {
							Object result = results[i];
							T obj = curObjects.get(i);

							// 如果需要返回结果，暂存在resultMap中
							if (storeResult) {
								saveResultToMap(obj, result);
							}

							// 通知等待线程这个对象已经处理完了
							synchronized (obj) {
								obj.notify();
							}

							// 如果某个对象出错了，调用出错处理
//							if (!CommonUtil.isBatchResultSucceeded(result)) {
//								//PerfCounter.count(name + "-failed-objects", 1);
//								if (errorProcessor != null) {
//									errorProcessor.process(obj,
//											(Throwable) result);
//								}
//							}
						}
					} else {
						// 返回空对象－正常情况
						if (logger.isDebugEnabled()) {
							logger.debug(name + " Thread {}, get null results",
									index);
						}
					}
				} catch (Throwable ex) {
					logger.error(name + " Thread " + index
							+ " Fail to execute batch.", ex);
					for (int i = 0; i < curObjects.size(); i++) {
						T obj = curObjects.get(i);

						// 如果需要返回结果，存储exception
						if (storeResult) {
							saveResultToMap(obj, ex);
						}

						// 通知对象已经处理完了
						synchronized (obj) {
							obj.notify();
						}

						// 调用出错处理
						//PerfCounter.count(name + "-failed-objects", 1);
						System.out.println((name + "-failed-objects" + 1));
						if (errorProcessor != null) {
							errorProcessor.process(obj, ex);
						}
					}
				}

				// 如果当前的线程序号大于最大线程数（表示当前线程不再需要了，直接退出）
				if (index >= curActiveThreadNum) {
					synchronized (activeMaxThreadsLock) {
						if (index >= curActiveThreadNum) {
							logger.info(" " + name
									+ " Terminate self b/c index {} >= {}.",
									index, curActiveThreadNum);
							break;
						}
					}
				}

				// 如果需要等待Queue size到一定数量，等待
				if (sizeToConsiderQueueAlmostEmpty != -1
						&& curActiveThreadNum == 1
						&& queueSize < sizeToConsiderQueueAlmostEmpty) {
					waitUntilQueueNotAlmostEmpty(queueSize);
				}
			}
		}

		// 当Queue size为空的时候，等待一段时间，直到有数据进来或者超过最长时间
		private void waitWhenQueueIsEmpty(int queueSize) {
			if (sizeToConsiderQueueAlmostEmpty == -1) { // 如果没配最小Queue
														// size，直接sleep
				try {
					Thread.sleep(TimeInMSBeforeNextTryIfEmpty);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				waitUntilQueueNotAlmostEmpty(queueSize);
			}
		}

		// 等待直到Queue不是基本为空(如果有配置)
		private void waitUntilQueueNotAlmostEmpty(int queueSize) {
			try {
				int sleepCnt = 0;
				int firstQueueSize = queueSize;
				int originQueueSize;
				while (queueSize < sizeToConsiderQueueExitEmpty) {
					originQueueSize = queueSize;
					sleepCnt++;
					Thread.sleep(TimeToWaitIfQueueIsAlmostEmpty);
					queueSize = queue.size();
					// 如果没有新的元素进来，直接退出（防止这时候处于程序终止状态，而遗留部分元素未处理），或者已经等待了50次
					if ((originQueueSize != 0 && originQueueSize == queueSize)
							|| sleepCnt > 50) {
						if (logger.isDebugEnabled()) {
							logger.debug("No new element after sleep. Break in case it is in kill mode.");
						}
						break;
					}
				}

				// 打日志
				if (sleepCnt > 0 && queueSize > 0) {
					if (logger.isDebugEnabled()) {
						logger.debug(String
								.format(name
										+ " Total sleep %d ms, sleepCnt = %d, firstQueueSize = %d, queueSize = %d, sizeToConsiderQueueAlmostEmpty = %d, sizeToConsiderQueueExitEmpty=%d",
										TimeToWaitIfQueueIsAlmostEmpty
												* sleepCnt, sleepCnt,
										firstQueueSize, queueSize,
										sizeToConsiderQueueAlmostEmpty,
										sizeToConsiderQueueExitEmpty));
					}
				}
			} catch (InterruptedException e) {
				logger.error("Sleep failed.", e);
			}
		}

		//通过Sleep 限流
		private void throttleIfOver() {
			long totalWaitTime = 0;
			while (true) {
				// 计算现在是否需要等待
				long elapsedTime = CommonUtil.getCurrentTimestamp()
						- lastTimestamp;
				if ((elapsedTime * 1.0 / 1000) * maxNumPerSec < curThrottleTotal) {
					try {
						totalWaitTime += 200;
						Thread.sleep(200);
					} catch (InterruptedException e) {
						logger.error("sleep fails.", e);
					}
				} else {
					break;
				}

				// 如果等待时间超过最大等待时间－不管限流设置了
				if (totalWaitTime > MaxWaitTimeAfterThrottledPerThread) {
					break;
				}
			}

			// 每1200秒重置限流设置
			if (CommonUtil.getCurrentTimestamp() - lastTimestamp > 1200000) {

				curThrottleTotal = 0;
				lastTimestamp = CommonUtil.getCurrentTimestamp();
			}

			// 如果被限流了，打出日志
			if (totalWaitTime > 0) {
				logger.info(" " + name
						+ " Throttling. Wait {}ms", totalWaitTime);
			}
		}
	}

	// 设置批处理的数量
	public static void setBatchSizePerThread(int value) {
		BatchSizePerThread = value;
	}

	/*
	 * 创建调度器
	 * 
	 * @param name 调度器的名字，会出现日志和PerfCounter之中
	 * 
	 * @param maxQueueSize 队列可以存储的数
	 * 
	 * @param maxThreads 最大的线程数
	 * 
	 * @param method 调用方法
	 * 
	 * @param storeResult 是否要存储调用结果－如果不需要获得返回结果，设成false
	 * 
	 * @param sizeToConsiderQueueAlmostEmpty 对列里面多少个元素的时候先等一段时间再处理, -1表示不需要考虑这功能
	 * 
	 * @param sizeToConsiderQueueExitEmpty 和sizeToConsiderQueueAlmostEmpty结合使用,
	 * 不需要可以设置100
	 * 
	 * @param errorProcessor 错误处理方法
	 * 
	 * @param maxNumPerSec 每秒能容许处理的个数 - 防止瞬间峰值的出现
	 */
	public ResourceScheduler(String name, int maxQueueSize, int maxThreads,
			Method<T, R> method, boolean storeResult,
			int sizeToConsiderQueueAlmostEmpty,
			int sizeToConsiderQueueExitEmpty, ErrorProcessor<T> errorProcessor,
			int maxNumPerSec) {
		queue = new ArrayBlockingQueue<T>(maxQueueSize);
		ThresholdToIncreaseThread = (int) (maxQueueSize / 2);
		ThresholdToDecreaseThread = (int) (maxQueueSize / 5);
		this.name = name;
		this.maxThreads = maxThreads;
		this.method = method;
		this.storeResult = storeResult;
		this.sizeToConsiderQueueAlmostEmpty = sizeToConsiderQueueAlmostEmpty;
		this.sizeToConsiderQueueExitEmpty = sizeToConsiderQueueExitEmpty;
		this.errorProcessor = errorProcessor;
		this.maxNumPerSec = maxNumPerSec;

		int calActiveMaxThreads = adjustMaxThreadRelatedParams(maxThreads);
		this.lastAdjustThreadTimestamp = System.currentTimeMillis();
		this.lastMaxQueueSize = -1;
		this.lastMinQueueSize = -1;
		this.curActiveThreadNum = calActiveMaxThreads;
		for (int i = 0; i < calActiveMaxThreads; i++) {
			ResourceRunningThread thread = new ResourceRunningThread(i);
			thread.start();
		}
	}

	/*
	 * 计算最小线程数和每次增加减少的线程数
	 */
	private int adjustMaxThreadRelatedParams(int maxThreads) {
		int calculatedCurActiveThreadNum = maxThreads / 4;
		if (calculatedCurActiveThreadNum < 1) {
			calculatedCurActiveThreadNum = 1;
		}

		this.stepToIncreaseThreads = maxThreads / 5;
		if (this.stepToIncreaseThreads < 1) {
			this.stepToIncreaseThreads = 1;
		}
		this.minActiveThreads = calculatedCurActiveThreadNum;
		return calculatedCurActiveThreadNum;
	}

	// 调整线程数
	private void manageThreadsIfNecessary() {
		// 没到调整周期的时候，更新周期内最大的Queue size和最小的Queue size
		if (System.currentTimeMillis() - this.lastAdjustThreadTimestamp < TimeStampToManageThreads) {
			int curQueueSize = this.queue.size();
			if (this.lastMaxQueueSize == -1
					|| curQueueSize > this.lastMaxQueueSize) {
				this.lastMaxQueueSize = curQueueSize;
			}
			if (this.lastMinQueueSize == -1
					|| curQueueSize < this.lastMinQueueSize) {
				this.lastMinQueueSize = curQueueSize;
			}

			//PerfCounter.setHistogramValue(name + "-max-queue-size",
			//		lastMaxQueueSize);
			//PerfCounter.setHistogramValue(name + "-min-queue-size",
			//		lastMinQueueSize);
			return;
		}

		logger.debug(String
				.format(name
						+ " activeThreads=%d, maxThreads=%d, lastQueMax=%d, lastQueueMin=%d",
						this.curActiveThreadNum, this.maxThreads,
						this.lastMaxQueueSize, this.lastMinQueueSize));

		// 如果之前最小的Queue size也比阈值大，调高线程数
		// （说明当前线程不足于处理）
		if (this.lastMaxQueueSize > ThresholdToIncreaseThread) {
			if (this.curActiveThreadNum < maxThreads) {
				synchronized (activeMaxThreadsLock) {
					for (int i = 0; i < stepToIncreaseThreads; i++) {
						if (this.curActiveThreadNum < maxThreads) {
							logger.info(String
									.format("Create new thread %d, lastMaxQueueSize=%d > %d",
											this.curActiveThreadNum,
											this.lastMaxQueueSize,
											ThresholdToIncreaseThread));
							ResourceRunningThread thread = new ResourceRunningThread(
									this.curActiveThreadNum);
							this.curActiveThreadNum = this.curActiveThreadNum + 1;
							thread.start();
						}
					}
				}
			} else {
				logger.info(String
						.format("Need new thread, but already in max threads mode. activeMaxThreads = %d, maxThreads = %d, lastMaxQueueSize = %d > %d",
								this.curActiveThreadNum, this.maxThreads,
								this.lastMaxQueueSize,
								ThresholdToIncreaseThread));
			}
		} else if (this.lastMaxQueueSize < ThresholdToDecreaseThread) {
			// 如果之前最大的Queue size也比阈值小，调低线程数
			// (说明当前的线程富裕，不用那么多线程)
			if (this.curActiveThreadNum > minActiveThreads) {
				synchronized (activeMaxThreadsLock) {
					if (this.curActiveThreadNum > minActiveThreads) {
						logger.info(String
								.format("Decrease thread %d, lastMaxQueueSize = %d < %d",
										this.curActiveThreadNum,
										this.lastMaxQueueSize,
										ThresholdToDecreaseThread));
						this.curActiveThreadNum--;
					}
				}
			}
		}

		this.lastMaxQueueSize = -1;
		this.lastMinQueueSize = -1;
		this.lastAdjustThreadTimestamp = System.currentTimeMillis();
	}

	// 更新最大线程数－这个方法用来在进程运行中动态更新最大线程数
	// 比如通过数据库配置来调整最大线程数
	public void updateMaxThreads(int newMaxThreads) {
		if (newMaxThreads <= 0) {
			return;
		}

		this.maxThreads = newMaxThreads;
		adjustMaxThreadRelatedParams(this.maxThreads);

		if (this.curActiveThreadNum > this.maxThreads) {
			synchronized (this.activeMaxThreadsLock) {
				if (this.curActiveThreadNum > this.maxThreads) {
					this.curActiveThreadNum = this.maxThreads;
				}
			}
		}
	}

	// 更新每秒的最大值
	public void updateMaxNumPerSec(long value) {
		this.maxNumPerSec = value;
	}

	public int remainingCapacity() {
		return queue.remainingCapacity();
	}

	// 添加需要处理的对象，不等待结果返回（注意：这里队列满了会阻塞线程）
	public void put(T obj) {
		try {
			this.queue.put(obj);
		} catch (InterruptedException e) {
			logger.error(name + " put failed.", e);
		}
	}

	public void add(T obj) {
		try {
			this.queue.add(obj);
		} catch (Throwable e) {
			Gson gson = new Gson();
			logger.error(name + " add obj " + gson.toJson(obj) + " failed.", e);
		}
	}

	// 添加需要处理的对象，等待返回结果
	public Object execute(T obj) {
		try {
			this.queue.put(obj);
		} catch (InterruptedException e) {
			logger.error(name + " put failed.", e);
			return e;
		}

		// 等待对象被处理
		// 对象被处理后会调用obj.notify
		synchronized (obj) {
			try {
				obj.wait(MaxTimeToWaitForResult);
			} catch (InterruptedException e) {
				logger.error(name + " wait failed.", e);
			}
		}

		// 从resultMap里面取出结果
		return getResultFromMap(obj, MaxTimeToWaitForResult);
	}

	// 批量执行一个列表
	public Object[] execute(List<T> objs) {
		if (logger.isDebugEnabled()) {
			logger.debug(name + " execute {} objs", objs.size());
		}
		Object[] results = new Object[objs.size()];
		List<T> pendingResultObjects = new ArrayList<T>();
		List<Integer> pendingResultObjectsIndex = new ArrayList<Integer>();
		for (int i = 0; i < objs.size(); i++) {
			try {
				T obj = objs.get(i);
				this.queue.put(obj);
				pendingResultObjects.add(obj);
				pendingResultObjectsIndex.add(i);
			} catch (InterruptedException e) {
				logger.error(name + " put failed.", e);
				results[i] = e;
			}
		}
		if (pendingResultObjects.size() == 0) {
			return results;
		}

		// 直接等待最后一个对象被处理
		// 这里假设的是放进去的对象是先进先出
		Object objToWait = pendingResultObjects
				.get(pendingResultObjects.size() - 1);
		synchronized (objToWait) {
			try {
				objToWait.wait(MaxTimeToWaitForResult);
			} catch (InterruptedException e) {
				logger.error(name + " wait failed.", e);
			}
		}

		// 获取所有的结果
		long maxWaitTime = MaxTimeToCheckResult;
		for (int i = 0; i < pendingResultObjects.size(); i++) {
			T obj = pendingResultObjects.get(i);
			int curWaitTime = waitUntilObjectProcessed(obj, maxWaitTime);
			maxWaitTime -= curWaitTime;

			Object result = getResultFromMap(obj, curWaitTime);
			results[pendingResultObjectsIndex.get(i)] = result;
		}

		return results;
	}

	// 等待知道obj被处理或者等待maxWaitTime时间
	private int waitUntilObjectProcessed(Object obj, long maxWaitTime) {
		int curWaitTime = 0;
		long curSleepTime = WaitTimeInMsPerCheckResult;
		while (!resultMap.containsKey(obj)) {
			if (curWaitTime > maxWaitTime) {
				break;
			}

			try {
				curWaitTime += curSleepTime;
				if (logger.isDebugEnabled()) {
					logger.debug(name + " Result not ready, wait total {}ms.",
							curWaitTime);
				}
				Thread.sleep(curSleepTime);
			} catch (InterruptedException e) {
				logger.error(name + " sleep failed.", e);
			}
		}

		if (curWaitTime > 200) {
			logger.info(" " + name
					+ " {} Finish waiting. Total wait time {}ms", obj,
					curWaitTime);
		}

		return curWaitTime;
	}

	// 保存结果到resultMap
	private void saveResultToMap(T obj, Object result) {
		if (result == null) {
			resultMap.put(obj, NullObject);
		} else {
			resultMap.put(obj, result);
		}
	}

	// 从resultMap获取结果
	private Object getResultFromMap(T obj, long waitTime) {
		Object result;
		if (!resultMap.containsKey(obj)) {
			result = " Time out. the result doesn't return after waiting "
					+ waitTime + "ms.";
		} else {
			result = resultMap.remove(obj);
		}

		if (result == NullObject) {
			result = null;
		}

		return result;
	}
}


class CommonUtil{
	public static long getCurrentTimestamp() {
		return System.currentTimeMillis();
	}
}