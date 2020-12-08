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
		ListNode listNode2 = new ListNode(2);
		ListNode listNode1 = new ListNode(1);
		ListNode listNode3 = new ListNode(3);
		listNode.next = listNode2;
		listNode2.next = listNode1;
		listNode1.next = listNode3;

		System.out.println(new Gson().toJson(new Question148().sortList(listNode)));
	}

	public ListNode sortList(ListNode head) {

		return null;
	}

}
