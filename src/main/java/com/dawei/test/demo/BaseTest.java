package com.dawei.test.demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.dawei.test.demo.pojo.DemoPojo;
import com.dawei.test.demo.utils.GsonUtil;

public class BaseTest {



	public static void main(String[] args) {

		int num = 0;
		String numStr = String.valueOf(num);
		StringBuffer numStrBuffer = new StringBuffer(num);
		String entryValue = "Invalid value 'N/A' passed for customfield 'Reproductivity'. Allowed values are: 16771[Always], 16772[High], 16773[Low], 16774[Once], -1";
		String substring = entryValue.substring(entryValue.indexOf(". Allowed values are: ") + ". Allowed values are: ".length());
		String[] split = substring.split(",");


		DemoPojo demoPojo4 = GsonUtil.fromJson(" ", DemoPojo.class);

		DemoPojo demoPojo = new DemoPojo();
		demoPojo.setId(1L);
		demoPojo.setName("A");
		DemoPojo demoPojo1 = new DemoPojo();
		DemoPojo demoPojo2 = new DemoPojo();
		DemoPojo demoPojo3 = null;
		demoPojo1.setId(22L);
		demoPojo1.setName("C");
		demoPojo2.setId(1L);
		demoPojo2.setName("B");
		List<DemoPojo> demoPojos = Arrays.asList(demoPojo, demoPojo1, demoPojo2, demoPojo3);

		num++;

		method(num, numStr, numStrBuffer);

		System.out.println(num);
		System.out.println(numStr);
		System.out.println(numStrBuffer.toString());
	}

	public static void method(int num, String numStr, StringBuffer numStrBuffer) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		Iterator<StackTraceElement> iterator = Arrays.stream(stackTrace).iterator();
		while (iterator.hasNext()) {
			StackTraceElement next = iterator.next();
			System.out.println(next.getClassName() + "#" + next.getMethodName() + "(" + next.getFileName() + ")" + "  on line: " + next.getLineNumber());
		}
		System.out.println(Thread.currentThread().getStackTrace().toString());
		num++;
		numStr = numStr + "-" + num;
		numStrBuffer.append(numStr);
	}


}
