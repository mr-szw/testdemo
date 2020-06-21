package com.dawei.test.demo.hystrix;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import com.dawei.test.demo.utils.GsonUtil;

import lombok.AllArgsConstructor;
import rx.Observable;

/**
 * @author Dawei 2019/1/8
 */
public class MainClientDemoTest {


	public static void main(String[] args) throws Exception {
		MainClientDemoTest mainClientDemoTest = new MainClientDemoTest();


		mainClientDemoTest.runDoWork();


	}

	private void runDoWork() throws Exception {
		MainClientDemoTest test = new MainClientDemoTest();


		HystrixTestDemo hystrixTestDemo = new HystrixTestDemo("2");


		Object object = new Object();

		for (int i = 0; i < 10; i++) {
			Thread thread1 = new Thread(() -> doWork(hystrixTestDemo));
			Thread thread2= new Thread(() -> doWork(hystrixTestDemo));
			thread1.start();
			thread2.start();
		}



		Thread.sleep(10L);
		//object.notifyAll();

	}

	private void doWork(HystrixTestDemo hystrixTestDemo) {
		String uuId = UUID.randomUUID().toString();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(() -> {
				try {
					//workCount.wait();
					hystrixTestDemo.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			thread.setName(uuId.substring(0, 2) + "-Thread#" + i);
			thread.start();
		}
	}

	@AllArgsConstructor
	public class WorkJob implements Runnable {
		Object workCount;
		HystrixTestDemo hystrixTestDemo;


		@Override
		public void run() {
//			synchronized (workCount) {
				try {
					//workCount.wait();
					hystrixTestDemo.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			//}
		}
	}


	private void runTestWorkQueue() {
		HystrixTestDemo hystrixTestDemo = new HystrixTestDemo("test");
		//1、阻塞执行
		List<String> execute = hystrixTestDemo.execute();
		System.out.println("execute: " + GsonUtil.toJson(execute));

		//2、异步执行
		Future<List<String>> listFuture = hystrixTestDemo.queue();
		try {
			if (listFuture.isDone()) {
				//2.1 get 时阻塞 等待返回
				List<String> list = listFuture.get();
				System.out.println("queue  执行完 " + GsonUtil.toJson(list));
			} else {
				boolean cancel = listFuture.cancel(true);

				System.out.println("queue 已经执行取消 执行完， 取消操作， cancel= " + cancel);

				if (listFuture.isCancelled()) {
					System.out.println("queue 已经取消完成");
				} else {
					System.out.println("取消失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//2.2 执行超时  -->  中断 返回
			System.out.println("中断指定返回值 ");
		}
		Observable<List<String>> observe = hystrixTestDemo.observe();

	}
}
