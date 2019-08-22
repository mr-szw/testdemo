package com.dawei.test.demo.trycatch;


/**
 * @author by Dawei on 2019/8/21.
 */
public class TryCatchFinally {


    public static void main(String[] args) {
        try {
            System.out.println("Try");
            Thread thread = Thread.currentThread();
            String name = thread.getName();
            long id = thread.getId();
            System.out.println("Try = " + name + "  id=" + id);
            thread.interrupt();
            Runnable runnable = () -> {
                System.out.println("In Runnable");
                Thread currentThread = Thread.currentThread();
                String nameRunnable = currentThread.getName();
                long currentThreadId = currentThread.getId();
                System.out.println("Runnable = " + nameRunnable + " currentThreadId=" + currentThreadId);
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e) {
                    System.out.println("Runnable Sleep  Exception");
                    e.printStackTrace();
                }
                System.out.println("Do RuntimeException");
                ThreadGroup threadGroup = currentThread.getThreadGroup();
                threadGroup.interrupt();
                throw new RuntimeException("Running Exception");
            };
            runnable.run();
            System.out.println("Main interrupt");
            thread.interrupt();
        } catch (Exception e) {
            System.out.println("Catch");
            e.printStackTrace();

        } finally {
            System.out.println("Finally");
            Thread currentThread = Thread.currentThread();
            String nameRunnable = currentThread.getName();
            long currentThreadId = currentThread.getId();
            System.out.println("Finally thread name = " + nameRunnable + "  currentThreadId = " + currentThreadId);
        }
    }
}
