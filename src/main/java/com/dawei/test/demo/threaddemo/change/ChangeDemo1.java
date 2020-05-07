package com.dawei.test.demo.threaddemo.change;

import java.util.concurrent.locks.LockSupport;

/**
 * @author sinbad on 2020/5/5.
 */
public class ChangeDemo1 {


	private static Thread thread1 = null;
	private static Thread thread2 = null;


	public static void main(String[] args) {
		char[] aI = "12345678".toCharArray();
		char[] aC = "ABCDEFG".toCharArray();


		thread1 = new Thread(() -> {
			for (char c : aI) {
				System.out.println(c);

				//叫醒2
				LockSupport.unpark(thread2);
				//睡自己 （当前）
				LockSupport.park();
			}
		}, "thread1");



		thread2 = new Thread(() -> {
			for (char c : aC) {
				System.out.println(c);

				LockSupport.unpark(thread1);
				LockSupport.park();
			}
		} , "thread2");


		thread1.start();
		thread2.start();
	}

















}
