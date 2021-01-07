package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

/**
 * 347. 前 K 个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * @author sinbad on 2020/09/07.
 */
public class Question347 {


	public static void main(String[] args) {
		int len = 1;
		int[] nums = new int[]{6, 0, 1, 4, 9, 7, -3, 1, -4, -8, 4, -7, -3, 3, 2, -3, 9, 5, -4, 0};
//		Random random = new Random();
//		for (int i = 0; i < len; i++) {
//			nums[i] = random.nextInt();
//		}
//

		//	int target = random.nextInt();
		System.out.println(new Gson().toJson(new Question347().topKFrequent(nums, 6)));
	}

	public int[] topKFrequent(int[] nums, int k) {

		Map<Integer, Integer> resultMap = new HashMap<>();
		Map<Integer, Integer> counterMap = new HashMap<>();
		int len = 0;
		int min = Integer.MAX_VALUE;
		for (int num : nums) {
			Integer orDefault = counterMap.get(num);
			if (orDefault == null) {
				orDefault = 1;
				len++;
			} else {
				orDefault += 1;
			}
			counterMap.put(num, orDefault);

			if (len <= k) {
				resultMap.put(num, orDefault);
				min = Math.min(orDefault, min);
			} else {
				if (resultMap.containsKey(num) || orDefault <= min) {
					continue;
				}
				for (Map.Entry<Integer, Integer> next : resultMap.entrySet()) {
					Integer value = next.getValue();
					if (value < orDefault) {
						resultMap.remove(next.getKey());
						resultMap.put(num, orDefault);
						min = Math.min(orDefault, min);
						break;
					}
				}
			}
		}

		int[] resultAry = new int[k];
		int i = 0;
		for (
				Integer integer : resultMap.keySet()) {
			resultAry[i++] = integer;
		}
		return resultAry;
	}


}
