package com.dawei.test.demo.threaddemo.change;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sinbad on 2020/5/5.
 */
public class ChangeDemo3 {


	public static void main(String[] args) {


		char[] aI = "12345678".toCharArray();
		char[] aC = "ABCDEFG".toCharArray();

		Lock lock = new ReentrantLock();
		Condition conditionT1 = lock.newCondition();
		Condition conditionT2 = lock.newCondition();

		new Thread(() -> {
			try {
				lock.lock();
				for (char c : aI) {
					System.out.print(c);

					conditionT2.signal();
					conditionT1.await();
				}
				//必须加 否则程序无法退出
				conditionT2.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}, "thread1").start();

		new Thread(() -> {
			try {
				lock.lock();
				for (char c : aC) {
					System.out.print(c);

					conditionT1.signal();
					conditionT2.await();
				}
				//必须加 否则程序无法退出
				conditionT1.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}, "thread2").start();
	}


}
