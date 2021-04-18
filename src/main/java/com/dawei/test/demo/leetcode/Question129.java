package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.dawei.test.demo.utils.GsonUtil;

/**
 * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 * 129. 求根到叶子节点数字之和
 *
 * @author sinbad on 2020/10/29.
 */
public class Question129 {


	public static void main(String[] args) {

		TreeNode treeNode = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode4 = new TreeNode(4);
		treeNode.left = treeNode2;
		treeNode.right = treeNode3;
		treeNode2.right = treeNode4;

		System.out.println(GsonUtil.toJson(new Question129().sumNumbers(treeNode)));

	}


	public int sumNumbers(TreeNode root) {
		//深度遍历并记住历史
		List<Integer> resultList = new ArrayList<>();
		return sumNumbers(root, 0);
	}

	public int sumNumbers(TreeNode root, int hasNum) {
		if (root == null) {
			return hasNum;
		}
		int val = root.val;
		hasNum = hasNum * 10 + val;
		if (root.left == null && root.right == null) {
			return hasNum;
		} else {
			hasNum = sumNumbers(root.left, hasNum);
			hasNum = sumNumbers(root.right, hasNum);
		}
		return hasNum;
	}


}
