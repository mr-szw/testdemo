package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 二分查找
 *
 * @author sinbad on 2020/08/24.
 */
public class Question704 {


	public static void main(String[] args) {
		int len = 20;
		List<Integer> integerList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			integerList.add(random.nextInt());
		}
		Integer[] integers = integerList.stream().sorted().toArray(Integer[]::new);


		int target = random.nextInt();
		System.out.println(new Question704().search(integers, target));

	}


	public int search(Integer[] nums, int target) {

		int length = nums.length - 1;
		int left = 0;
		int right = length;
		while (left <= right) {
			int temp = (left + right) / 2;
			if (target == nums[temp]) {
				return temp;
			} else if (target < nums[temp]) {
				right = temp - 1;
			} else {
				left = temp + 1;
			}
		}

		return -1;

	}


}
