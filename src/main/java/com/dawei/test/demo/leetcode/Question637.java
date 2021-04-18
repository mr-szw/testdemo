package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.checkerframework.checker.units.qual.A;

import com.google.gson.Gson;

/**
 * 637. 二叉树的层平均值
 * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
 *
 * @author sinbad on 2020/09/12.
 * <p>
 * 目前处理结果 仅能返回一次的计算结果
 */
public class Question637 {


	public static void main(String[] args) {

		TreeNode treeNode = new TreeNode(3);
		TreeNode treeNode1 = new TreeNode(1);
		TreeNode treeNode5 = new TreeNode(5);

		TreeNode treeNode0 = new TreeNode(0);
		TreeNode treeNode2 = new TreeNode(2);

		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode6 = new TreeNode(6);

		treeNode.left = treeNode1;
		treeNode.right = treeNode5;

		treeNode1.left = treeNode0;
		treeNode1.right = treeNode2;


		treeNode5.left = treeNode4;
		treeNode5.right = treeNode6;

		System.out.println(new Gson().toJson(new Question637().averageOfLevels(treeNode)));
	}


	public List<Double> averageOfLevels(TreeNode root) {

		List<Double> resultList = new ArrayList<>();
		List<TreeNode> nowTree = new ArrayList<>();
		nowTree.add(root);
		int curLen = 1;
		int nextLen = 0;
		double sum = 0.0D;
		int index = 0;

		while (curLen > 0) {
			int tempLen = curLen;
			while (tempLen > 0) {
				TreeNode pop = nowTree.get(index++);
				tempLen--;
				sum = sum + pop.val;
				if (pop.left != null) {
					nowTree.add(pop.left);
					nextLen++;
				}
				if (pop.right != null) {
					nowTree.add(pop.right);
					nextLen++;
				}
			}
			resultList.add(sum / curLen);
			sum = 0;
			curLen = nextLen;
			nextLen = 0;
		}

		return resultList;
	}


//	public List<Double> averageOfLevels(TreeNode root) {
//		List<Double> resultList = new ArrayList<>();
//		Map<Integer, Integer> markMap = new HashMap<>();
//		averageOfLevels(resultList, root, 0, markMap);
//		for (int i = 0; i < resultList.size(); i++) {
//			resultList.set(i, resultList.get(i) / markMap.get(i));
//		}
//		return resultList;
//	}
//
//	public void averageOfLevels(List<Double> resultList, TreeNode root, int deep, Map<Integer, Integer> markMap) {
//		if (root == null) {
//			return;
//		}
//		int val = root.val;
//
//		if (markMap.get(deep) == null) {
//			resultList.add(deep, (double) val);
//			markMap.put(deep, 1);
//		} else {
//			resultList.set(deep, (resultList.get(deep) + val) );
//			markMap.put(deep, markMap.get(deep) + 1);
//		}
//		if (root.left != null) {
//			averageOfLevels(resultList, root.left, deep + 1, markMap);
//		}
//		if (root.right != null) {
//			averageOfLevels(resultList, root.right, deep + 1, markMap);
//		}
//	}


}
