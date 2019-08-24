package com.dawei.test.demo.single;


/**
 * @author Dawei 2019/8/25.
 *
 * 单例模式的懒汉式
 *
 * 线程不安全
 */
public class SingletonLazy{

    private static SingletonLazy instance;

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }

}