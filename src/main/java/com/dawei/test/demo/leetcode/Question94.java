package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.checkerframework.checker.units.qual.A;

/**
 * 最短回文串
 * https://leetcode-cn.com/problems/shortest-palindrome/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question94 {


	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(3);

		treeNode.right = treeNode2;
		treeNode2.left = treeNode3;
		System.out.println(new Question94().inorderTraversal(treeNode));
	}

	public List<Integer> inorderTraversal(TreeNode root) {

		List<Integer> resultList = new ArrayList<>();
		Stack<TreeNode> pathQueue= new Stack<>();
		TreeNode node = root;
		while (node != null || !pathQueue.isEmpty()) {
			while (node != null) {
				pathQueue.push(node);
				node = node.left;
			}
			node = pathQueue.pop();
			resultList.add(node.val);
			if (node.right != null) {
				node = node.right;
			} else {
				node = null;
			}
		}
		return resultList;
	}





//	public List<Integer> inorderTraversal(TreeNode root) {
//		List<Integer> resultList = new ArrayList<>();
//		inorderTraversal(resultList, root);
//		return resultList;
//	}
//
//	public void inorderTraversal(List<Integer> resultList, TreeNode root) {
//		if (root == null) {
//			return;
//		}
//		if (root.left != null) {
//			inorderTraversal(resultList, root.left);
//		}
//		resultList.add(root.val);
//		if (root.right != null) {
//			inorderTraversal(resultList, root.right);
//		}
//	}


}
