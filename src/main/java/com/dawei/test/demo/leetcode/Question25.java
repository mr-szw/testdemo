package com.dawei.test.demo.leetcode;


/**
 * 剑指 Offer 25. 合并两个排序的链表
 * https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 */
public class Question25 {


	public static void main(String[] args) {

		ListNode listNode1 = new ListNode(1);
		ListNode listNode11 = new ListNode(2);
		ListNode listNode12 = new ListNode(4);
		listNode1.next = listNode11;
		listNode11.next = listNode12;


		ListNode listNode2 = new ListNode(1);
		ListNode listNode21 = new ListNode(3);
		ListNode listNode22 = new ListNode(4);

		listNode2.next = listNode21;
		listNode21.next = listNode22;

		System.out.println(new Question25().mergeTwoLists(listNode1, listNode2));
	}


	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode header = new ListNode(-1);
		ListNode resultNode = header;
		header.next = resultNode;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				resultNode.next = l1;
				l1 = l1.next;
			} else {
				resultNode.next = l2;
				l2 = l2.next;
			}
			resultNode = resultNode.next;
		}
		if (l1 != null) {
			resultNode.next = l1;
		} else {
			resultNode.next = l2;
		}
		return header.next;
	}
}


