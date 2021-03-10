package com.dawei.test.demo.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author sinbad on 2021/2/26.
 */
public class DoubleTree {

	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode5 = new TreeNode(5);
		TreeNode treeNode6 = new TreeNode(6);


		treeNode.left = treeNode2;
		treeNode2.right = treeNode3;
		treeNode3.left = treeNode4;
		treeNode3.right = treeNode5;
		treeNode4.right = treeNode6;

		new DoubleTree().firstRootRange1(treeNode);

	}

	/**
	 * 	二叉树遍历
	 * 	先序遍历 --- 先根遍历
	 * 	中序遍历 --- 中根遍历
	 * 	后序遍历 --- 后根遍历
	 */

	//递归
	private void firstRootRange1(TreeNode root) {
		if (root == null) {
			return;
		}
		List<Integer> pathList = new ArrayList<>();

//		firstRootRange(root, pathList);
//		pathList.forEach(System.out::println);
//
//		System.out.println( "    --------    ");
//		pathList = new ArrayList<>();
//		firstRootRange(root, pathList);
//		pathList.forEach(System.out::println);

		TreeNode nodeLast = root;
		TreeNode nodeLastNO = root;

		System.out.println( "    --------    ");
		pathList = new ArrayList<>();
		lostRootRang(nodeLast, pathList);

		pathList.forEach(System.out::println);

		System.out.println( "    --------    ");
		pathList = new ArrayList<>();
		lostRootRang(nodeLastNO, pathList);
		pathList.forEach(System.out::println);

		System.out.println( "    --------    ");
		pathList = new ArrayList<>();
	}
	private void firstRootRange(TreeNode root,List<Integer> pathList) {
		if (root == null) {
			return;
		}
		pathList.add(root.val);
		firstRootRange(root.left, pathList);
		firstRootRange(root.right, pathList);
	}


	/**
	 * 非递归
		先根 入队打印
	 */
	private void firstRootRangeNo(TreeNode root, List<Integer> pathList) {
		if (root == null) {
			return;
		}
		//进栈就打印 栈内的是打印过的
		Stack<TreeNode> treeNodeStack = new Stack<>();
		treeNodeStack.add(root);
		while (root.left != null) {
			pathList.add(root.left.val);
			root = root.left;
			treeNodeStack.add(root);
		}
		while (!treeNodeStack.isEmpty()) {
			TreeNode pop = treeNodeStack.pop();
			if (pop.right != null) {
				treeNodeStack.push(pop.right);
				pop = pop.right;
				while (pop != null) {
					pathList.add(pop.left.val);
					pop = root.left;
					treeNodeStack.add(pop);
				}
			}
		}
	}


	/**
	 * 中序遍历
	 * 递归
	 */
	private void minRootRange(TreeNode root, List<Integer> pathList) {
		if (root == null) {
			return;
		}


		minRootRange(root.left, pathList);

		pathList.add(root.val);

		minRootRange(root.right, pathList);

	}


	/**
	 * 中序遍历 出队打印
	 * 非递归
	 */
	private void minRootRangeNo(TreeNode root, List<Integer> pathList) {
		if (root == null) {
			return;
		}

		Stack<TreeNode> treeNodeStack = new Stack<>();
		while (root.left != null) {
			root = root.left;
			treeNodeStack.add(root);
		}

		while (!treeNodeStack.isEmpty()) {
			TreeNode pop = treeNodeStack.pop();
			pathList.add(pop.val);

			if (root.right != null) {
				TreeNode temp = root.right;
				while (temp != null) {
					treeNodeStack.add(temp);
					temp = temp.left;
				}
			}
		}
	}


	/**
	 * 后序遍历 左右根
	 */
	private void  lostRootRang(TreeNode root, List<Integer> pathList) {

		if (root == null) {
			return;
		}

		lostRootRang(root.left, pathList);
		lostRootRang(root.right, pathList);

		pathList.add(root.val);
	}



	/**
	 * 后序遍历 左右根
	 *	非递归
	 */
	private void  lostRootRangNo(TreeNode root, List<Integer> pathList) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> treeNodeStack = new Stack<>();
		while (root.left != null) {
			treeNodeStack.push(root.left);
			root = root.left;
		}
		TreeNode lastDoNode = null;
		while (!treeNodeStack.isEmpty()) {

			TreeNode pop = treeNodeStack.pop();
			if (pop.right != null && lastDoNode != pop.right) {
				treeNodeStack.push(pop);
				TreeNode treeNode = pop.right;
				while (pop != null) {
					treeNodeStack.push(pop);
					pop = pop.left;
				}
			} else {
				pathList.add(pop.val);
				lastDoNode = pop;
			}
		}
		lostRootRang(root.left, pathList);
		lostRootRang(root.right, pathList);
		pathList.add(root.val);
	}




	//跟节点先入队

	//二叉树深度遍历
	private void getPath( TreeNode root, List<List<Integer>> markPathList, Stack<Integer> pathList) {
		if (root.left == null && root.right == null) {
			markPathList.add(new ArrayList<>(pathList));
			return;
		}

		if(root.left != null) {
			pathList.add(root.left.val);
			getPath(root.left, markPathList, pathList);
			pathList.pop();
		}

		if(root.right != null) {
			pathList.add(root.right.val);
			getPath(root.right, markPathList, pathList);
			pathList.pop();
		}

	}


	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }

	}

}
