package com.dawei.test.demo.threaddemo.change;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author sinbad on 2020/5/5.
 */
public class ChangeDemo4 {


	public static void main(String[] args) {


		char[] aI = "12345678".toCharArray();
		char[] aC = "ABCDEFGH".toCharArray();


		//阻塞的容量为0的对列
		TransferQueue<Character> transferQueue = new LinkedTransferQueue<>();


		new Thread(() -> {

			try {
				for (char c : aI) {
					//执行任务
					System.out.print(transferQueue.take());
					//存储数据 并阻塞线程
					transferQueue.transfer(c);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread-1").start();


		new Thread(() -> {
			try {
				for (char c : aC) {

					//存储数据并阻塞进程
					transferQueue.transfer(c);
					//获取数据
					System.out.print(transferQueue.take());


				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread2").start();

	}


}
