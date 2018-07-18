package com.dawei.test.demo.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class RedisConnTry {

    public static void main(String[] args) {


  /*      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-redis.xml"});
        context.start();
        try{
            JedisSentinelPool jedisSentinelPool = (JedisSentinelPool) context.getBean("jedisSentinelPool");

            Jedis resource = jedisSentinelPool.getResource();
            String aaaaa = resource.set("aaaaa", "121312");

            System.out.println(aaaaa);

            String aaaaa1 = resource.get("aaaaa");
            System.out.println(aaaaa1);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }
}
