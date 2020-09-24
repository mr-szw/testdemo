package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

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
		treeNode.right = treeNode2;
		treeNode2.right = treeNode2_2;
		System.out.println(new Gson().toJson(new Question501().findMode(treeNode)));
	}


	public int[] findMode(TreeNode root) {

		int val = root.val;
		if (root.left != null) {
			if (root.left.val == val) {

			}
		}

		Map<Integer, Integer> markMap = new HashMap<>();
		findMode(markMap, root);



	}

	public void findMode(Map<Integer, Integer> markMap, TreeNode root) {
		if (root == null) {
			return;
		}

		int val = root.val;
		int orDefault = markMap.getOrDefault(val, 0);
		if (orDefault == 0) {

		}
		if ()
	}

}
