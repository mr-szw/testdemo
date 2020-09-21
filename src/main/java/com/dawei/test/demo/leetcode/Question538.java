package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * 538. 把二叉搜索树转换为累加树
 * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
 *
 * @author sinbad on 2020/09/21.
 */
public class Question538 {


	public static void main(String[] args) {

		TreeNode treeNode = new TreeNode(2);
		TreeNode treeNode0 = new TreeNode(0);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode_4 = new TreeNode(-4);
		TreeNode treeNode1 = new TreeNode(1);
		treeNode.left = treeNode0;
		treeNode.right = treeNode3;
		treeNode0.left = treeNode_4;
		treeNode0.right = treeNode1;
		System.out.println(new Gson().toJson(new Question538().convertBST(treeNode)));
	}
 int sum = 0;
	public TreeNode convertBST(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode right = root.right;
		if (right != null) {
			sum = right.val + root.val;
			convertBST(right);
			root.val = sum;
		}
		TreeNode left = root.left;
		if (left != null) {
			left.val = left.val + root.val;
			convertBST(left);
		}
		return root;
	}
}
