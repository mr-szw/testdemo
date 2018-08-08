package com.dawei.test.demo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author by Dawei on 2018/8/8.
 */
public class ExecutorDemo {

    /*
     * 线程池 :
     *  固定数目的线程数：
     *      newFixedThreadPool(int nThreads)
     *  直接执行的一个线程 :
     *      void java.util.concurrent.Executor.execute(Runnable command);
     *  查看活动线程个数 :
     *      int java util.concurrent.ThreadExecutor.getActiveCount();
     *  结束所有线程 :
     *      void java.util.concurrent.ExecutorService.shutdown();
     *
     *
     *
     */

    public static void main(String[] args) {
        //创建 固定数目的线程池
        ExecutorService executorService =
                Executors.newFixedThreadPool(10);
        int i = 150;
        while (true) {
            if(i-- > 0) {
                executorService.execute(()-> {
                    System.out.println("this Thread--" + Thread.currentThread().getName());
                    System.out.println("Thread Num===" + ((ThreadPoolExecutor)executorService).getActiveCount());
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }else {
                if(((ThreadPoolExecutor)executorService).getActiveCount() == 0) {
                    executorService.shutdown();
                    System.out.println("任务结束");
                    break;
                }
            }

        }

    }





}
