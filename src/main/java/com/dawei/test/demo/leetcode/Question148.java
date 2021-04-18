package com.dawei.test.demo.leetcode;


import com.google.gson.Gson;

/**
 * 147. 对链表进行插入排序
 * https://leetcode-cn.com/problems/insertion-sort-list/
 *
 * @author sinbad on 2020/11/22.
 * 造成栈溢出 不会做啊
 * <p>
 */
public class Question148 {


	public static void main(String[] args) {

		ListNode listNode = new ListNode(4);
		ListNode listNode1 = new ListNode(1);
		ListNode listNode2 = new ListNode(2);
		//ListNode listNode3 = new ListNode(3);
		listNode.next = listNode1;
		listNode1.next = listNode2;
		//listNode1.next = listNode3;
		//4 1 2

		System.out.println(new Gson().toJson(new Question148().sortList(listNode)));
	}



	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		//首先根据nlog(n) 方法有快排 堆 排序 归并
		//归并需要先切两段
		ListNode fastNode = head.next;
		ListNode slowNode = head;

		while(fastNode !=null && fastNode.next != null) {
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;

		}
		//中间节点

		ListNode rightStartNode = slowNode.next;
		slowNode.next = null;

		ListNode leftNode = sortList(head);
		ListNode rightNode = sortList(rightStartNode);

		return mergeNode(leftNode, rightNode);
	}


	public ListNode mergeNode(ListNode leftNode , ListNode rightNode) {
		ListNode headNode = new ListNode(0);
		ListNode provNode = headNode;
		while(leftNode != null && rightNode != null) {
			if (leftNode.val <= rightNode.val) {
				provNode.next = leftNode;
				leftNode = leftNode.next;
			} else {
				provNode.next = rightNode;
				rightNode = rightNode.next;
			}
			provNode = provNode.next;
		}
		if (leftNode == null) {
			provNode.next = rightNode;
		}  else{
			provNode.next = leftNode;
		}
		return headNode.next;
	}

}
