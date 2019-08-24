package com.dawei.test.demo.single;


/**
 * @author Dawei 2019/8/25.
 *
 * 单例模式的静态内部类
 *
 * 线程安全
 * 对静态域使用延迟初始化
 */
public class SingletonStaticInner {

    private static class SingletonInner {
        private static final SingletonStaticInner INSTANCE = new SingletonStaticInner();
    }

    private SingletonStaticInner() {
    }

    public static final SingletonStaticInner getInstance() {
        return SingletonInner.INSTANCE;
    }

}