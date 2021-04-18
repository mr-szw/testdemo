package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 19. 删除链表的倒数第N个节点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * @author sinbad on 2020/09/18.
 * <p>
 */
public class Question437 {


	public static void main(String[] args) {

		//[10,5,-3,3,2,null,11,3,-2,null,1]
		TreeNode node10 = new TreeNode(10);
		TreeNode node5 = new TreeNode(5);
		TreeNode node3_ = new TreeNode(-3);
		TreeNode node3 = new TreeNode(3);
		TreeNode node3_3 = new TreeNode(3);
		TreeNode node2 = new TreeNode(2);
		TreeNode node11 = new TreeNode(11);
		TreeNode node2_ = new TreeNode(-2);
		TreeNode node1 = new TreeNode(1);
		node10.left = node5;
		node10.right = node3_;
		node5.left = node3;
		node5.right = node2;
		node3.left = node3_3;
		node3.right = node2_;
		node2.right = node1;
		node3_.right = node11;

		ListNode nodeOne = new ListNode(0);
		//System.out.println(new Question19().removeNthFromEnd(node, 3));
		System.out.println(new Question437().pathSum(node10, 8));
	}




	public int pathSum(TreeNode root, int sum) {

		if (root == null) {
			return 0;
		}
		//记录所有的路径
		List<List<Integer>> markPathList = new ArrayList<>();
		Stack<Integer> pathStack = new Stack<>();
		pathStack.push(root.val);
		getPath(root, markPathList, pathStack);

		int count = 0;

		for (List<Integer> pathList : markPathList) {
			pathList.forEach(System.out::println);
			count += getCountTargetForList(pathList, sum);
			System.out.println("-------------");
		}

		return count;
	}

	private void getPath(TreeNode root, List<List<Integer>> markPathList, Stack<Integer> pathList) {
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

	private int getCountTargetForList(List<Integer> pathList, int target) {
		List<List<Integer>> resultList = new ArrayList<>();
		for (int i = 0; i < pathList.size(); i++) {
			getCountTargetForList(pathList, target, i, resultList, new Stack<>());
		}
		return resultList.size();
	}

	private void getCountTargetForList(List<Integer> numList, int target, int index, List<List<Integer>> resultList,
									   Stack<Integer> pathList) {
		if (index == numList.size()) {
			return;
		}

		if (target == 0) {
			resultList.add(pathList);
		}
		int num = numList.get(index);
		pathList.push(num);
		getCountTargetForList(numList, target - num, index + 1, resultList, pathList);
	}







	public int pathSumOffice(TreeNode root, int sum) {
		// key是前缀和, value是大小为key的前缀和出现的次数
		Map<Integer, Integer> prefixSumCount = new HashMap<>();
		// 前缀和为0的一条路径
		prefixSumCount.put(0, 1);
		// 前缀和的递归回溯思路
		return recursionPathSum(root, prefixSumCount, sum, 0);
	}

	/**
	 * 前缀和的递归回溯思路
	 * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
	 * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
	 * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
	 * @param node 树节点
	 * @param prefixSumCount 前缀和Map
	 * @param target 目标值
	 * @param currSum 当前路径和
	 * @return 满足题意的解
	 */
	private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
		// 1.递归终止条件
		if (node == null) {
			return 0;
		}
		// 2.本层要做的事情
		int res = 0;
		// 当前路径上的和
		currSum += node.val;

		//---核心代码
		// 更新路径上当前节点前缀和的个数
		prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);

		// 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
		// 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
		// currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
		res = res +  prefixSumCount.getOrDefault(currSum - target, 0);

		//---核心代码

		// 3.进入下一层
		res += recursionPathSum(node.left, prefixSumCount, target, currSum);
		res += recursionPathSum(node.right, prefixSumCount, target, currSum);

		// 4.回到本层，恢复状态，去除当前节点的前缀和数量
		prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
		return res;
	}

}
