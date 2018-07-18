package com.dawei.test.demo;

/**
 * @author Dawei  on 2018/3/25.
 */
public class ThreadTestMain {

    public static void main(String[] args) {

        System.out.println("this Main method");
        System.out.println(execute());
        System.out.println("main method over");
    }

    public static String execute() {
        new ThreadT().start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "execute over";
    }
    static
    class ThreadT extends Thread {
        @Override
        public void run() {
            System.out.println("to do Run now ");
            try {
                Thread.sleep(1000);
                System.out.println("this run first");
                Thread.sleep(5000);
                System.out.println("this run Second ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
