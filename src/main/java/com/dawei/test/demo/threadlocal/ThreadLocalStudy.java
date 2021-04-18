package com.dawei.test.demo.threadlocal;

/**
 * @author sinbad on 2021/3/12.
 */
public class ThreadLocalStudy {


	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

	private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal();



	public static void main(String[] args) throws InterruptedException {

		Runnable r = new TT();

		inheritableThreadLocal.set("parent");
		System.out.println(inheritableThreadLocal.get());
		Thread.sleep(500);
		new Thread(r, "child").start();
		System.out.println(inheritableThreadLocal.get());
		System.out.println("exit");

		System.out.println("++++++++");
		//ThreadLocal.ThreadLocalMap WeakReference<ThreadLocal<?>>
		threadLocal.set("张三");

		System.out.println(threadLocal2.get());
		System.out.println("===");
		System.out.println(threadLocal.get());

	}




	private static class TT implements Runnable {
		@Override
		public void run() {
			System.out.println(inheritableThreadLocal.get());
			inheritableThreadLocal.set(Thread.currentThread().getName());
			System.out.println(inheritableThreadLocal.get());

			new Thread(() -> {
				System.out.println("newwwww");
				System.out.println(inheritableThreadLocal.get());
				inheritableThreadLocal.set(Thread.currentThread().getName());
				System.out.println(inheritableThreadLocal.get());
			}).start();
		}
	}
}
