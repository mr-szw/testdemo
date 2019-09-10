package com.dawei.test.demo.threaddemo;


import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author Dawei 2019/3/13
 */
public class TestTryDemo {

    private volatile Integer countNum;

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public static void main(String[] args) {


        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        System.out.println(todayStart.getTime());

       // mainTest1();
    }



    public static void mainTest1() {
//异步线程 提交数据
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 15, 3,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), new AbortPolicy());


        for (int i=0; i< 1000; i++) {
            try {
                System.out.println(executorService.getActiveCount());
                executorService.submit(() -> {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException eInter) {
                        System.out.println(executorService.getActiveCount());
                        eInter.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        try {
            Thread.sleep(10000L);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        TestTryDemo testTryDemo = new TestTryDemo();
//        testTryDemo.setCountNum(0);
//
//
//        ThreadTest threadTest = new ThreadTest(testTryDemo);
//        ThreadTest2 threadTest2 = new ThreadTest2(testTryDemo);
//
//        new Thread(threadTest).start();
//        new Thread(threadTest2).start();

    }


}
