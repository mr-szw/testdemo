package com.dawei.test.demo.utils;

public class ClassLoaderDemo {


	private static int k = 0;
	private static int valueI = print("valueI");
	private static ClassLoaderDemo classLoaderDemo1 = new ClassLoaderDemo("属性实例1");
	private static ClassLoaderDemo classLoaderDemo2 = new ClassLoaderDemo("属性实例2");


	private static int n = 99;
	public int valueJ = print("valueJ");

	{
		print("构造块");
	}

	static {
		print("静态块");
	}

	public ClassLoaderDemo(String str) {
		System.out.println((++k) + ":" + str + "   valueI=" + valueI + "    n=" + n);
		++valueI;
		++n;
	}

	public static int print(String str) {
		System.out.println((++k) + ":" + str + "   valueI=" + valueI + "    n=" + n);
		++n;
		return ++valueI;
	}

	public static void main(String args[]) {
		ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo("init");
	}
}