package com.dawei.test.demo.redis;

import redis.clients.jedis.Jedis;

public class RedisConnTry {


    //private static final String REDIS_HOST = "39.105.201.88";
    private static final String REDIS_HOST = "";
    private static final Integer REDIS_PORT = 6379;


    public static void main(String[] args) {

        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        System.out.println("Jedis ping : " + jedis.ping());
        System.out.println("Jedis set : " + jedis.set("abc", "123"));
        System.out.println("Jedis get abc:" + jedis.get("abc"));
        System.out.println("Jedis del abc:" + jedis.del("abc"));
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-redis.xml"});
//        context.start();
//        try{
//            JedisSentinelPool jedisSentinelPool = (JedisSentinelPool) context.getBean("jedisSentinelPool");
//
//            Jedis resource = jedisSentinelPool.getResource();
//            String aaaaa = resource.set("aaaaa", "121312");
//
//            System.out.println(aaaaa);
//
//            String aaaaa1 = resource.get("aaaaa");
//            System.out.println(aaaaa1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
