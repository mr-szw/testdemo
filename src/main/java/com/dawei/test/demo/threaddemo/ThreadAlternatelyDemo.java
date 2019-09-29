package com.dawei.test.demo.threaddemo;

/**
 * @author by Dawei on 2019/9/29.
 *
 *
 * 线程交替打印
 *
 */
public class ThreadAlternatelyDemo {



    //用于做锁的对象
    private static final Object object = new Object();
    //
    private static boolean flag = false;


    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {

            while (true) {
                synchronized (object) {
                    if (flag) {
                        doPrintInfo();
                    }
                }
            }
        });


        Thread thread2 = new Thread(() -> {

            while (true) {
                synchronized (object) {
                    if (!flag) {
                        doPrintInfo();
                    }
                }
            }
        });

        //让线程1 先执行
        thread1.setPriority(Thread.MAX_PRIORITY);
        System.out.println(thread1.getName());

        //提交执行
        thread1.start();
        thread2.start();

        System.out.println("Over");


    }

    private static void  doPrintInfo() {
        String name = Thread.currentThread().getName();
        System.out.println("CurrentThread name = " + name);
        flag = !flag;
        try {

            System.out.println(name + "   do notify ");
            object.notifyAll();

            System.out.println(name + "   do Wait");
            object.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }












}
