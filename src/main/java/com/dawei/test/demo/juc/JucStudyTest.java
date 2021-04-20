package com.dawei.test.demo.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sinbad on 2021/3/13.
 */
public class JucStudyTest {

	public static void main(String[] args) throws InterruptedException {

		ReentrantLock reentrantLock = new ReentrantLock();

		reentrantLock.lock();
		reentrantLock.unlock();


		LockSupport.park();


		Semaphore semaphore = new Semaphore(2);
		semaphore.acquire(1);
		semaphore.release(1);


		CountDownLatch countDownLatch = new CountDownLatch(6);

		ExecutorService exec = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					countDownLatch.countDown();
					System.out
							.println("执行的线程" + Thread.currentThread().getName() + "循环第" + j + "次");
				}
			});
			exec.submit(t);
		}
		System.out.println("需要等待的所有线程结束");
		// 等待检查
		countDownLatch.await();
		System.out.println("All OK");
		Thread.sleep(10000L);
		// 关闭线程池
		exec.shutdown();




		CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
		cyclicBarrier.isBroken();
	}

}
