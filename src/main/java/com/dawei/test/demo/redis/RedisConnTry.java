package com.dawei.test.demo.redis;



import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisConnTry {


    private static final String REDIS_HOST = "";
    private static final Integer REDIS_PORT = 6379;
    private static final String[] REDIS_CHANNELS = {"Channels-1", "Channels-2"};


    public static void main(String[] args) {

        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        System.out.println("Jedis ping : " + jedis.ping());
        System.out.println("Jedis set : " + jedis.set("abc", "123"));
        System.out.println("Jedis get abc:" + jedis.get("abc"));
        System.out.println("Jedis del abc:" + jedis.del("abc"));

        //toSubscriber();
        toPublish();
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


        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
