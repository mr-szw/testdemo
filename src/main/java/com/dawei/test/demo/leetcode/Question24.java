package com.dawei.test.demo.leetcode;

/**
 * 24. 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question24 {


	public static void main(String[] args) {


	}

	public ListNode swapPairs(ListNode head) {
		//第一个跟第二个交换
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = swapPairs(head.next);
		newHead.next = head;


		return newHead;
	}
}
