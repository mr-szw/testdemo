package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

/**
 * 33 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *
 * @author sinbad on 2020/09/06.
 * <p>
 */
public class Question33 {


	public static void main(String[] args) {

		Random random = new Random();
		int len = 10;
		int[] nums = new int[len];
		for (int i = 0; i < len; i++) {
			nums[i] = random.nextInt(200);
		}


		System.out.println(new Gson().toJson(new Question33().search(nums, 2)));
	}


	public int search(int[] nums, int target) {

		return 0;
		//return search(nums, 0, nums.length -1, target);
	}




//	public int search(int[] nums, int start, int end, int target) {
//
//		int mid = (start + end) >> 1;
//
//		if (nums[mid] == target) {
//			return mid;
//		} else if (target < nums[end]) {
//			//中间往后是对的 或者中间往后 部分是对的 这一节是对的
//			if (nums[mid] < target) {
//				start = mid + 1;
//				//正常情况
//				if (target > nums[start]) {
//
//				} else {
//					end =
//					search(nums, start, )
//				}
//
//
//				return search(nums, start, end, target);
//			}else  {
//				end = mid - 1;
//				return search(nums, start, end, target);
//				}
//
//
//
//
//			}
//
//
//		} else if (target > nums[start]) {
//
//		}
//
//
//
//
//
//		return -1;
//
//	}
}
