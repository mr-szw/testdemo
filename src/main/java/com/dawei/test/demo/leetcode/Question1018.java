package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * 1018. 可被 5 整除的二进制前缀 https://leetcode-cn.com/problems/binary-prefix-divisible-by-5/
 * 
 * @author sinbad on 2021/1/14.
 */
public class Question1018 {

	public static void main(String[] args) {

		System.out.println(
				new Gson().toJson(new Question1018().prefixesDivBy5(new int[] { 1, 0, 1 })));
	}

	public List<Boolean> prefixesDivBy5(int[] A) {

		List<Boolean> resultList = new ArrayList<>();
		long count = 0;
		for (int i : A) {

			//累加多余的就好
			count = ((count << 1) + i ) % 5;
			resultList.add(count  == 0);
		}
		return resultList;
	}

}
