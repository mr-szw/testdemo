package com.dawei.test.demo.leetcode;

/**
 * 24. 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question24 {


	public static void main(String[] args) {
		ListNode listNode = new ListNode(1);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode3 = new ListNode(3);
		ListNode listNode4 = new ListNode(4);

		listNode.next = listNode2;
		listNode2.next = listNode3;
		listNode3.next = listNode4;
		System.out.println(new Question24().swapPairs(listNode));
	}

	public ListNode swapPairs(ListNode head) {
		/**
		 * 指向
		 * ListNode newHead = head.next;
		 * ListNode newNext = head;
		 * newNext.next = swapPairs(head.next.next);
		 * newHead.next = newNext;
		 * return newHead;
		 */
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = swapPairs(head.next.next);
		newHead.next = head;
		return newHead;
	}
}
