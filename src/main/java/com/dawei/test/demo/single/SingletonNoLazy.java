package com.dawei.test.demo.single;


/**
 * @author Dawei 2019/8/25.
 *
 * 单例模式的饿汉式
 *
 * 它基于 classloader 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化
 * 线程安全
 *
 */
public class SingletonNoLazy {

    private static SingletonNoLazy instance = new SingletonNoLazy();

    private SingletonNoLazy() {
    }

    public static SingletonNoLazy getInstance() {
        return instance;
    }

}