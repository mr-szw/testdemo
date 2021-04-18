package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 二叉树的所有路径
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * @author sinbad on 2020/09/03.
 * <p>
 */
public class Question144 {


	public static void main(String[] args) {

		TreeNode treeNode = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode4 = new TreeNode(5);
		treeNode.left = treeNode2;
		treeNode.right = treeNode3;
		treeNode2.right = treeNode4;


		System.out.println(new Gson().toJson(new Question144().preorderTraversal(treeNode)));
	}

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> resultList = new ArrayList<>();

		Stack<TreeNode> treeNodeStack = new Stack<>();


		while (!treeNodeStack.isEmpty() || root != null) {
			if (root == null) {
				root = treeNodeStack.pop();
			}
			resultList.add(root.val);
			if (root.right != null) {
				treeNodeStack.add(root.right);
			}
			root = root.left;
		}
		return resultList;
	}
}

