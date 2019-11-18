package com.dawei.test.demo.hystrix;

import com.netflix.hystrix.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Dawei 2019/1/8
 * 豪猪 --- 服务熔断降级使用
 */
public class HystrixTestDemo extends HystrixCommand<List<String>> {

    private static final String COMMAND_GROUP_KEY = "Demo_Group_Key";
    private static final String COMMAND_KEY = "Demo_Key";
    private static final int EXECUTION_TIMEOUT_MILLISECONDS = 5000;

    private String parameterName;


    //构造器 将执行所需的参数传进来
    public HystrixTestDemo(String parameterName) {

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(COMMAND_GROUP_KEY))//提交分组
<<<<<<< HEAD
                        .andCommandKey(HystrixCommandKey.Factory.asKey(COMMAND_KEY))
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                                .withKeepAliveTimeMinutes(EXECUTION_TIMEOUT_MILLISECONDS)  //线程配置保持连接时长
                                .withCoreSize(4) //核心线程数
                        )
                        .andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
                                .withExecutionTimeoutInMilliseconds(EXECUTION_TIMEOUT_MILLISECONDS)
                                .withCircuitBreakerEnabled(true)     //是否允许熔断，默认允许；
                                .withCircuitBreakerRequestVolumeThreshold(5)  //熔断器是否开启的阀值，也就是说单位时间超过了阀值请求数，熔断器才开 十秒内請求超过5个
                                .withCircuitBreakerErrorThresholdPercentage(50) //错误比例触发熔断： 失败50%的情况下
                                .withCircuitBreakerSleepWindowInMilliseconds(1000)  //熔断器默认工作时间，超过此时间会进入半开状态，即允许流量做尝试；熔断1000ms
                        )
=======
                .andCommandKey(HystrixCommandKey.Factory.asKey(COMMAND_KEY))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withKeepAliveTimeMinutes(EXECUTION_TIMEOUT_MILLISECONDS)  //线程配置保持连接时长
                        .withCoreSize(4) //核心线程数
                )
                .andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
                        .withExecutionTimeoutInMilliseconds(EXECUTION_TIMEOUT_MILLISECONDS)
                        .withCircuitBreakerRequestVolumeThreshold(5)  //十秒内請求超过5个
                        .withCircuitBreakerErrorThresholdPercentage(50) // 失败50%的情况下
                        .withCircuitBreakerSleepWindowInMilliseconds(1000)  //熔断1000ms
                )
>>>>>>> master
        );
        //业务处理参数
        this.parameterName = parameterName;
    }

    //业务执行
    @Override
    protected List<String> run() throws Exception {

        System.out.println("业务内容： " + parameterName);

        return Collections.singletonList("parameterName" + parameterName);
    }


    //失败回调
    @Override
    protected List<String> getFallback() {
        System.out.println("失败回调");
        return Collections.singletonList("失败回调");
    }
}
