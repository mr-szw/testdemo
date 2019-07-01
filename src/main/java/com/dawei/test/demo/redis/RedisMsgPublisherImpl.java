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

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public Long publishMessage(String messageBody, String ... channels) {
        Long resultPush = 0L;
        if(channels != null && channels.length > 0) {
            for (String channel : channels) {
                Long publish = jedis.publish(channel, messageBody);
                logger.info("Push message in channel={}, messageBody={}. result={}", channel, messageBody, publish);
                System.out.println("Push message in channel=" + channel+ "   messageBody= " + messageBody + " . result=" + publish);
                resultPush += publish;
            }
        }
        return resultPush;

    }
}
