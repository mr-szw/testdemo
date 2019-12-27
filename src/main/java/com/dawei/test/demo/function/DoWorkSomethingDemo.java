package com.dawei.test.demo.function;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dawei on 2019/12/26
 */
public class DoWorkSomethingDemo {

	private static ExecutorService fixedThreadPoolTest = Executors.newFixedThreadPool(10);


	public static <T> T executeWithPerf(String action, FutureGetter<T> supplier, int timoout) throws Throwable {

		fixedThreadPoolTest.submit((Runnable) supplier.get());

		return supplier.get();


	}
}
