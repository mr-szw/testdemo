package com.dawei.test.demo.leetcode;


import com.google.gson.Gson;

/**
 * 121. 买卖股票的最佳时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author sinbad on 2020/11/22.
 * 造成栈溢出 不会做啊
 * <p>
 */
public class Question121 {


	public static void main(String[] args) {

		int[] prices = new int[]{};

		System.out.println(new Gson().toJson(new Question121().maxProfit(prices)));
	}


	public int maxProfit(int[] prices) {

		if (prices == null || prices.length < 2) {
			return 0;
		}
		//仅交易一次 是不是要找后面的哪个比前面的差值最大
		int count = 0;
		int buy = Integer.MAX_VALUE;

		int length = prices.length;
		for (int i = 0; i < length; i++) {
			if (prices[i] < buy) {
				buy = prices[i];
			} else if (prices[i] - buy > count) {
				count = prices[i] - buy;
			}
		}
		return count;
	}
}
