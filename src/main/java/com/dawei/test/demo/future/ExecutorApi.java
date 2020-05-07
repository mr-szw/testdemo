package com.dawei.test.demo.future;

/**
 * deprecated
 */
@FunctionalInterface
public interface ExecutorApi<T> {

	T execute() throws Throwable;
}
