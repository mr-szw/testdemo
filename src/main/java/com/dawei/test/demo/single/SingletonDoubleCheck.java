package com.dawei.test.demo.single;


/**
 * @author Dawei 2019/8/25.
 *
 * 单例模式的双重锁校验
 *
 * 线程安全
 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 */
public class SingletonDoubleCheck {

    private static volatile SingletonClass instance;

    private SingletonDoubleCheck() {
    }

    public static SingletonClass getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonClass();
                }
            }
        }
        return instance;
    }

}