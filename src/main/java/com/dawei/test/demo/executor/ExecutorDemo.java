package com.dawei.test.demo.executor;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author by Dawei on 2018/8/8.
 *
 * https://www.cnblogs.com/trust-freedom/p/6693601.html
 */
public class ExecutorDemo {


    public String working1() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "working1 Failed";
        }
        System.out.println("working1" + Thread.currentThread().getName());
        return "working1";
    }

    public String working2() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "working2 Failed";
        }
        System.out.println("working2" + Thread.currentThread().getName());
        return "working2";
    }

    public String working3() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "working3 Failed";
        }
        System.out.println("working3" + Thread.currentThread().getName());
        return "working3";
    }

    public String working4() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "working4 Failed";
        }
        System.out.println("working4" + Thread.currentThread().getName());
        return "working4";
    }

    public String working5() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "working5 Failed";
        }
        System.out.println("working5" + Thread.currentThread().getName());
        return "working5";
    }

    public String working6() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "working6 Failed";
        }
        System.out.println("working6" + Thread.currentThread().getName());
        return "working6";
    }










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

        GuavaExecutor guavaExecutor = new GuavaExecutor();
        try {
            guavaExecutor.merageExecutorResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExecutorService executorServicePool = null;
        try {
            executorServicePool =
                new ThreadPoolExecutor(
                    5,
                    6,
                    2L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(20),
                    Executors.defaultThreadFactory(),
                    new AbortPolicy()
                );



            for(int i= 0; i< 50; i++) {
                executorServicePool.submit(() -> {
                    System.out.println("i = ");
                    try {
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
            Thread.sleep(10000);
        } finally {
            if(executorServicePool != null) {
                executorServicePool.shutdown();
            }
        }

        System.out.println("#########################");

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        Long startTime = System.currentTimeMillis();
        List<String> resultList =  new ArrayList<>();







        ExecutorDemo executorDemo = new ExecutorDemo();

        List<Future<String>> futureList = new ArrayList<>();
        futureList.add(fixedThreadPool.submit(() -> executorDemo.working1()));
        futureList.add(fixedThreadPool.submit(() -> executorDemo.working2()));
        futureList.add(fixedThreadPool.submit(() -> executorDemo.working3()));
        futureList.add(fixedThreadPool.submit(() -> executorDemo.working4()));
        futureList.add(fixedThreadPool.submit(() -> executorDemo.working5()));
        futureList.add(fixedThreadPool.submit(() -> executorDemo.working6()));
        for(Future<String> future : futureList) {
            resultList.add(future.get());
        }
//        resultList.add(executorDemo.working1());
//        resultList.add(executorDemo.working2());
//        resultList.add(executorDemo.working3());
//        resultList.add(executorDemo.working4());
//        resultList.add(executorDemo.working5());
//        resultList.add(executorDemo.working6());



        Long endTime = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(resultList));
        System.out.println(endTime - startTime);

        fixedThreadPool.shutdownNow();
    }



//   异步           同步
// 2199040038   5173509886
//12002157877   15003028957

}
