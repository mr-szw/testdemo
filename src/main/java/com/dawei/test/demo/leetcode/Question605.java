package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 605. 种花问题
 * https://leetcode-cn.com/problems/can-place-flowers/
 *
 * @author sinbad on 2020/09/18.
 * 技巧好多 不会玩
 * <p>
 */
public class Question605 {


	public static void main(String[] args) {

		//System.out.println(new Question605().canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
		//System.out.println(new Question605().canPlaceFlowers(new int[]{0, 0, 1, 0, 1}, 1));
		//System.out.println(new Question605().canPlaceFlowers(new int[]{0, 0}, 2));
		System.out.println(new Question605().canPlaceFlowers(new int[]{0, 0, 0}, 2));
	}

	/**
	 * 有两边
	 * 101        0
	 * 1001       0
	 * 10001      1
	 * 100001     1
	 * 1000001    2
	 * (n - 1 ) /2
	 * <p>
	 * 一侧边
	 * 01        0
	 * 001       1
	 * 0001      1
	 * 00001     2
	 * 000001    2
	 * n / 2
	 * <p>
	 * 两侧无边
	 * 0        1
	 * 00       1
	 * 000      2
	 * 0000     2
	 * 00000    3
	 * （n + 1） / 2
	 */
	public boolean canPlaceFlowers(int[] flowerbed, int n) {

		int count = 0;
		int start = 0;
		int length = flowerbed.length;
		for (int i = 0; i < length; i++) {
			//右侧有边
			if (flowerbed[i] == 1) {
				if (start == 0) {
					count += i / 2;
				} else {
					count += (i - start - 1) / 2;
				}
				start = i + 1;
				if (count > n) {
					return true;
				}
			}
		}
		if (start == 0) {
			//两侧无边
			count = (length + 1) / 2;

		} else
			//处理了一部分但是右侧无边
			if (start < length) {
				count += (length - start) / 2;
			}
		return count >= n;
	}
}