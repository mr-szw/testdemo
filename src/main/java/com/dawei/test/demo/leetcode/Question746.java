package com.dawei.test.demo.leetcode;

/**
 * 746. 使用最小花费爬楼梯
 https://leetcode-cn.com/problems/min-cost-climbing-stairs/solution/ *
 * @author sinbad on 2020/12/21.
 * dp
 */
public class Question746 {


	public static void main(String[] args) {

		new Question746().findMaxNoRepeatSonStr("abcadac");
		new Question746().minCostClimbingStairs(new int[]{1, 2, 0});


	}


	private String findMaxNoRepeatSonStr(String orginal){
		String[] orginals =  orginal.split("");
		String restult = "";

		for (int i = 0;i < orginals.length - 2;i++) {
			String tempStr = orginals[i];
			for (int j = i+1;j < orginals.length - 2;j++) {
				if (tempStr.contains(orginals[j])) {
					break;
				}
				tempStr += orginals[j];
			}
			if (tempStr.length() > restult.length()) {
				restult = tempStr;
			}
		}

		return restult;
	}

	/**
	 *  i = 1 /2
	 *  	1= cost
	 * @param cost
	 * @return
	 */
	public int minCostClimbingStairs(int[] cost) {
		int[] setCost = new int[cost.length];

		return 1;
	}
}
