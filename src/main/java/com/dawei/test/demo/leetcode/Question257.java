package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.dawei.test.demo.stream.StreamDemo;
import com.google.gson.Gson;

/**
 * 二叉树的所有路径
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * @author sinbad on 2020/09/03.
 * <p>
 */
public class Question257 {


	public static void main(String[] args) {

		TreeNode treeNode = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode4 = new TreeNode(5);
		treeNode.left = treeNode2;
		treeNode.right = treeNode3;
		treeNode2.right = treeNode4;


		System.out.println(new Gson().toJson(new Question257().binaryTreePaths(treeNode)));
	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		List<String> subResult = new ArrayList<>();
		Stack<TreeNode> treeStack = new Stack<>();

		if (root != null) {
			getData(result, subResult, root, treeStack);
		}
		return result;
	}


	public void getData(List<String> stringList, List<String> subResult, TreeNode root, Stack<TreeNode> treeStack) {
		while (root != null) {
			subResult.add(String.valueOf(root.val));
			treeStack.add(root);
			if (root.left != null) {
				getData(stringList, subResult, root.left, treeStack);
			}
			if (root.right != null) {
				getData(stringList, subResult, root.right, treeStack);
			}
			if (!treeStack.empty()) {
				root = treeStack.pop();
			} else {
				stringList.add(String.join(",", stringList));
				break;
			}
		}
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
