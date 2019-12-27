package com.dawei.test.demo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * @author Dawei  on 2018/3/25.
 */
public class DemoTestMain implements Cloneable {

	private ConcurrentHashMap<Thread, Long> concurrentHashMap = new ConcurrentHashMap<>();
	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

	public static void main(String[] args) {

	}

	@Test
	public void testMethod() throws Throwable {

		ThreadLocal<String> threadLocal = new ThreadLocal<>();

		List<Integer> integers = Lists.newArrayList(1, 2, 3, 4);

		Thread[] threads = new Thread[integers.size()];
		int i = 0;
		for (Integer integer : integers) {
			threads[i++] = new Thread(() -> {
				threadLocal.set(Thread.currentThread().getName());
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "---" + threadLocal.get());
			});
			threads[i - 1].start();
		}

		for (Thread t : threads) {
			t.join();
		}

	}

	private Queue<Thread> workerQueue = new LinkedList<>();

	private void testMethod2() {
		List<Future<String>> futureList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Future<String> submit = fixedThreadPool.submit(() -> testJob());
			futureList.add(submit);
		}

		if (!CollectionUtils.isEmpty(futureList)) {
			for (Future<String> stringFuture : futureList) {
				try {
					String result = stringFuture.get(1, TimeUnit.MILLISECONDS);
					System.out.println(result);
				} catch (Exception e) {
					Enumeration<Thread> threadEnumeration = concurrentHashMap.keys();
					while (threadEnumeration.hasMoreElements()) {
						Thread thread = threadEnumeration.nextElement();
						if (!thread.isDaemon()) {
							Long timeStart = concurrentHashMap.get(thread);
							if (System.currentTimeMillis() - timeStart > 10) {
								try {
									thread.interrupt();
									System.out.println("Try interrupt thread");
								} catch (Exception ex) {
									System.out.println("Try interrupt thread failed");
									ex.printStackTrace();
								}
							}
						}
					}
					e.printStackTrace();
				}
			}
		}

		//#1
		//#2
		//#3
		//#4 A
	}

	private String testJob() {
		String reuslt = "0";
		try {
			Thread currentThread = Thread.currentThread();
			System.out.println("Start " + currentThread.getName());
			concurrentHashMap.put(currentThread, System.currentTimeMillis());

			Random random = new Random(System.currentTimeMillis());
			reuslt = "" + random;
			int nextInt = random.nextInt(1000);
			try {
				Thread.sleep(nextInt);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(currentThread.getName());
			System.out.println("End " + currentThread.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return reuslt;
	}

	private void work() {
		Thread thread = Thread.currentThread();
		workerQueue.add(thread);

		thread.isDaemon();
	}



	 private ExecutorService fixedThreadPoolTest = Executors.newFixedThreadPool(10);

	private void testFuture() {

//		Map<String, Future<Object>> reusltMap = new HashMap<>();
//		Future<String> submit1 = fixedThreadPoolTest.submit(this::test1);
//		Future<Integer> submit2 = fixedThreadPoolTest.submit(this::test2);
//		Future<List<String>> submit3 = fixedThreadPoolTest.submit(this::test3);
//		reusltMap.put("submit1", submit1);
//		reusltMap.put("submit2", submit2);
//		reusltMap.put("submit3", submit3);

	}




	@Test
	public void testMethod12() throws Throwable {
//		String withPerf = DoWorkSomethingDemo.   ithPerf("", this::test1, 1);
//		Integer integer = DoWorkSomethingDemo.executeWithPerf("", test2, 1);
//		List<String> strings = DoWorkSomethingDemo.executeWithPerf("", this::test3, 1);
	}

	private String test1() {
		return "a";
	}

	private Integer test2() {
		return 1;
	}
	private List<String> test3() {
		return Lists.newArrayList("1", "4");
	}
}
