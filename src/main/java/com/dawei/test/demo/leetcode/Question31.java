package com.dawei.test.demo.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 * https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 *
 * @author sinbad on 2021/1/6.
 */
public class Question31 {

	public static void main(String[] args) {

		int[] position = new int[]{1, 2, 3, 4, 5};
		int[] positionOut = new int[]{4, 5, 3, 2, 1};
		System.out.println(new Gson().toJson(new Question31().validateStackSequences(position, positionOut)));
	}


	public boolean validateStackSequences(int[] pushed, int[] popped) {

		Stack<Integer> queue = new Stack<>();

		int index = 0;
		int length = pushed.length;

		for (int i : popped) {
			//System.out.println("Check=" + i);
			if (!queue.isEmpty() && queue.peek() == i) {
				queue.pop();
				//System.out.println("Pop=" + i);
			} else {
				while (index < length) {
					if (pushed[index] == i) {
						index++;
						//System.out.println("push=" + i);
						//System.out.println("Pop=" + i);
						break;
					}
					//System.out.println("push=" + pushed[index]);
					queue.push(pushed[index++]);
				}
			}
			//System.out.println("");
		}
		return queue.isEmpty();
	}
}
