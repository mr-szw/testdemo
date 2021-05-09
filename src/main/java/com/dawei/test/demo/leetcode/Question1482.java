package com.dawei.test.demo.leetcode;

/**
 * https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/
 * 37. 解数独
 *
 * @author sinbad on 2020/09/15.
 */
public class Question1482 {

	public static void main(String[] args) {

		int[] bloomDay = {1, 10, 3, 10, 2};
		System.out.println(new Question1482().minDays(bloomDay, 3, 1));
		System.out.println("STOP");
	}

	public int minDays(int[] bloomDay, int m, int k) {
		int length = bloomDay.length;

		if (m * k > length) {
			return -1;
		}
		int low = Integer.MAX_VALUE;
		int hight = Integer.MIN_VALUE;
		for (int num : bloomDay) {
			low = Math.min(low, num);
			hight = Math.max(hight, num);
		}

		while (low < hight) {
			int mid = (hight - low) / 2 + low;

			if (checkCanMake(bloomDay, mid, m, k)) {
				hight = mid;
			} else {
				low = mid + 1;
			}
		}
		return hight;

	}

	/**
	 * tryDay尝试的天数
	 * needM 花束量
	 * needK 每束花 连续
	 */
	private boolean checkCanMake(int[] bloomDay, int tryDay, int needM, int needK) {
		int hasNum = needK;
		for (int needDay : bloomDay) {
			if (needDay <= tryDay) {
				hasNum--;
				if (hasNum == 0) {
					needM--;
					hasNum = needK;
				}
			} else {
				hasNum = needK;
			}
		}
		return needM <= 0;
	}
}
