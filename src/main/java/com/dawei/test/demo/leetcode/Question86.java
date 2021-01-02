package com.dawei.test.demo.leetcode;

/**
 * 86.分隔链表
 * https://leetcode-cn.com/problems/partition-list/
 *
 * @author sinbad on 2021/1/3. 好难理解啊
 */
public class Question86 {

	public static void main(String[] args) {
		ListNode treeNode = new ListNode(1);

		System.out.println(new Question86().partition(treeNode, 1));
	}

	public ListNode partition(ListNode head, int x) {

		ListNode smallNode = new ListNode(0);
		ListNode smallNodeHead = smallNode;
		ListNode bigNode = new ListNode(0);
		ListNode bigNodeHead = bigNode;


		while (head != null) {
			if (head.val < x) {
				smallNode.next = head;
				smallNode = smallNode.next;
			} else {
				bigNode.next = head;
				bigNode = bigNode.next;
			}
			head = head.next;
		}

		smallNode.next = bigNodeHead.next;
		bigNode.next = null;
		return smallNodeHead.next;
	}
}
