package com.dawei.test.demo;

public class BaseTest {



	public static void main(String[] args) {

		int num = 0;
		String numStr = String.valueOf(num);
		StringBuffer numStrBuffer = new StringBuffer(num);

		num++;

		method(num, numStr, numStrBuffer);

		System.out.println(num);
		System.out.println(numStr);
		System.out.println(numStrBuffer.toString());
	}

	public static void method(int num, String numStr, StringBuffer numStrBuffer) {

		num++;
		numStr = numStr + "-" + num;
		numStrBuffer.append(numStr);
	}


}
