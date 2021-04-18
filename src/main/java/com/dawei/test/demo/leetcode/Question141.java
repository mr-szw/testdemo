package com.dawei.test.demo.leetcode;


import com.google.gson.Gson;

/**
 * 141. 环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question141 {


	public static void main(String[] args) {

		ListNode listNode3 = new ListNode(3);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode0 = new ListNode(0);
		ListNode listNode4 = new ListNode(-4);
		System.out.println(new Gson().toJson(new Question141().hasCycle(null)));
	}

	public boolean hasCycle(ListNode head) {
		//1、双指针追逐
		//2、set去重方式 空间不对
		ListNode slowNode = head;
		ListNode fastNode = head;
		while (slowNode != null && fastNode.next != null && fastNode.next.next != null) {
			fastNode = fastNode.next.next;
			if (slowNode == fastNode) {
				return true;
			}
			slowNode = slowNode.next;
		}
		return false;
	}
}

