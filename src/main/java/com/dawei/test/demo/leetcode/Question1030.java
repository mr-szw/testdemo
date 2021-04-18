package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.dawei.test.demo.utils.GsonUtil;

/**
 * 1030. 距离顺序排列矩阵单元格
 * https://leetcode-cn.com/problems/matrix-cells-in-distance-order/
 *
 * @author sinbad on 2020/11/17.
 */
public class Question1030 {


	public static void main(String[] args) {

		System.out.println(GsonUtil.toJson(new Question1030().allCellsDistOrder(2, 3, 1, 2)));
	}

	//暴力 获取所有数据 然后 排序 获取记录
	public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
		Map<Integer, List<int[]>> mapList = new HashMap<>();
		Set<Integer> countNumSet = new TreeSet<>();
		int[][] resultAry = new int[R * C][2];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int abs = Math.abs(j - c0) + Math.abs(i - r0);
				List<int[]> arryList;
				if (countNumSet.add(abs)) {
					arryList = new ArrayList<>();
					mapList.put(abs, arryList);
				} else {
					arryList = mapList.get(abs);
				}
				arryList.add(new int[]{i, j});
			}
		}
		List<int[]> allRecordList = new ArrayList<>();
		for (Integer integer : countNumSet) {
			allRecordList.addAll(mapList.get(integer));
		}
		allRecordList.toArray(resultAry);
		return resultAry;
	}

//	//暴力 获取所有数据 然后 排序 获取记录
//	public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
//		Map<Integer, List<int[]>> mapList = new HashMap<>();
//		Set<Integer> countNumSet = new TreeSet<>();
//		int[][] resultAry = new int[R * C][2];
//
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				int abs = Math.abs(j - c0) + Math.abs(i - r0);
//				List<int[]> arryList;
//				if (countNumSet.add(abs)) {
//					arryList = new ArrayList<>();
//					mapList.put(abs, arryList);
//				} else {
//					arryList = mapList.get(abs);
//				}
//				arryList.add(new int[]{i, j});
//			}
//		}
//		List<int[]> allRecordList = new ArrayList<>();
//		for (Integer integer : countNumSet) {
//			allRecordList.addAll(mapList.get(integer));
//		}
//		allRecordList.toArray(resultAry);
//		return resultAry;
//	}

}
