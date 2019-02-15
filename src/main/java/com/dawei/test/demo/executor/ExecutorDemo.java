package com.dawei.test.demo.executor;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author by Dawei on 2018/8/8.
 *
 * https://www.cnblogs.com/trust-freedom/p/6693601.html
 */
public class ExecutorDemo {


    //https://blog.csdn.net/javazejian/article/details/77410889?locationNum=1&fps=1

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
     */

    /*
    *  线程池 构造方法解释
    * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,  TimeUnit unit,  BlockingQueue<Runnable> workQueue)
    *
    * 1、corePoolSize 核心线程数 线程池 中稳定存在的线程数 （当设置了allowCoreThreadTimeOut参数其也会失效）
    * 2、maximunPoolSize 最大线程数 线程池可创建的线程数
     *          线程从corePoolSize --> maximumPoolSize过程 当当大量请求过来的的时候 会阻塞一些等待任务 创建线程后处理任务
     *              当持续阻塞任务不执行其存在降级逻辑（rejectHandler ）
    * 3、keepAliveTime 保持存活时间 即当线程数 大于 corePoolSize时 ， 那些大于线程数的那些线程将会被关闭，关闭时间有keepAliveTime 参数来控制
    * 4、unit  时间单位 keepAliveTime的时间单位
    * 5、workQueue 仅保存由execute提交的任务队列 LinkedBlockingQueue 有数量限制   Synchor ArrayBlockingQueue
    * 6、threadFactory 新线程创建方式
    * 7、             失效策略
    *
    *
    *
    *
    *
    * */




    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建 固定数目的线程池
        ExecutorService executorService =
                Executors.newFixedThreadPool(10);
//        int i = 150;
//        while (true) {
//            if(i-- > 0) {
//                executorService.execute(()-> {
//                    System.out.println("this Thread--" + Thread.currentThread().getName());
//                    System.out.println("Thread Num===" + ((ThreadPoolExecutor)executorService).getActiveCount());
//                    try {
//                        Thread.sleep(20);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }else {
//                if(((ThreadPoolExecutor)executorService).getActiveCount() == 0) {
//                    executorService.shutdown();
//                    System.out.println("任务结束");
//                    break;
//                }
//            }
//
//        }


        Future<Boolean> future =   executorService.submit(() -> {

            int count = 1;
            while (true) {
               Thread.sleep(500);
               System.out.println(count++);
               if(count > 5) {
                   return true;
               }
            }
        });

        long timeOut = 2 * 1000L;
        Boolean aBoolean = null;
        try {
            aBoolean = future.get(timeOut, TimeUnit.MILLISECONDS);
            System.out.println("1 aboolean-- > " + aBoolean);
        } catch (TimeoutException e) {
            e.printStackTrace();
            boolean interrupted = Thread.interrupted();
            System.out.println("interrupted-- > " + interrupted);
        }

        if(future.get()) {
            System.out.println("Success");
        } else {
            System.out.println("Faild");
        }

    }









}
