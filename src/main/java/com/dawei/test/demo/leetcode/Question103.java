package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Single;

/**
 * 103. 二叉树的锯齿形层序遍历
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * @author sinbad on 2020/12/22.
 */
public class Question103 {


	public static void main(String[] args) {

		//3,9,20,null,null,15,7],
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode9 = new TreeNode(9);
		TreeNode treeNode20 = new TreeNode(20);
		TreeNode treeNode15 = new TreeNode(15);
		TreeNode treeNode7 = new TreeNode(7);

		treeNode3.left = treeNode9;
		treeNode3.right = treeNode20;
		treeNode20.left = treeNode15;
		treeNode20.right = treeNode7;
		System.out.println(new Question103().zigzagLevelOrder(treeNode3));
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		//层序遍历
		if (root == null) {
			return Collections.emptyList();
		}
		List<TreeNode> fatherList = new ArrayList<>();
		fatherList.add(root);
		List<List<Integer>> resultList = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		List<TreeNode> childrenList = new ArrayList<>();
		boolean leftToRight = true;

		while (!fatherList.isEmpty()) {
			for (TreeNode treeNode : fatherList) {
				if (treeNode.left != null) {
					childrenList.add(treeNode.left);
				}
				if (treeNode.right != null) {
					childrenList.add(treeNode.right);
				}
				tempList.add(treeNode.val);
			}
			if (!leftToRight) {
				Collections.reverse(tempList);
				leftToRight = true;
			} else {
				leftToRight = false;
			}
			resultList.add(new ArrayList<>(tempList));
			tempList.clear();

			fatherList = new ArrayList<>(childrenList);
			childrenList.clear();;
		}
		return resultList;
	}
}
