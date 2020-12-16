package com.dawei.test.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

	public static void main(String[] args) {

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.MINUTES,
				new ArrayBlockingQueue<>(10));


		for (int i = 0; i < 20; i++) {
			TaskJob taskJob = new TaskJob();
			threadPoolExecutor.submit(taskJob);
		}



		threadPoolExecutor.execute(() -> {
			System.out.println("ABc");
		});

		threadPoolExecutor.submit(() -> {
			System.out.println("ABc" + System.currentTimeMillis());
		});

	}



	static class TaskJob implements Runnable{


		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " start!");
			try {
				Thread.sleep(TimeUnit.MINUTES.toMillis(20));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " end!");
		}
	}



}
