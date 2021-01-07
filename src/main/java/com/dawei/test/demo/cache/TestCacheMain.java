package com.dawei.test.demo.cache;

/**
 * @author by Dawei on 2018/8/12.
 */
public class TestCacheMain {


    public static void main(String[] args) {

        try {

            GuavaCacheDemo instance = GuavaCacheDemo.getInstance();

            instance.putValue(null, null);


            System.out.println(instance.getLoadingCache().stats()); //获取统计信息

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
