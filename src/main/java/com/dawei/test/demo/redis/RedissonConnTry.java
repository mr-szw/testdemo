package com.dawei.test.demo.redis;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import com.dawei.test.demo.utils.GsonUtil;
import com.google.common.collect.Lists;

public class RedissonConnTry {

	private static final int SCAN_INTERVAL = 5000;
	private static final int CONN_TIMEOUT = 5000;
	private static final int TIMEOUT = 5000;

	public static void main(String[] args) throws InterruptedException {

		List<String> nodeList = new ArrayList<>();
		nodeList.removeAll(Lists.newArrayList("11", "22"));



		Config config = new Config();
		config.useClusterServers()
				.addNodeAddress("redis://10.38.160.5:39811", "redis://10.38.160.22:39816", "redis://10.38.160.26:39813")
				.setConnectTimeout(CONN_TIMEOUT)
				.setTimeout(TIMEOUT)
				.setScanInterval(SCAN_INTERVAL);

		RedissonClient redissonClient = Redisson.create(config);

		//创建锁对象 用锁对象去创建锁
		RLock lock = redissonClient.getLock("lock");

		try {

			System.out.println("holdCount =" + lock.getHoldCount());
			lock.lock(10, TimeUnit.SECONDS);
			System.out.println("get lock no try");
			System.out.println("holdCount =" + lock.getHoldCount());
			getLockInfo(redissonClient);

			/*
			 *  @param waitTime the maximum time to acquire the lock
			 *      * @param leaseTime lease time
			 *      * @param unit time unit
			 */
			boolean tryLock = lock.tryLock(10, 10, TimeUnit.SECONDS);
			System.out.println("holdCount =" + lock.getHoldCount());

			getLockInfo(redissonClient);
			if (tryLock) {
				System.out.println("get lock");
			}

			new Thread(() -> {
				while (true) {
					try {

						boolean tryLockOther = lock.tryLock(2, 10, TimeUnit.SECONDS);
						if (tryLockOther) {
							System.out.println("other get");
							getLockInfo(redissonClient);
							break;
						} else {
							System.out.println("other no get");
						}
						System.out.println("holdCount =" + lock.getHoldCount());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}).start();

			Thread.currentThread().join();

		} finally {
			lock.unlock();
		}

		System.exit(1);

	}



	public static void getLockInfo(RedissonClient redissonClient) {
		RMapCache<Object, Object> mapCache = redissonClient.getMapCache("lock");
		System.out.println(GsonUtil.toJson(mapCache));
	}

}
