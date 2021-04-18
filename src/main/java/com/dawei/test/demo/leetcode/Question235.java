package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.google.gson.Gson;

/**
 * 235. 二叉搜索树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * @author sinbad on 2020/09/27.
 */
public class Question235 {


	public static void main(String[] args) {
		TreeNode treeNode6 = new TreeNode(6);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode8 = new TreeNode(8);
		TreeNode treeNode0 = new TreeNode(0);
		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode7 = new TreeNode(7);
		TreeNode treeNode9 = new TreeNode(9);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode5 = new TreeNode(5);

		treeNode6.left = treeNode2;
		treeNode6.right = treeNode8;
		treeNode2.left = treeNode0;
		treeNode2.right = treeNode4;
		treeNode4.left = treeNode3;
		treeNode4.right = treeNode5;

		treeNode8.left = treeNode7;
		treeNode8.right = treeNode9;


		TreeNode treeNode_t_2 = new TreeNode(2);
		TreeNode treeNode_t_8 = new TreeNode(8);
		TreeNode treeNode_t_4 = new TreeNode(4);

		System.out.println(new Gson().toJson(new Question235().lowestCommonAncestor(treeNode6, treeNode_t_2, treeNode_t_4)));
	}


	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p.val <= q.val) {
			return findTreeFork(root, p.val, q.val);
		} else {
			return findTreeFork(root, q.val, p.val);
		}
	}

//
//		Queue<Integer> onePath = new LinkedList<>();
//		Queue<Integer> nextPath = new LinkedList<>();
//		// 遍历搜索二叉树记录path
//		findPath(root, p.val, onePath);
//		findPath(root, q.val, nextPath);
//
//		TreeNode treeNode = new TreeNode(0);
//		Iterator<Integer> iterator = onePath.iterator();
//		while (iterator.hasNext() && !nextPath.isEmpty()) {
//
//			Integer poll = nextPath.poll();
//			if (iterator.next().equals(poll)) {
//				treeNode.val = poll;
//			}
//		}
	//return treeNode;
//	}

	public void findPath(TreeNode root, int target, Queue<Integer> path) {
		if (root == null) {
			return;
		}
		path.add(root.val);
		if (root.val == target) {
			return;
		}

		if (root.val > target) {
			findPath(root.left, target, path);
		} else {
			findPath(root.right, target, path);
		}

	}


	//找树叉
	public TreeNode findTreeFork(TreeNode treeNode, int targetMin, int targetMax) {
		if (treeNode == null) {
			return null;
		}
		Integer val = treeNode.val;
		if (val >= targetMin && val <= targetMax) {
			return new TreeNode(val);
		}
		if (val > targetMax) {
			return findTreeFork(treeNode.left, targetMin, targetMax);
		} else {
			return findTreeFork(treeNode.right, targetMin, targetMax);
		}
	}


	//找树叉
	public TreeNode findTreeFork(TreeNode treeNode, TreeNode targetMin, TreeNode targetMax) {
		if (treeNode == null) {
			return null;
		}
		Integer min = targetMax.val;
		Integer max = targetMin.val;

		if (min > max) {
			min = min ^ max;
			max = min ^ max;
			min = min ^ max;
		}

		while (true) {
			Integer val = treeNode.val;
			if (val >= min && val <= max) {
				return new TreeNode(val);
			}
			if (val > max) {
				treeNode = treeNode.left;
			} else {
				treeNode = treeNode.right;

			}
		}
	}



//	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//		if (root == null) {
//			return null;
//		}
//		Integer min = p.val;
//		Integer max = q.val;
//
//		if (min > max) {
//			min = min ^ max;
//			max = min ^ max;
//			min = min ^ max;
//		}
//		while (true) {
//			Integer val = root.val;
//
//			if (val > max) {
//				root = root.left;
//			} else if(val < min) {
//				root = root.right;
//			}else {
//				return new TreeNode(val);
//			}
//		}
//	}
}
