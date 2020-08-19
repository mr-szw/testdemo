package com.dawei.test.demo.loader;

public class ClassLoaderDemo {


    private int a = 0;

    public int j = print("参数1");


    {
        print("构造块");
    }

    static {
        print("静态块");
    }


    public static ClassLoaderDemo t1 = new ClassLoaderDemo("属性类1");
    public static ClassLoaderDemo t2 = new ClassLoaderDemo("属性类2");
    public static int i = print("参数2");
    public static int n = 99;
    public static int k = 0;


    public ClassLoaderDemo(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String args[]) {
        ClassLoaderDemo t = new ClassLoaderDemo("init");
    }


    /**
     * 静态块
     * 属性 和 构造块同级
     * 属性初始化
     *
     */
}