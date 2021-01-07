package com.dawei.test.demo.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dawei.test.demo.pojo.DemoPojo;

/**
 * @author Dawei on 2019/12/27
 */
public class TaskScheduler {

	private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);

	public static void main(String[] args) {
		ResourceScheduler<DemoPojo, Boolean> uploadPerfObj = new ResourceScheduler<>(
				"uploadPerfObj",

				100000, // 最大Queue里面放10万对象
				2, // 最大2个线程同时处理
				new DoWork<>(),
				false, // 不保存结果
				-1,
				-1, // 不限制批处理数量
				new ErrorDoWork<>(),
				1000);


		//uploadPerfObj.
	}

}

class DoWork<DemoPojo, Boolean> implements ResourceScheduler.Method<DemoPojo, Boolean> {

	@Override
	public Object[] execute(int threadIndex, List<DemoPojo> obj) throws Throwable {
		return new Object[0];
	}
}

class ErrorDoWork<T> implements ResourceScheduler.ErrorProcessor<T> {

	@Override
	public void process(T obj, Throwable ex) {

	}
}