package com.dawei.test.demo.future;

@FunctionalInterface
public interface TaskGetSupplier<T> {
	T get(String key);

}
