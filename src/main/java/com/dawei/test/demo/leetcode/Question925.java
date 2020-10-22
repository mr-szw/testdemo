package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/long-pressed-name/
 * 925. 长按键入
 *
 * @author sinbad on 2020/10/21.
 */
public class Question925 {

	public static void main(String[] args) {

		//new Question925().isLongPressedName("abcd", "aaabcccdddd");
		new Question925().isLongPressedName("pyplrz", "ppyypllr");
		//new Question925().isLongPressedName("aaa", "aaaaab");
		System.out.println("STOP");
	}

	public boolean isLongPressedName(String name, String typed) {
		//首字母一定会相等 不相等直接false
		char[] typedAry = typed.toCharArray();
		char[] nameAry = name.toCharArray();
		int typedLength = typedAry.length;
		int nameLength = nameAry.length;
		int typeIndex = 1;
		int nameIndex = 1;
		if (nameAry[0] != typedAry[0]) {
			return false;
		}

		while (nameIndex < nameLength && typeIndex < typedLength) {
			if (typedAry[typeIndex] == nameAry[nameIndex]) {
				typeIndex++;
				nameIndex++;
			} else if ( typedAry[typeIndex] == typedAry[typeIndex - 1]) {
				typeIndex++;
			} else {
				return false;
			}
		}
		if (nameIndex != nameLength) {
			return false;
		}

		while (typeIndex < typedLength) {
			if (typedAry[typeIndex] != nameAry[nameLength -1]) {
				return false;
			}
			typeIndex++;
		}

		return true;
	}
}
