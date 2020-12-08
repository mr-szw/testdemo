package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. 插入区间
 * https://leetcode-cn.com/problems/insert-interval/
 *
 * @author sinbad on 2020/09/18.
 * 技巧好多 不会玩
 * <p>
 */
public class Question57 {


	public static void main(String[] args) {

		System.out.println(Arrays.deepToString(new Question57().insert0(new int[][]{{1, 5}}, new int[]{2, 7})));
		//System.out.println(Arrays.deepToString(new Question57().insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5})));
		//System.out.println(Arrays.deepToString(new Question57().insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));
	}


//	public int[][] insert(int[][] intervals, int[] newInterval) {
//
//		if (intervals.length == 0) {
//			return new int[][]{newInterval};
//		}
//		/**
//		 *  		归纳场景
//		 *  	1、完全在间隔左边
//		 *  	2、在两个间隔中间
//		 *  	3、在一个间隔内部
//		 *  	4、在多个间隔内部
//		 *  	5、
//		 *  	3、小于间隔左侧 大于等于间隔右侧
//		 *  	4、小于等于间隔右侧 大于等于间隔右侧
//		 *  	5、大于左侧间隔 小于右侧间隔
//		 */
//
//		int shortNum = newInterval[0];
//		int heightNum = newInterval[1];
//
//		int[][] resultAry = new int[intervals.length + 1][2];
//
//		int countNum = 0;
//
//		boolean checkAgain = true;
//
//		int[] thisEntry = new int[2];
//		boolean inIng = false;
//		for (int[] interval : intervals) {
//
//
//			if (!checkAgain) {
//				resultAry[countNum++] = interval;
//				continue;
//			}
//			int start = interval[0];
//			int stop = interval[1];
//
//			//1、在间隔左侧
//			if (heightNum < start) {
//				//不在边界上
//				//之前有包含  就 结束
//				if (inIng) {
//					thisEntry[1] = heightNum;
//					resultAry[countNum++] = thisEntry;
//					checkAgain = false;
//				}
//				resultAry[countNum++] = interval;
//				//在边界上
//			continue;
//			}
//
//			//2、再两个间隔中间
//			if (stop < shortNum) {
//				resultAry[countNum++] = interval;
//				continue;
//			}
//			//3、在间隔内部
//			if (start <= shortNum && stop >= heightNum) {
//				resultAry[countNum++] = interval;
//				checkAgain = false;
//				continue;
//			}
//
//			//4、在多个间隔内部
//			if (start <= shortNum || stop >= heightNum) {
//				thisEntry[0] = start;
//				inIng = true;
//			}
//		}
//
//		return Arrays.copyOf(resultAry, countNum);
//	}


	public int[][] insert0(int[][] intervals, int[] newInterval) {
		int left = newInterval[0];
		int right = newInterval[1];
		boolean placed = false;
		List<int[]> ansList = new ArrayList<int[]>();
		for (int[] interval : intervals) {
			if (interval[0] > right) {
				// 在插入区间的右侧且无交集
				if (!placed) {
					ansList.add(new int[]{left, right});
					placed = true;
				}
				ansList.add(interval);
			} else if (interval[1] < left) {
				// 在插入区间的左侧且无交集
				ansList.add(interval);
			} else {
				// 与插入区间有交集，计算它们的并集
				left = Math.min(left, interval[0]);
				right = Math.max(right, interval[1]);
			}
		}
		if (!placed) {
			ansList.add(new int[]{left, right});
		}
		int[][] ans = new int[ansList.size()][2];
		for (int i = 0; i < ansList.size(); ++i) {
			ans[i] = ansList.get(i);
		}
		return ans;
	}
}