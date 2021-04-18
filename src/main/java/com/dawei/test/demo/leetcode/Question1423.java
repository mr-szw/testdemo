package com.dawei.test.demo.leetcode;

/**
 * https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
 *
 * @author sinbad on 2021/2/6.
 */
public class Question1423 {

	public static void main(String[] args) {
		//[11,49,100,20,86,29,72]
		//[1,2,3,4,5,6,1]
		//3
		//4
		System.out.println(new Question1423().maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
	}

	public int maxScore(int[] cardPoints, int k) {
		int length = cardPoints.length;
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += cardPoints[i];
		}
		if (length == k) {
			return sum;
		}
		int left = k - 1;
		int right = length - 1;
		int max = sum;
		while (left >= 0){
			max = Math.max(max, sum - cardPoints[left] + cardPoints[right]);
			sum = sum - cardPoints[left] + cardPoints[right];
			left--;
			right--;
		}
		return max;
	}
}
