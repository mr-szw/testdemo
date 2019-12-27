package com.dawei.test.demo.function;

import java.util.concurrent.Callable;

public interface FutureGetter<T>  extends Callable<T> {

	T get();
}
