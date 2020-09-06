package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 第k个排列
 * https://leetcode-cn.com/problems/permutation-sequence/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question60 {


	public static void main(String[] args) {

		System.out.println(new Question60().getPermutation(3, 3));
	}


	public String getPermutation(int n, int k) {

		//n =4;  k = 15

		//n这个数字能构建多少数据
		//比如 4 能搞出 24个 3能搞出 6个
		// 所以 15 是第三个6的内容 第二个六 是2开头   那么第三个就是3开头

		List<String> markList = new ArrayList<>();
		StringBuilder result = new StringBuilder();
		int tempLen = 1;
		for (int i = 1; i <= n; i++) {
			markList.add(String.valueOf(i));
			tempLen = tempLen * i;
		}
		tempLen = tempLen / n;
		int step = 0;
		do {
			while (k - tempLen > 0) {
				step++;
				k = k - tempLen;
			}
			result.append(markList.get(step));
			markList.remove(step);
			n = n - 1;
			if (n<=0) {
				break;
			}

			tempLen = tempLen / n;
			step = 0;
		} while (markList.size() > 0);

//
//		int tempLen = 1;
//
//		do {
//			tempLen = tempLen * step;
//			if (k == 0) {
//				break;
//			}
//			if (tempLen <= k && tempLen * (step + 1) > k) {
//				//长度在两个长度范围内
//				int temp = 0;
//				while (k > tempLen) {
//					k = k - tempLen;
//					temp++;
//				}
//
//				result.append(markList.get(temp));
//				markList.remove(temp);
//				tempLen = 1;
//				step = 1;
//			} else {
//				step++;
//			}
//		} while (markList.size() > 0);

		//得到 k在第几段区间
		return result.toString() + String.join("", markList);
	}


}
