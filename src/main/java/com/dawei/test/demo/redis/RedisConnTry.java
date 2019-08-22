package com.dawei.test.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisConnTry {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnTry.class);

    private static final String REDIS_HOST = "";
    private static final Integer REDIS_PORT = 6379;
    private static final String[] REDIS_CHANNELS = {"Channels-1", "Channels-2"};


    public static void main(String[] args) {

        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        System.out.println("Jedis ping : " + jedis.ping());
        System.out.println("Jedis set : " + jedis.set("abc", "123"));
        System.out.println("Jedis get abc:" + jedis.get("abc"));
        System.out.println("Jedis del abc:" + jedis.del("abc"));

        toSubscriber();
        //toPublish();
    }

    private static void toPublish() {
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);

        RedisMsgPublisherImpl redisMsgPublisher = new RedisMsgPublisherImpl(jedis);
        while (true) {
            Long aLong = redisMsgPublisher.publishMessage("message" + UUID.randomUUID().toString(), REDIS_CHANNELS);
        }
    }

    private static void toSubscriber() {

        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        RedisMsgSubscriberImpl redisMsgSubscriber = new RedisMsgSubscriberImpl();

        jedis.subscribe(redisMsgSubscriber, REDIS_CHANNELS);

        new Runnable(){
            @Override
            public void run() {
                toPublish();
            }
        };

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
