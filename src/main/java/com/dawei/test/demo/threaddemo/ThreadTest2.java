package com.dawei.test.demo.threaddemo;

/**
 * Created by Dawei on 2018/3/25.
 */
public class ThreadTest2 {


    public static boolean flag = false;


    public static class ThreadOne implements Runnable {

        @Override
        public void run() {
            if (flag) {
                System.out.println("111");
                flag = !flag;
            }
        }
    }

    public static class ThreadTwo implements Runnable {
        @Override
        public void run() {
            if (!flag) {
                System.out.println("2222");
                flag = !flag;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ThreadOne());
        Thread thread2 = new Thread(new ThreadTwo());
        thread1.start();
        thread2.start();

        Thread.sleep(1000);
        thread1.interrupt();
        thread2.interrupt();
    }
}
