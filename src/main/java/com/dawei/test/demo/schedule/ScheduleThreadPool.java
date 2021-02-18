package com.dawei.test.demo.schedule;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author sinbad on 2021/2/14.
 */
public class ScheduleThreadPool {


	public static void main(String[] args) {

		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		//等100毫秒然后每200毫秒执行一次
		scheduledExecutorService.scheduleAtFixedRate(() -> {

			System.out.println(new Date() + "  " + Thread.currentThread());


		}, 100,200, TimeUnit.MILLISECONDS);


	}






}
