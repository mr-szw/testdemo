package com.dawei.test.demo.threaddemo;

import java.util.concurrent.TimeUnit;

/**
 * @author Dawei 2019/3/13
 */
public class TsetTryDemo {


    public static void main(String[] args) {
        mainTest1();
    }

    public static void mainTest1() {

        //开线程执行一个东西  设定时间是 【2S】 若两秒之后在没有结束 那么就中断他也就是相当于超时了
        //                      若两秒内结束啦 那么就是 【NO alive】 符合要求
        Thread thread = new Thread(() -> {
            try {
                int i = 0;
                while (i < 1001) {
                    System.out.println("+   i=" + i);
                    Thread.sleep(500);
                    i += 500;
                }
                System.out.println("Run over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            //他在这等你两秒  两秒之后 结束
            TimeUnit.MILLISECONDS.timedJoin(thread, 2000);
        } catch (InterruptedException e) {
            System.out.println("Trans time is so long failed, e=" + e);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (thread.isAlive()) {
            System.out.println("Alive");
            //结束掉
            thread.interrupt();
        } else {
            System.out.println(" No  Alive");
        }

        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
