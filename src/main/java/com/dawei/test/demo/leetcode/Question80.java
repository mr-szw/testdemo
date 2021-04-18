package com.dawei.test.demo.leetcode;

/**
 * 24. 两两交换链表中的节点 https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question80 {

	public static void main(String[] args) {

		System.out.println(new Question80().removeDuplicates(new int[] { 1, 1, 1, 2, 2, 3 }));
	}

	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length <= 2) {
			return nums.length;
		}
		int putIndex = 0;
		int length = nums.length;
		int deleteCount = 0;
		int lastNum = nums[0];
		int lastCount = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == lastNum) {
				lastCount++;
				if (lastCount > 2) {
					deleteCount++;
					putIndex--;
				}
			} else {
				lastNum = nums[i];

				lastCount = 1;
			}
			putIndex++;
			nums[putIndex] = nums[i];
		}
		return length - deleteCount;
	}
}
