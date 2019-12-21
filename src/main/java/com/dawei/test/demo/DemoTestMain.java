package com.dawei.test.demo;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Dawei  on 2018/3/25.
 */
public class DemoTestMain {

    private ConcurrentHashMap<Thread, Long> concurrentHashMap = new ConcurrentHashMap<>();
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    @Test
    public void testMethod() throws Throwable {

        List<Future<String>> futureList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Future<String> submit = fixedThreadPool.submit(() -> testJob());
            futureList.add(submit);
        }

        if (!CollectionUtils.isEmpty(futureList)) {
            for (Future<String> stringFuture : futureList) {
                try {
                    String result = stringFuture.get(1, TimeUnit.MILLISECONDS);
                    System.out.println(result);
                } catch (Exception e) {
                    Enumeration<Thread> threadEnumeration = concurrentHashMap.keys();
                    while (threadEnumeration.hasMoreElements()) {
                        Thread thread = threadEnumeration.nextElement();
                        if(!thread.isDaemon()) {
                            Long timeStart = concurrentHashMap.get(thread);
                            if (System.currentTimeMillis() - timeStart > 10) {
                                try {
                                    thread.interrupt();
                                    System.out.println("Try interrupt thread");
                                } catch (Exception ex) {
                                    System.out.println("Try interrupt thread failed");
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    e.printStackTrace();
                }
            }
        }



        //#1
        //#2
        //#3
        //#4 A
    }


    private String testJob() {
            String reuslt = "0";
        try {
            Thread currentThread = Thread.currentThread();
            System.out.println("Start " + currentThread.getName());
            concurrentHashMap.put(currentThread, System.currentTimeMillis());

            Random random = new Random(System.currentTimeMillis());
            reuslt = "" + random;
            int nextInt = random.nextInt(1000);
            try {
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(currentThread.getName());
            System.out.println("End " + currentThread.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reuslt;
    }
}
