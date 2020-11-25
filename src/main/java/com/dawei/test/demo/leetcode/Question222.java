package com.dawei.test.demo.leetcode;

/**
 * 222. 完全二叉树的节点个数 https://leetcode-cn.com/problems/count-complete-tree-nodes/
 *
 * @author sinbad on 2020/11/24. 好难理解啊
 */
public class Question222 {

	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode(1);
		TreeNode treeNodeLeft = new TreeNode(2);
		treeNode.left = treeNodeLeft;
		System.out.println(new Question222().countNodes(treeNode));
	}

	// 基础暴力法
	public int countNodesBase(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return countNodes(root.left) + countNodes(root.right) + 1;
	}

	// 找那层少的
	public int countNodesLevel(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftLevel = getLeftDepth(root.left);
		int rightLevel = getRightDepth(root.right);

		return 0;

	}

	private int getLeftDepth(TreeNode root) {
		int depth = 0;
		while (root != null) {
			root = root.left;
			depth++;
		}
		return depth;
	}

	private int getRightDepth(TreeNode root) {
		int depth = 0;
		while (root != null) {
			root = root.right;
			depth++;
		}
		return depth;
	}

	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = countLevel(root.left);
		int right = countLevel(root.right);
		if (left == right) {
			return countNodes(root.right) + (1 << left);
		} else {
			return countNodes(root.left) + (1 << right);
		}
	}

	private int countLevel(TreeNode root) {
		int level = 0;
		while (root != null) {
			level++;
			root = root.left;
		}
		return level;
	}

}
