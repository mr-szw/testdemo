package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.A;

/**
 * 448. 找到所有数组中消失的数字 https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * @author sinbad on 2021/02/13.
 */
public class Question448 {

	public static void main(String[] args) {

		System.out.println(
				new Question448().findDisappearedNumbers(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 }));
	}


	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			while (num > 0 && nums[num - 1] > 0) {
				int nextIndex = nums[num -1];
				nums[num - 1] = -1;
				num = nextIndex;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0)
				result.add(i + 1);
		}
		return result;
	}


	public List<Integer> findDisappearedNumbers0(int[] nums) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			mark(nums, i);
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != -1)
				result.add(i + 1);
		}
		return result;
	}




	private void mark(int[] array, int i) {
		if (array[i] == -1) {
			return; // 当前下标已标记跳过
		}
		// 下个位置
		int nextIndex = array[i] - 1;
		// 对应下标处已标记，跳过
		if (array[nextIndex] == -1) {
			return;
		}
		// 将当前位置数据设置为 -1 操作下一个的位置
		array[i] = array[nextIndex];
		array[nextIndex] = -1;
		mark(array, i);
	}

}
