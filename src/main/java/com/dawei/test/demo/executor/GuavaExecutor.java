package com.dawei.test.demo.executor;

import com.google.common.util.concurrent.*;
import java.io.Serializable;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author by Dawei on 2018/8/13.
 *
 * google 对Java的线程进行封装
 * 提供了对执行任务的Callable 做监听回调
 */
public class GuavaExecutor {

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    private ExecutorService singeleThresdPool =  Executors.newSingleThreadExecutor();
    private ExecutorService cacheThreadPool =  Executors.newCachedThreadPool();



    /* 原始的 可获取执行结果的JDK 线程池 */
    public void simpleJDKExecuterDemo() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        /*  返回 */
        Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Boolean call() throws Exception {
                return true;
            }
        });

        /*
         * 获取执行结果
         *      by 阻塞 等待 线程执行
         */
        Boolean aBoolean = future.get();


        CountDownLatch latch = new CountDownLatch(2);
    }



    /* 做监听的 线程池 线程执行完成后 主动触发响应事件 */
    public void listenerExecuterDemo() throws ExecutionException, InterruptedException {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Boolean> listenableFuture = listeningExecutorService.submit(new Callable<Boolean>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Boolean call() throws Exception {

                Thread.sleep(300);
                return true;
            }
        });

        /* 添加回调机制返回结果 */
        Futures.addCallback(listenableFuture, new DoFutureCallBackImpl(), listeningExecutorService);
    }


    /*  将原本的JDK多线程 转化为 可见听的多线程 */
    public void changeToListenerExecuterDemo() throws ExecutionException, InterruptedException {

        //创建 固定数目的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Boolean call() throws Exception {

                Thread.sleep(300);
                return true;
            }
        });
        /* 阻塞 等待 线程执行 */
        /*Boolean resultGet = future.get();*/
        /* 使用Jdk Future 是配置器 将 原本的JDK 线程池 转化为 可监听的线程池 */
        ListenableFuture<Boolean> listenableFuture = JdkFutureAdapters.listenInPoolThread(future);

        /* 添加回调机制返回结果 */
        Futures.addCallback(listenableFuture, new DoFutureCallBackImpl(), executorService);

    }





    /* 提供合并线程的 方法线程执行池*/
    private ListeningExecutorService listeningExecutorServiceFather = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    /**
     *  合并 执行结果
     *
     *  对多个线程的执行结果 进行合并操作
     */
    public void merageExecutorResult() {

        /* 子任务线程-1 */
        ListenableFuture<Long> longListenableFuture1 = listeningExecutorServiceFather.submit(() -> {
            Thread.sleep(1000);
            System.out.println("This work -------------- > 1");
            return 1L;
        });

        /* 子任务线程-2 */
        ListenableFuture<Long> longListenableFuture2 = listeningExecutorServiceFather.submit(() -> {
            Thread.sleep(1000);
            System.out.println("This work -------------- > 2");
            //让任务二 发生异常
           throw new RuntimeException("Work Exception ----------- > work-2 ");
            //return 2L;
        });


        /* 合并 任外务返回监控
        *    当多个任务皆成功返回则返回 一个longListenableFuture 的List 对象
        *    allAsList 若存在一个失败的任务 则返回失败或取消
        *    successfulAsList 将成功的返回 不成功的返回null
        * */
        //ListenableFuture<List<Long>> mergeFuture = Futures.allAsList(longListenableFuture1, longListenableFuture2);

        ListenableFuture<List<Long>> mergeFuture = Futures.successfulAsList(longListenableFuture1, longListenableFuture2);

        
        /*
        * 
        * 对合并的任务进行继续处理
        * */
        final ListenableFuture<String> transformAsyncFuture = Futures.transformAsync(mergeFuture, new AsyncFunction<List<Long>, String>() {
            /**
             * Returns an output {@code Future} to use in place of the given {@code input}. The output {@code
             * Future} need not be {@linkplain Future#isDone done}, making {@code AsyncFunction} suitable for
             * asynchronous derivations.
             * <p>
             * <p>Throwing an exception from this method is equivalent to returning a failing {@code Future}.
             *
             * @param input
             */
            @Override
            public ListenableFuture<String> apply(@Nullable List<Long> input) throws Exception {
                if(!CollectionUtils.isEmpty(input)){
                    System.out.println(Arrays.toString(input.toArray()));
                }
                return Futures.immediateFuture("合并后的统一任务执行成功-----");
            }
        }, listeningExecutorServiceFather);



        /* 对结果合并后的任务执行的 做回调 处理*/
        Futures.addCallback(transformAsyncFuture, new DoFutureCallBackImpl(), listeningExecutorServiceFather);
    }













    /* 回调函数 实现体 */
    class DoFutureCallBackImpl implements FutureCallback<Object> {
        /**
         * Invoked with the result of the {@code Future} computation when it is successful.
         *
         * @param result 结果
         */
        @Override
        public void onSuccess(@Nullable Object result) {
            System.out.println("SuccessFully"  + result);
        }

        /**
         * Invoked when a {@code Future} computation fails or is canceled.
         * <p>
         * <p>If the future's {@link Future#get() get} method throws an {@link ExecutionException}, then
         * the cause is passed to this method. Any other thrown object is passed unaltered.
         *
         * @param t 异常
         */
        @Override
        public void onFailure(Throwable t) {
            System.out.println("onFailure  + t=" +  t);

        }
    }


}
