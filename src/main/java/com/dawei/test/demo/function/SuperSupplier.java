package com.dawei.test.demo.function;





@FunctionalInterface
public interface SuperSupplier<T> {
    T get() throws Throwable;
}
