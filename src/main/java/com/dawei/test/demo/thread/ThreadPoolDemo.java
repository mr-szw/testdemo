package com.dawei.test.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

	public static void main(String[] args) {

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(10));
		threadPoolExecutor.execute(() -> {
			System.out.println("ABc");
		});

		threadPoolExecutor.submit(() -> {
			System.out.println("ABc" + System.currentTimeMillis());
		});

	}

}
