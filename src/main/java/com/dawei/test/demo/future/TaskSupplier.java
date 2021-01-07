package com.dawei.test.demo.future;

@FunctionalInterface
public interface TaskSupplier<T> {
	T execute();

}
