package com.dawei.test.demo.cache;

/**
 * @author by Dawei on 2018/8/12.
 */
public class TestCacheMain {


    public static void main(String[] args) {

        try {

            GuavaCacheDemo instance = GuavaCacheDemo.getInstance();
            for (int i = 0; i < 20; i++) {
                instance.putValue("key" + i, "value" + i);
                System.out.println("instance.getSize()  " + instance.getSize());
                System.out.println("instance.getValue()   " + instance.getValue("key" + i));
            }

            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
