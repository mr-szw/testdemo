package com.dawei.test.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author by Dawei on 2019/7/1.
 * Redis message Publish
 */
public class RedisMsgPublisherImpl implements RedisMsgPublisher{

    private static final Logger logger = LoggerFactory.getLogger(RedisMsgPublisherImpl.class);

    private Jedis jedis;
    public RedisMsgPublisherImpl(Jedis jedis) {
        this.jedis = jedis;
    }
    private Jedis getJedis() {
        return jedis;
    }
    private void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public Long publishMessage(String messageBody, String ... channels) {
        Long resultPush = 0L;
        if(channels != null && channels.length > 0) {
            for (String channel : channels) {
                Long publish = jedis.publish(channel, messageBody);
                logger.info("Push message in channel={}, messageBody={}. result={}", channel, messageBody, publish);
                resultPush += publish;
            }
        }
        return resultPush;

    }
}
