package com.dawei.test.demo.leetcode;


import com.google.gson.Gson;

/**
 *328. 奇偶链表
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 *
 * @author sinbad on 2020/11/13.
 * <p>
 */
public class Question328 {


	public static void main(String[] args) {

		ListNode listNode = new ListNode(0);
		ListNode listNode1 = new ListNode(1);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode3 = new ListNode(3);

		listNode.next  = listNode1;
		listNode1.next  = listNode2;
		listNode2.next  = listNode3;



		System.out.println(new Gson().toJson(new Question328().oddEvenList(listNode)));
	}

	public ListNode oddEvenList(ListNode head) {

		if (head == null || head.next == null) {
			return head;
		}
		ListNode oddNode = head;
		ListNode eventNodeSave = head.next;
		ListNode eventNode = head.next;
		ListNode indexNode = head.next.next;

		boolean change = true;
		while (indexNode != null) {

			if (change) {
				oddNode.next = indexNode;
				oddNode = oddNode.next;
			} else {
				eventNode.next = indexNode;
				eventNode = eventNode.next;
			}
			change = !change;
			indexNode = indexNode.next;
		}
		eventNode.next = null;
		oddNode.next = eventNodeSave;
		return head;
	}

}
