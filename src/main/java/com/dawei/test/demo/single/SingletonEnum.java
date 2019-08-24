package com.dawei.test.demo.single;


/**
 * @author Dawei 2019/8/25.
 *
 * 单例模式的枚举方式
 *
 * 线程安全
 */
public enum  SingletonEnum {

    INSTANCE;

    private SingletonClass singletonClass;

    SingletonEnum() {
        this.singletonClass = new SingletonClass();
    }

    public SingletonClass getSingletonClass() {
        return singletonClass;
    }
}