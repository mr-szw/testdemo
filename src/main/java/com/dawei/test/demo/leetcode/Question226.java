package com.dawei.test.demo.leetcode;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * 226. 翻转二叉树
 * @author sinbad on 2020/09/16.
 */
public class Question226 {

	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode(4);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode7 = new TreeNode(7);
		TreeNode treeNode1 = new TreeNode(1);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode6 = new TreeNode(6);
		TreeNode treeNode9 = new TreeNode(9);

		treeNode.left = treeNode2;
		treeNode.right = treeNode7;

		treeNode2.left = treeNode1;
		treeNode2.right = treeNode3;

		treeNode7.left = treeNode6;
		treeNode7.right = treeNode9;

		new Question226().invertTree(treeNode);

		System.out.println("");

	}

	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode right = root.right;
		TreeNode left = root.left;
		invertTree(right);
		invertTree(left);
		root.left = right;
		root.right = left;
		return root;
	}
}
