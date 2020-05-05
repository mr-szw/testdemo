package com.dawei.test.demo.threaddemo.change;

/**
 * @author sinbad on 2020/5/5.
 */
public class ChangeDemo2 {


	public static void main(String[] args) {


		final Object object = new Object();

		char[] aI = "12345678".toCharArray();
		char[] aC = "ABCDEFG".toCharArray();


		new Thread(() -> {
			synchronized (object) {
				for (char c : aI) {
					System.out.print(c);

					//唤醒其他线程
					object.notify();
					try {
						//让出锁  持有线程锁的线程wait 并不是object wait
						object.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//必须加 否则程序无法退出
				object.notify();
			}
		}, "thread1").start();

		new Thread(() -> {
			synchronized (object) {
				for (char c : aC) {
					System.out.print(c);
					//唤醒其他线程
					object.notify();
					try {
						//让出锁
						object.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//必须加 否则程序无法退出
				object.notify();
			}
		}, "thread2").start();

	}


}
