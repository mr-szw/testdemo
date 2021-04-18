package com.dawei.test.demo.leetcode;

/**
 * 189. 旋转数组
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * @author sinbad on 2021/1/8.
 */
public class Question189 {


	public static void main(String[] args) {

		new Question189().rotate(new int[]{1, 2, 3, 4}, 2);

	}


	//
	public void rotate(int[] nums, int k) {

		int length = nums.length;
		int step = k % length;
		if (step == 0) {
			return;
		}

		reverse(nums, 0, length - 1);
		reverse(nums, 0, step - 1);
		reverse(nums, step, length - 1);

	}

	//两级反转
	public void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}


// 环式替换 追踪  主要是去环
//	public void rotate(int[] nums, int k) {
//
//		int length = nums.length;
//		int step = k % length;
//		if (step == 0) {
//			return;
//		}
//		int count = 0;
//		for (int i = 0; i < length && count < length; i++) {
//
//			int tempIndex = i;
//			int startValue = nums[i];
//			while (true) {
//				int targetIndex = (tempIndex + step) % length;
//
//				int temp = nums[targetIndex];
//				nums[targetIndex] = startValue;
//				startValue = temp;
//				tempIndex = targetIndex;
//				count++;
//				if (targetIndex == i) {
//					break;
//				}
//			}
//		}
//	}
}
