package com.dawei.test.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

/**
 * @author by Dawei on 2019/7/1.
 * 消息订阅
 */
public class RedisMsgSubscriberImpl extends JedisPubSub {

    private static final Logger logger = LoggerFactory.getLogger(RedisMsgSubscriberImpl.class);

    public RedisMsgSubscriberImpl() {
    }

    /**
     * 订阅了相关信息，接收到订阅就回去调用
     * @param channel 渠道|频道
     * @param messageBody 消息内容
     */
    @Override
    public void onMessage(String channel, String messageBody) {
        logger.info("Subscriber on message , channel={}, messageBody={}", channel, messageBody);
        super.onMessage(channel, messageBody);
    }

    /**
     * 订阅后会接受到调用
     * @param channel 渠道
     * @param subscribedChannels 订阅数目
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        super.onSubscribe(channel, subscribedChannels);
    }

    /**
     * 取消订阅
     * @param channels 渠道
     */
    @Override
    public void unsubscribe(String... channels) {
        super.unsubscribe(channels);
    }
}
