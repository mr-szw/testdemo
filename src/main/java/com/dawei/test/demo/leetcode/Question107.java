package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.gson.Gson;

/**
 * 二叉树的层次遍历II
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question107 {


	public static void main(String[] args) {

		TreeNode root = new TreeNode(1);
		TreeNode root2 = new TreeNode(2);
		TreeNode root3 = new TreeNode(3);
		TreeNode root4 = new TreeNode(4);
		TreeNode root7 = new TreeNode(7);
		TreeNode root5 = new TreeNode(5);
		TreeNode root6 = new TreeNode(6);
		TreeNode root8 = new TreeNode(8);


		root.left = root2;
		root.right = root3;
		root2.right = root4;
		root3.left = root7;
		root4.left = root5;
		root4.right = root6;
		root7.right = root8;

		System.out.println(new Gson().toJson(new

				Question107().

				levelOrderBottom(root)));

	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {

		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> rootQueue = new LinkedList<>();
		rootQueue.add(root);
		int count = 1;
		while (count > 0) {
			int nextLevelCount = 0;
			List<Integer> levelNodeVal = new ArrayList<>();
			while (count-- > 0) {
				TreeNode treeNode = rootQueue.poll();
				levelNodeVal.add(treeNode.val);
				if (treeNode.left != null) {
					rootQueue.add(treeNode.left);
					nextLevelCount++;
				}
				if (treeNode.right != null) {
					rootQueue.add(treeNode.right);
					nextLevelCount++;
				}
			}

			if (levelNodeVal.size() > 0) {
				result.add(0, levelNodeVal);
			}
			count = nextLevelCount;
		}
		return result;
}


static class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

}