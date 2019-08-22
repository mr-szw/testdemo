package com.dawei.test.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

public class RedisMsgListener extends JedisPubSub {


    private static final Logger logger = LoggerFactory.getLogger(RedisMsgListener.class);


    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }


    @Override
    public void unsubscribe(String... channels) {
        super.unsubscribe(channels);
    }

    @Override
    public void subscribe(String... channels) {
        super.subscribe(channels);
    }


    @Override
    public void psubscribe(String... patterns) {
        super.psubscribe(patterns);
    }


    @Override
    public void punsubscribe() {
        super.punsubscribe();
    }

    /**
     * 监听到订阅频道接受到消息时的回调 (onMessage )
     * @param patterns
     */
    @Override
    public void punsubscribe(String... patterns) {
        super.punsubscribe(patterns);
    }

    /**
     * 监听到订阅频道接受到消息时的回调 (onMessage )
     * @param channel
     * @param message
     */
    @Override
    public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
    }

    /**
     * 监听到订阅模式接受到消息时的回调 (onPMessage)
     * @param pattern
     * @param channel
     * @param message
     */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        super.onPMessage(pattern, channel, message);
    }


    /**
     * 订阅频道时的回调( onSubscribe )
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        super.onSubscribe(channel, subscribedChannels);
    }

    /**
     * 取消订阅模式时的回调( onPUnsubscribe )
     * @param pattern
     * @param subscribedChannels
     */
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        super.onPUnsubscribe(pattern, subscribedChannels);
    }

    /**
     * 订阅频道模式时的回调 ( onPSubscribe )
     * @param pattern
     * @param subscribedChannels
     */
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        super.onPSubscribe(pattern, subscribedChannels);
    }


    /**
     * 取消订阅频道时的回调( onUnsubscribe )
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        super.onUnsubscribe(channel, subscribedChannels);
    }



}
