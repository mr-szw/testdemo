package com.dawei.test.demo.down;

import java.util.concurrent.TimeUnit;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;

public class DownTest {


    static int threadCount = 200;

    public static void main(String[] args) throws Throwable {
        for (int i = 0; i < threadCount; i++) {
            Thread entryThread = new Thread(() -> {
                while (true) {
                    try {
                        new DowngradeUtil<>("getBoardLiveAnnounceList_TEST", DownTest::testMethod, DownTest::down, null).get();
                    } catch (Throwable throwable) {

                    }
                }
            });
            entryThread.setName("working-thread");
            entryThread.start();
        }


    }

    private static String testMethod() {
        System.out.println("testMethod");
        throw new RuntimeException("Test");
    }

    private static String down() {
        System.out.println("DOWN");
        return "Down";
    }
}

