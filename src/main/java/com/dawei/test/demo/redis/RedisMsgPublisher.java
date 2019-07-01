package com.dawei.test.demo.redis;

/**
 * @author by Dawei on 2019/7/1.
 */
public interface RedisMsgPublisher {




    Long publishMessage(String messageBody, String ... channels);
}
