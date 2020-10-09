package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.checkerframework.checker.units.qual.A;

import com.google.gson.Gson;

/**
 * 501. 二叉搜索树中的众数
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
 *
 * @author sinbad on 2020/09/24.
 */
public class Question501 {


	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode2_2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(3);


		treeNode.right = treeNode2;
		treeNode.left = treeNode3;
		treeNode2.right = treeNode2_2;
		System.out.println(new Gson().toJson(new Question501().findMode(treeNode)));
	}


	public int[] findMode(TreeNode root) {
		Map<Integer, Integer> markMap = new HashMap<>();
		Stack<Integer> resultStack = new Stack<>();
		findMode(markMap, root);
		int maxCount = 0;
		Set<Integer> keySet = markMap.keySet();
		for (Integer key : keySet) {
			int count
					= markMap.get(key);
			if (count < 2) {
				continue;
			}
			if (count > maxCount) {
				maxCount = count;
				resultStack.clear();
				resultStack.push(key);
			} else if (count == maxCount) {
				resultStack.push(key);
			}
		}
		int[] result =  resultStack.stream().mapToInt(Integer::valueOf).toArray();


		return result;
	}
	public void findMode(Map<Integer, Integer> markMap, TreeNode root) {

		if(root == null) {
			return;
		}

		//遍历个二叉树 记录值
		findMode(markMap, root.left);
		int val = root.val;
		Integer orDefault = markMap.getOrDefault(val, 0);
		markMap.put(val, orDefault + 1);
		System.out.println(val);
		findMode(markMap, root.right);
	}




//	if (root.left != null) {
//		if (root.left.val == val) {
//
//		}
//	}
//
//	Stack<Integer> markList = new Stack<>();
//	findMode(root, markList, 0, 0);

	public void findMode(TreeNode root, Stack<Integer> markStack, int countAfterNum, int countNextNum) {
		if (root == null) {
			return;
		}

		int val = root.val;
		if (markStack.empty()) {
			markStack.push(val);
			countAfterNum = 1;
		} else {
			Integer pop = markStack.pop();
			if (val == pop) {
				countAfterNum++;
			} else {
				countNextNum++;
			}
		}
	}

}
