package com.dawei.test.demo.leetcode;

import com.google.gson.Gson;

/**
 * 617. 合并二叉树
 * https://leetcode-cn.com/problems/merge-two-binary-trees/
 *
 * @author sinbad on 2020/09/21.
 */
public class Question617 {


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
		System.out.println(new Gson().toJson(new Question617().mergeTrees(treeNode, treeNode)));
	}


	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

		return mergeTrees(null, t1, t2, null);
	}

	public TreeNode mergeTrees(TreeNode treeNode, TreeNode t1, TreeNode t2, Boolean left) {
		//如何遍历二叉树
		//先跟遍历
		if (t1 == null && t2 == null) {
			return treeNode;
		}
		int sum = 0;
		sum += t1 != null ? t1.val : 0;
		sum += t2 != null ? t2.val : 0;
		TreeNode thisNode = new TreeNode(sum);
		if (left == null) {
			treeNode = thisNode;
		} else {
			if (left) {
				treeNode.left = thisNode;
			} else {
				treeNode.right = thisNode;
			}
		}
		mergeTrees(thisNode, t1 == null ? null : t1.left, t2 == null ? null : t2.left, true);
		mergeTrees(thisNode, t1 == null ? null : t1.right, t2 == null ? null : t2.right, false);
		return treeNode;
	}


}
