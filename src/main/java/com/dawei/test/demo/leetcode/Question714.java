package com.dawei.test.demo.leetcode;

import com.google.gson.Gson;

/**
 * 714. 买卖股票的最佳时机含手续费
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * @author sinbad on 2020/12/17.
 */
public class Question714 {

	public static void main(String[] args) {
		System.out.println(new Gson().toJson(new Question714().maxProfit(new int[]{1, 2}, 1)));
	}
//
//	public int maxProfit(int[] prices, int fee) {
//
//		if (prices == null || prices.length < 2) {
//			return 0;
//		}
//
//		int length = prices.length;
//		//子问题就是当天持有股票的利润和当天不持有股票得利润
//		//持有 就要买入  初始的时候没有卖出的情况   dp[i][i] = {dp[i - 1][1], dp[i - 1][0] - prices[i]}
//		int hold = 0 - prices[0];
//		//不持有就是没有 其他情况有卖了 dp[i][0] = {dp[i - 1][0], dp[i -1][1] + prices[i] - fee}
//		int noHold = 0;
//
//		for (int i = 1; i < length; i++) {
//			//有
//			int nowHold = Math.max(hold, noHold - prices[i]);
//			//没有
//			int nowNoHold = Math.max(noHold, hold + prices[i] - fee);
//			noHold = nowNoHold;
//			hold = nowHold;
//		}
//
//		return noHold;
//	}


//什么时候是最好的买入买出
	public int maxProfit(int[] prices, int fee) {

		if (prices == null || prices.length < 2) {
			return 0;
		}

		int length = prices.length;

		int buy = prices[0] + fee;
		int profit = 0;

		for (int i = 1; i < length; i++) {
			if ((prices[i] + fee) < buy) {
				//记录费用并补加手续费
				buy = prices[i] + fee;
			} else if (prices[i] > buy){
				profit += (prices[i] - buy);
				//如果后续没有变更继续上涨 就假装我这边没有买入 所以不计入手续费 但是如果不上涨了 那么就得记入手续费
				buy = prices[i];
			}
		}

		return profit;
	}
}
