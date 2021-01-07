package com.dawei.test.demo.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * 触发熔断器熔断
 * **熔断器开启：当熔断器处于开启的状态，将会触发fallback。
 */
public class HelloWorldBreakerCommand extends HystrixCommand<String> {

	private final int n;

	public HelloWorldBreakerCommand(int n) {
		//最小配置,指定groupKey
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("helloWorldBreakerGroup"))
				.andCommandPropertiesDefaults(
						HystrixCommandProperties.Setter()
								/*
								 * execution.isolation.thread.timeoutInMilliseconds
								 * 设置调用者等待命令执行的超时限制，超过此时间，HystrixCommand被标记为TIMEOUT，
								 * 并执行回退逻辑。
								 * 默认值：1000（毫秒）
								 */
								.withExecutionTimeoutInMilliseconds(1000)
								/*
								 * execution.isolation.thread.interruptOnTimeout
								 * 设置HystrixCommand的执行是否在超时发生时被中断。
								 * 使用线程隔离时，是否对命令执行超时的线程调用中断（Thread.interrupt()）操作。
								 * 默认值：true
								 */
								.withExecutionIsolationThreadInterruptOnTimeout(true)
								// ** 断路器（Circuit Breaker）属性配置 **
								/*
								 * circuitBreaker.enabled
								 * 设置断路器是否生效
								 * 默认值：true
								 */
								.withCircuitBreakerEnabled(true)
								/*
								 * circuitBreaker.requestVolumeThreshold
								 * 设置在一个滚动窗口中，打开断路器的最少请求数。比如：如果值是20，在一个窗口内（比如10秒），收到19个请求，即使这19个请求都失败了，断路器也不会打开。
								 * 默认值：20
								 */
								.withCircuitBreakerRequestVolumeThreshold(6)
								/*
								 * circuitBreaker.sleepWindowInMilliseconds
								 * 设置在断路器被打开，拒绝请求到再次尝试请求的时间间隔。
								 * 默认值：5000（毫秒）
								 */
								.withCircuitBreakerSleepWindowInMilliseconds(3000)
								/*
								 * circuitBreaker.errorThresholdPercentage
								 * 设置打开断路器并启动回退逻辑的错误比率。
								 * （这个参数的效果受到circuitBreaker.requestVolumeThreshold和滚动时间窗口的时间长度影响）
								 * 默认值：50(%)
								 */
								.withCircuitBreakerErrorThresholdPercentage(50)
				)
				//设置核心线程池的大小
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(20))
		);
		this.n = n;
	}

	@Override
	protected String run() throws Exception {
		System.out.println(n + "-Command A--> " + Thread.currentThread().getName());
		if (n > 0) {
			// 设置超时
			Thread.sleep(1000);
		}
		// 设置withExecutionIsolationThreadInterruptOnTimeout(true)后面代码将中断执行
		System.out.println(n + "-Command B--> " + Thread.currentThread().getName());
		return n + "执行成功";
	}

	@Override
	protected String getFallback() {
		System.out.println("熔断降级! C--> " + Thread.currentThread().getName());
		return n + "执行失败";
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new HelloWorldBreakerCommand(10).execute());
	}
}