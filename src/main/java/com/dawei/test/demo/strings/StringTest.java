package com.dawei.test.demo.strings;

import java.util.Arrays;

/**
 * @author by Dawei on 2018/6/10.
 */
public class StringTest {

	public static void main(String[] args) {
		System.out.println("\uD83D\uDE0F");
		String str = "密云水库骑行\uD83D\uDE0F但是";
		System.out.println(str.indexOf("但"));
		System.out.println(str.indexOf("\uD83D\uDE0F"));
		System.out.println(str.offsetByCodePoints(0, 7));
		System.out.println(str.substring(0, str.offsetByCodePoints(0, 7)));
		System.out.println(str.substring(0, 7));
		int length1 = str.length();
		for (int i = 0; i < length1; i++) {
			System.out.println(str.charAt(i));
			System.out.println(str.codePointAt(i));

		}

		String str1 = "你想説些生麽#$#就説寫生麽吧";
		String[] result = str1.split("#\\$#");
        str1 = "";
		String[] split = str1.split(",");
		for (String s : split) {
			System.out.println(s);
		}
		String[] strs = new String[1];
		//strs[0] = "1";
       /* strs[1] = "1";
        strs[2] = "1";*/
		System.out.println(" ______________" + String.join("@", strs));
		System.out.println(Arrays.toString(split));

		String teplate = "asabdb${V1}${v2}sdv${c2}";
		System.out.println(teplate.substring(0, teplate.length() - 1));
		String temp = teplate;
		while (temp.contains("$")) {
			int length = temp.length();

			int i = temp.indexOf("${");
			int j = temp.indexOf("}");
			String substring = temp.substring(i + 2, j);
			System.out.println(("i   " + i + "   j  " + j + "  sub=" + substring));
			temp = temp.substring(j + 1, length);

		}

	}
}
