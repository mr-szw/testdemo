package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 123. 买卖股票的最佳时机 III
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * @author sinbad on 2021/1/09.
 * <p>
 */
public class Question123 {


	public static void main(String[] args) {

		int[] position = new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
		System.out.println(new Gson().toJson(new Question123().maxProfit(position)));
	}


	public int maxProfit(int[] prices) {
		int length = prices.length;

		//0的时候 第一次买入 或者 不买
		int buy1 = Math.min(0, 0 - prices[0]);
		//卖出 保留上次的情况或者卖出（卖出得先买入)
		int sell1 = Math.max(0, buy1 + prices[0]);

		//操做同第一次 （同一天操作的）
		int buy2 = -prices[0];
		int sell2 = Math.max(0, buy2 + prices[0]);


		for (int i = 1; i < length; i++) {
			//不动  或者 第一次买入
			buy1 = Math.max(buy1, -prices[i]);
			//不动或者 第一次卖出，对冲买入成本(昨天的买入)
			sell1 = Math.max(sell1, buy1 + prices[i]);

			//不动 或者 从第一次的卖出资本上买入
			buy2 = Math.max(buy2, sell1 - prices[i]);
			//不动或者 或者 从第二次买入再卖出，对冲第二次买入成本(昨天的买入)
			sell2 = Math.max(sell2, buy2 + prices[i]);
		}
		return sell2;
	}

//
//	public int maxProfit(int[] prices) {
//		int length = prices.length;
//		int[] buy1 = new int[length];
//		int[] sell1 = new int[length];
//		int[] buy2 = new int[length];
//		int[] sell2 = new int[length];
//
//		//0的时候 第一次买入 或者 不买
//		buy1[0] = Math.min(0, 0 - prices[0]);
//		//卖出 保留上次的情况或者卖出（卖出得先买入)
//		sell1[0] = Math.max(0, buy1[0] + prices[0]);
//
//		//操做同第一次 （同一天操作的）
//		buy2[0] = - prices[0];
//		sell2[0] = Math.max(0, buy2[0] + prices[0]);
//
//
//		for (int i = 1; i < length; i++) {
//
//			//不动  或者 第一次买入
//			buy1[i] = Math.max(buy1[i - 1],  - prices[i]);
//			//不动或者 第一次卖出，对冲买入成本(昨天的买入)
//			sell1[i] = Math.max(sell1[i - 1], buy1[i - 1] + prices[i]);
//
//			//不动 或者 从第一次的卖出资本上买入
//			buy2[i] = Math.max(buy2[i - 1], sell1[i - 1] - prices[i]);
//			//不动或者 或者 从第二次买入再卖出，对冲第二次买入成本(昨天的买入)
//			sell2[i] = Math.max(sell2[i - 1], buy2[i - 1] + prices[i]);
//		}
//		return sell2[length - 1];
//	}

	//错误示例 可能先跌再涨
//	public int maxProfit(int[] prices) {
//
//		List<Integer> valueList = new ArrayList<>();
//		//列出所有交易方式 找到其中最大的两个
//
//
//		//买入
//		int inIndex = 0;
//		//卖出
//		int outIndex = 0;
//
//
//		for (int i = 0; i < prices.length - 1; ) {
//			while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
//				i++;
//			}
//			outIndex = i;
//			//卖出
//			valueList.add(prices[outIndex] - prices[inIndex]);
//			//买入
//			i++;
//			inIndex = i;
//		}
//
//		Collections.sort(valueList);
//		int size = valueList.size();
//		if (size > 1) {
//			return valueList.get(size - 1) + valueList.get(size - 2);
//		} else if (size == 1) {
//			return valueList.get(0);
//		}
//
//		return 0;
//	}
}