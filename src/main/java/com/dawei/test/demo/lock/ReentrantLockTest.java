package com.dawei.test.demo.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sinbad on 2021/3/13.
 */
public class ReentrantLockTest {


	public static void main(String[] args) {
		ReentrantLock reentrantLock = new ReentrantLock();
		reentrantLock.lock();
	}
}
