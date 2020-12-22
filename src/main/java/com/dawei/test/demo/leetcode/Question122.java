package com.dawei.test.demo.leetcode;


import com.google.gson.Gson;

/**
 * 122. 买卖股票的最佳时机 II
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * @author sinbad on 2020/11/22.
 * <p>
 */
public class Question122 {


	public static void main(String[] args) {

		int[] prices = new int[]{};

		System.out.println(new Gson().toJson(new Question122().maxProfit(prices)));
	}

//	public int maxProfit(int[] prices) {
//
//		if (prices == null || prices.length <= 1) {
//			return 0;
//		}
//		//定义状态转移方程
//		//定义转移方程初始值
//		//找到子方程
//
//
//		int length = prices.length;
//		int[][] dp = new int[length][2];
//
//		//定义子问题 某一天买与不买的获得利润 转化为某一天持有股票或者不持有
//		//第i天持有 存在情况就是之前他买过 或者昨天没有但今天他买了
//		//第1天不持有 存在情况就是之前他就没有 或者昨天有但他卖了
//
//		//第0天他有
//		dp[0][0] = 0 - prices[0];
//		//第0天他没有
//		dp[0][1] = 0;
//
//		for (int i = 1; i < length; i++) {
//			//hold
//			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] - prices[i]);
//			//nohold
//			dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + prices[i]);
//		}
//		return dp[length - 1][1];
//	}


	// //优化过的动态规划，因为后面的值不会再用到 就不需要数组记录了
//	public int maxProfit(int[] prices) {
//
//		if (prices == null || prices.length <= 1) {
//			return 0;
//		}
//		int hold = 0 - prices[0];
//		int noHold = 0;
//		for (int i = 1; i < prices.length; i++) {
//			int nowHold = Math.max(hold, noHold - prices[i]);
//			int nowNoHold = Math.max(noHold, hold + prices[i]);
//			hold = nowHold;
//			noHold = nowNoHold;
//		}
//		return noHold;
//	}

//  //贪心算法 逻辑有点不是特别好想
//	public int maxProfit(int[] prices) {
//
//		if (prices == null || prices.length <= 1) {
//			return 0;
//		}
//		int count = 0;
//		for (int i = 1; i < prices.length; i++) {
//			if (prices[i] - prices[i - 1] > 0) {
//				count += (prices[i] - prices[i - 1]);
//			}
//		}
//		return count;
//	}


	//找所有的上坡
	public int maxProfit(int[] prices) {

		if (prices == null || prices.length <= 1) {
			return 0;
		}
		int count = 0;
		int lastIndex = 0;
		int index = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] - prices[i -1] >= 0) {
				lastIndex = i;
			} else {
				count += (prices[lastIndex] - prices[index]);
				index = i;
				lastIndex = i;
			}
		}
		if (prices[index] < prices[lastIndex]) {
			count += prices[lastIndex] - prices[index];
		}
		return count;
	}
}
