package com.dawei.test.demo.leetcode;

import com.google.gson.Gson;

/**
 * 968. 监控二叉树
 * https://leetcode-cn.com/problems/binary-tree-cameras/
 *
 * @author sinbad on 2020/09/21.
 */
public class Question968 {


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
		System.out.println(new Gson().toJson(new Question968().minCameraCover(treeNode)));
	}



	public int minCameraCover(TreeNode root) {

		return 0;
	}
}
