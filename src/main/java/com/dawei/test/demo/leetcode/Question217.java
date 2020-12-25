package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.gson.Gson;

/**
 * 217. 存在重复元素
 * https://leetcode-cn.com/problems/contains-duplicate/
 *
 * @author sinbad on 2020/12/14.
 */
public class Question217 {


	public static void main(String[] args) {

		int[] position = new int[]{2, 5, 2, 1, 2, 1};
		System.out.println(new Gson().toJson(new Question217().containsDuplicate(position)));
	}

	public boolean containsDuplicate(int[] nums) {
		//set
		//排序后遍历比较
		//
		List<int[]> collect = Stream.of(nums).distinct().collect(Collectors.toList());
		String collect1 = IntStream.of(nums).distinct().mapToObj(Objects::toString).collect(Collectors.joining(","));
		long collect2 = IntStream.of(nums).distinct().count();
		collect.size();
		return false;
	}

}
