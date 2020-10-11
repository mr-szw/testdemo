package com.dawei.test.demo.leetcode;


import com.google.gson.Gson;

/**
 * 142. 环形链表II
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * @author sinbad on 2020/10/11.
 */
public class Question142 {


	public static void main(String[] args) {

		ListNode listNode3 = new ListNode(3);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode0 = new ListNode(0);
		ListNode listNode4 = new ListNode(-4);
		System.out.println(new Gson().toJson(new Question142().detectCycle(listNode3)));
	}

	public ListNode detectCycle(ListNode head) {
		//
		//起点到环起始点 距离 a
		//环起始点到相遇点 距离 b
		//相遇点到环起始点 距离 c
		//慢 步长是 a + b
		//快 步长是 a + （b + c）* n + b
		//关系： 2 * （a + b) = a + （b + c）* n + b    n > 0
		//简化
		// 1）、n=1  a = c
		// 2)、 n>1  a = (n -1)（b + c） + c

		ListNode slowNode = head;
		ListNode fastNode = head;
		while (slowNode != null && fastNode.next != null && fastNode.next.next != null) {
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
			//此时有环
			if (slowNode == fastNode) {
				//找起始点
				slowNode = head;
				while (slowNode != fastNode) {
					slowNode = slowNode.next;
					fastNode = fastNode.next;
				}
				return slowNode;
			}

		}
		return null;


	}
}

