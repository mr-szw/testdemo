package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 530. 二叉搜索树的最小绝对差
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 *
 * @author sinbad on 2020/10/12.
 */
public class Question530 {


	//任意两点
	//[236,104,701, null,227,null,911]
	public static void main(String[] args) {

		TreeNode treeNode5 = new TreeNode(5);
		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode7 = new TreeNode(7);
		treeNode5.left = treeNode4;
		treeNode5.right = treeNode7;


//		TreeNode treeNode236 = new TreeNode(10);
//		TreeNode treeNode104 = new TreeNode(5);
//		TreeNode treeNode701 = new TreeNode(18);
//		TreeNode treeNode7021 = new TreeNode(2);
//		TreeNode treeNode706 = new TreeNode(6);
//		TreeNode treeNode708 = new TreeNode(8);
//
//
//		treeNode236.left = treeNode104;
//		treeNode236.right = treeNode701;
//
//		treeNode104.left = treeNode7021;
//		treeNode104.right = treeNode706;
//		treeNode706.right = treeNode708;

		System.out.println(new Gson().toJson(new Question530().getMinimumDifference(treeNode5)));
	}

	public int getMinimumDifference(TreeNode root) {
		List<Integer> pathList = new ArrayList<>();
		getMinimumDifference(root, pathList);

		int result = Integer.MAX_VALUE;
		int size = pathList.size();
		for (int i = size - 1; i > 0; i--) {
			result = Math.min(result, pathList.get(i) - pathList.get(i - 1));
		}
		return result;
	}

	public void getMinimumDifference(TreeNode root, List<Integer> pathList) {
		//二叉树遍历 中序遍历
		if (root == null) {
			return;
		}
		getMinimumDifference(root.left, pathList);
		pathList.add(root.val);
		getMinimumDifference(root.right, pathList);
	}
}
