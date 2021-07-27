package com.dawei.test.demo.thread;

import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {


	private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 20, 1, TimeUnit.MILLISECONDS,
			new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().setNameFormat("FutureTaskThreadPool").build(),
			new ThreadPoolExecutor.AbortPolicy());

	private static ThreadPoolExecutor threadPoolExecutorMark = new ThreadPoolExecutor(2, 5, 10, TimeUnit.MINUTES,
			new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().setNameFormat("aaaaaa").build(),
			new ThreadPoolExecutor.AbortPolicy());


	public static void main(String[] args) throws InterruptedException, ExecutionException {

		threadPoolExecutorMark.execute(() -> {
			while (true) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				printThreadPoolStatus();
			}
		});

		List<Future<SettableFuture<String>>> settableFutureList = new ArrayList<>();

		for (int i = 0; i < 200; i++) {
			TaskJob taskJob = new TaskJob();
			try {
				Future<SettableFuture<String>> submit = threadPoolExecutor.submit(taskJob);
				settableFutureList.add(submit);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		System.out.println("put Finish");
		int getting = 0;
		for (Future<SettableFuture<String>> settableFuture : settableFutureList) {
			System.out.println("Get ..." + getting++);
			threadPoolExecutorMark.execute(()-> {
				System.out.println("du run");
				String s = null;
				try {
					s = settableFuture.get().get();
					System.out.println(s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				System.out.println("get result:");
			});



			getting--;
		}



		TimeUnit.HOURS.sleep(1);
	}


	static class TaskJob implements Callable<SettableFuture<String>> {
		@Override
		public SettableFuture<String> call() throws Exception {

			Thread.sleep(200);
			SettableFuture<String> settableFuture = SettableFuture.create();

			Random random = new Random();
			boolean b = random.nextBoolean();
			b = false;
			if (b) {

				settableFuture.set(UUID.randomUUID().toString());
			} else {
			}
			return settableFuture;
		}
	}


	public static void printThreadPoolStatus() {


		int activeCount = threadPoolExecutor.getActiveCount();
		long taskCount = threadPoolExecutor.getTaskCount();
		int queueSize = threadPoolExecutor.getQueue().size();
		long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();

		String msg = "队列大小:%s, 加入到线程池的任务数:%s, 正在执行任务数:%s, 执行完的任务数: %s";
		System.out.printf((msg) + "%n", queueSize, taskCount, activeCount, completedTaskCount);
	}
}
