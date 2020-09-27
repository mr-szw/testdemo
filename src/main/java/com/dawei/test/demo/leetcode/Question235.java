package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.google.gson.Gson;

/**
 * 235. 二叉搜索树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * @author sinbad on 2020/09/27.
 */
public class Question235 {


	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode2_2 = new TreeNode(2);

		System.out.println(new Gson().toJson(new Question235().lowestCommonAncestor(treeNode, treeNode2, treeNode2_2)));
	}


	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		Queue<Integer> oneTree = new LinkedList<>();
		Queue<Integer> nextTree = new LinkedList<>();



	}

}
