package com.dawei.test.demo.threaddemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Dawei 2019/3/13
 */
public class ThreadTest2 {


	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));
		Future<?> submit = threadPoolExecutor.submit(() -> System.out.println("Submit thread ={}" + Thread.currentThread()));

		threadPoolExecutor.execute(() -> System.out.println("Execute the thread =" + Thread.currentThread()));

	}




}
