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
public class Question147 {


	public static void main(String[] args) {

		ListNode listNode = new ListNode(4);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode1 = new ListNode(1);
		ListNode listNode3 = new ListNode(3);
		listNode.next = listNode2;
		listNode2.next = listNode1;
		listNode1.next = listNode3;

		System.out.println(new Gson().toJson(new Question147().insertionSortList(listNode)));
	}


	public ListNode insertionSortList(ListNode head) {

		if (head == null || head.next == null) {
			return head;
		}


		/*
		插入排序
		当前值跟列表值比较
			1、比较当前值与被比较值：大于等于被比较值则替换位置
			 					 否则修改被比较节点
			2、修改当前值

		 */

		ListNode newHeadNode = new ListNode(0);
		newHeadNode.next = head;

		ListNode currNode = head.next;
		ListNode currNodePre = head;
		while (currNode != null) {
			ListNode preNode = newHeadNode;
			ListNode compareNode = newHeadNode.next;
			//内层逻辑
			while (compareNode != null) {
				if (currNode.val < compareNode.val) {
					//移除当前元素
					currNodePre.next = currNode.next;
					//插入新元素了
					preNode.next = currNode;
					compareNode.next = currNode.next;
					currNode.next = compareNode;
					break;
				}
				preNode = compareNode;
				compareNode = compareNode.next;
			}
			currNodePre = currNode;
			currNode = currNode.next;
		}
		return newHeadNode.next;
	}

}
