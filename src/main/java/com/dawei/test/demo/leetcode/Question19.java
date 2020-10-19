package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 19. 删除链表的倒数第N个节点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * @author sinbad on 2020/09/18.
 * <p>
 */
public class Question19 {


	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		ListNode nodeOne = new ListNode(0);
		//System.out.println(new Question19().removeNthFromEnd(node, 3));
		System.out.println(new Question19().removeNthFromEnd(nodeOne, 1));
	}

	//栈pop的方式
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || n <= 0) {
			return head;
		}
		Stack<ListNode> path = new Stack<>();
		ListNode tempNode = new ListNode(0, head);
		while (tempNode.next != null){
			tempNode = tempNode.next;
			path.add(tempNode);
		}
		ListNode removeNode = null;
		while (n-- > 0) {
			removeNode = path.pop();
		}
		if (path.isEmpty()) {
			head = removeNode.next;
		} else {
			ListNode preNode = path.pop();
			preNode.next = removeNode.next;
		}
		return head;
	}
}
