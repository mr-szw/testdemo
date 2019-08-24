package com.dawei.test.demo.single;


/**
 * @author Dawei 2019/8/25.
 *
 * 单例模式的 懒汉式 锁的方式
 *
 * 线程不安全
 */
public class SingletonLock {

    private static SingletonLock instance;

    private SingletonLock() {
    }

    public static synchronized SingletonLock getInstance() {
        if (instance == null) {
            instance = new SingletonLock();
        }
        return instance;
    }

}