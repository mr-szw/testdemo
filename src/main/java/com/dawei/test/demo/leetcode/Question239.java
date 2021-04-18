package com.dawei.test.demo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * @author sinbad on 2020/12/01.
 */
public class Question239 {

	public static void main(String[] args) {

		System.out.println(new Gson().toJson(new Question239().maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3)));
		//System.out.println(new Gson().toJson(new Question239().maxSlidingWindow(new int[]{7, 2, 4}, 2)));
	}

	//n个数的大小比较
	public int[] maxSlidingWindow(int[] nums, int k) {
		int length = nums.length;
		int winLen = length - k + 1;
		int[] resultAry = new int[winLen];
		//有效队列
		LinkedList<Integer> fillNumList = new LinkedList<>();
		int index = 0;
		//获取初始最大值
		for (int i = 0; i < length; i++) {
			//从右向左剔除
			while (!fillNumList.isEmpty() && nums[fillNumList.peekLast()] <= nums[i]) {
				fillNumList.pollLast();
			}
			fillNumList.addLast(i);
			if (i - fillNumList.peekFirst() >= k) {
				fillNumList.pollFirst();
			}
			if (i + 1 >= k) {
				resultAry[index++] = nums[fillNumList.peekFirst()];
			}
		}
		return resultAry;
	}
}
