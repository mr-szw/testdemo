package com.dawei.test.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * @author by Dawei on 2019/7/1.
 * Redis message Publish
 */
public class RedisMsgPublisherImpl implements RedisMsgPublisher{

    private Jedis jedis;


    public RedisMsgPublisherImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public Long publishMessage(String channel, String messageBody) {

        Long publish = jedis.publish(channel, messageBody);
        return publish;

    }
}
