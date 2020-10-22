package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 844. 比较含退格的字符串
 * https://leetcode-cn.com/problems/backspace-string-compare/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question844 {


	public static void main(String[] args) {


		//System.out.println(new Question844().backspaceCompare("ab#c", "ad#c#c"));
		//System.out.println(new Question844().backspaceCompare("bxj##tw", "bxj###tw"));
		//System.out.println(new Question844().backspaceCompare("bbb", "bb#"));
		System.out.println(new Question844().backspaceCompare("nzp#o#g", "b#nzp#o#g"));
	}


	public boolean backspaceCompare(String S, String T) {

		//双指针

		char[] charOne = S.toCharArray();
		char[] charTwo = T.toCharArray();
		int indexOne = charOne.length - 1;
		int indexTwo = charTwo.length - 1;
		while (indexOne >= 0 && indexTwo >= 0) {
			indexOne = backspaceCompare(charOne, indexOne);
			indexTwo = backspaceCompare(charTwo, indexTwo);

			if (indexOne == -1 && indexTwo == -1) {
				return true;
			}
			if ((indexOne + indexTwo) < 0 || (indexOne * indexTwo) < 0) {

				return false;
			}
			if (charOne[indexOne] != charTwo[indexTwo]) {
				return false;
			}
			indexOne--;
			indexTwo--;
		}

		return indexOne < 0 && indexTwo < 0;

	}


	public int backspaceCompare(char[] charAry, int start) {
		int less = 0;
		while (start >= 0) {
			if (charAry[start] == '#') {
				less++;
				start--;
			} else if (less > 0) {
				less--;
				start--;
			} else {
				break;
			}
		}
		return start;
	}
}
