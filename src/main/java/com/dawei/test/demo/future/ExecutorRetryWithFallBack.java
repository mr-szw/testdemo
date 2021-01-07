
package com.dawei.test.demo.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 尝试工具 带failback策略
 *
 * @author sinbad on 2020/4/2.
 */
public class ExecutorRetryWithFallBack<T> {
	private static final Logger logger = LoggerFactory.getLogger(ExecutorRetryWithFallBack.class);

	private static final int DEFAULT_MAX_RETRY_CNT = 3;
	private static final long WAIT_TIME_IN_MS_BETWEEN_RETRY = 100L;

	private ExecutorApi<T> executorApi;
	private OnErrorApi onError;
	private ExecutorApi<T> failBackApi;
	private int maxRetry;
	private long waitTimeBetweenRetry;

	public interface OnErrorApi {
		void onError(int retryIndex, Throwable th) throws Throwable;
	}

	public ExecutorRetryWithFallBack(ExecutorApi<T> executorApi, OnErrorApi onError,
			ExecutorApi<T> failBackApi, int maxRetry, long waitTimeBetweenRetry) {
		this.executorApi = executorApi;
		this.onError = onError;
		this.failBackApi = failBackApi;
		this.maxRetry = maxRetry;
		this.waitTimeBetweenRetry = waitTimeBetweenRetry;
	}

	public ExecutorRetryWithFallBack(ExecutorApi<T> executorApi, OnErrorApi onError,
			ExecutorApi<T> failBackApi) {
		this.executorApi = executorApi;
		this.onError = onError;
		this.failBackApi = failBackApi;
		this.maxRetry = DEFAULT_MAX_RETRY_CNT;
		this.waitTimeBetweenRetry = WAIT_TIME_IN_MS_BETWEEN_RETRY;
	}

	public ExecutorRetryWithFallBack(ExecutorApi<T> executorApi, ExecutorApi<T> failBackApi) {
		this.executorApi = executorApi;
		this.onError = null;
		this.failBackApi = failBackApi;
		this.maxRetry = DEFAULT_MAX_RETRY_CNT;
		this.waitTimeBetweenRetry = WAIT_TIME_IN_MS_BETWEEN_RETRY;
	}

	public ExecutorRetryWithFallBack(ExecutorApi<T> executorApi, int maxRetry) {
		this.executorApi = executorApi;
		this.onError = null;
		this.failBackApi = null;
		this.maxRetry = maxRetry;
		this.waitTimeBetweenRetry = WAIT_TIME_IN_MS_BETWEEN_RETRY;
	}

	public T execute(String name) throws Throwable {
		T result = null;
		for (int i = 0; i < maxRetry; i++) {
			try {
				result = executorApi.execute();
				if (i > 0) {
					logger.info("{} Succeeded after retry.", name);
				}
				return result;
			} catch (Throwable th) {
				// 吃掉异常
				// 处理错误
				callbackError(name, i, th);
				// 暂停一下 避免太快了
				waitForNextTry(i);
				// 多次尝试仍不生效
				result = finalErrorToFailBack(name, i, th);
			}
		}
		// 最后一次重试失败会走failback failback异常会抛出异常，否则用failback的结果
		return result;
	}

	/**
	 * 回调异常处理
	 */
	private void callbackError(String name, int i, Throwable err) {
		try {
			if (onError != null) {
				onError.onError(i, err);
			}
		} catch (Throwable th) {
			logger.error(name + " onError err: ", th);
		}
	}

	/**
	 * 尝试多次后仍失败 走failBack
	 */
	private T finalErrorToFailBack(String name, int i, Throwable err) throws Throwable {

		// 最后一次
		if (!tryContinue(i)) {
			if (failBackApi != null) {
				try {
					return failBackApi.execute();
				} catch (Throwable th) {
					logger.error(name + " finalErrorToFailBack err: ", th);
					throw th;
				}
			} else {
				throw err;
			}
		}
		// 有数据会返回 否则就抛了异常了 应该不会抛
		return null;
	}

	// 是否继续尝试的try
	private boolean tryContinue(int i) {
		return i < maxRetry - 1 ;
	}

	private void waitForNextTry(int i) {
		if (tryContinue(i)) {
			try {
				Thread.sleep(waitTimeBetweenRetry);
			} catch (InterruptedException sleepEx) {
				logger.error("sleep interrupted.", sleepEx);
				Thread.currentThread().interrupt(); // 恢复中断信号
			}
		}
	}
}
