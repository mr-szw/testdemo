package com.dawei.test.demo.hystrix;

import java.util.Collections;
import java.util.List;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * @author Dawei 2019/1/8
 * 豪猪 --- 服务熔断降级使用
 * https://blog.csdn.net/Ramboll/article/details/84876403
 */
public class HystrixTestDemo extends HystrixCommand<List<String>> {

	private static final String COMMAND_GROUP_KEY = "Demo_Group_Key";
	private static final String COMMAND_KEY = "Demo_Key";
	private static final int EXECUTION_TIMEOUT_MILLISECONDS = 100;
	private static final int THREAD_ACTIVITY_TIME_MILLISECONDS = 100;

	private String parameterName;


	//构造器 将执行所需的参数传进来
	public HystrixTestDemo(String parameterName) {

		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(COMMAND_GROUP_KEY))//提交分组
				.andCommandKey(HystrixCommandKey.Factory.asKey(COMMAND_KEY))
				.andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
						/*
						 * execution.isolation.thread.timeoutInMilliseconds
						 * 设置调用者等待命令执行的超时限制，超过此时间，HystrixCommand被标记为TIMEOUT，
						 * 并执行回退逻辑。
						 * 默认值：1000（毫秒）
						 */
						.withExecutionTimeoutInMilliseconds(EXECUTION_TIMEOUT_MILLISECONDS)
						/*
						 *execution.isolation.thread.interruptOnTimeout
						 * 设置HystrixCommand的执行是否在超时发生时被中断 使用线程隔离时，是否对命令执行超时的线程调用中断（Thread.interrupt()）操作。
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
						.withCircuitBreakerRequestVolumeThreshold(1)

						/*
						 * circuitBreaker.sleepWindowInMilliseconds
						 * 设置在断路器被打开，拒绝请求到再次尝试请求的时间间隔。
						 * 默认值：5000（毫秒）
						 */
						.withCircuitBreakerSleepWindowInMilliseconds(30)
						/*
						 * circuitBreaker.errorThresholdPercentage
						 * 设置打开断路器并启动回退逻辑的错误比率。
						 * （这个参数的效果受到circuitBreaker.requestVolumeThreshold和滚动时间窗口的时间长度影响）
						 * 默认值：50(%)
						 */
						.withCircuitBreakerErrorThresholdPercentage(1)
				)
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
						//设置核心线程池的大小
						.withKeepAliveTimeMinutes(THREAD_ACTIVITY_TIME_MILLISECONDS)  //线程配置保持连接时长
						.withCoreSize(1) //核心线程数
						.withMaximumSize(1)
				)

		);
		//业务处理参数
		this.parameterName = parameterName;
	}

	//业务执行
	@Override
	protected List<String> run() throws Exception {

		System.out.println("业务内容： " + parameterName + "  " + Thread.currentThread().getName());

		Thread.sleep(1000L);
		if (parameterName.contains("2")) {
			throw new InterruptedException("Error #");
		}

		return Collections.singletonList("parameterName " + parameterName);
	}


	//失败回调
	@Override
	protected List<String> getFallback() {
		System.out.println("失败回调 " + Thread.currentThread().getName());
		return Collections.singletonList("失败回调");
	}
}
