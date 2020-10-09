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

		int[] position = new int[]{2, 5, 2, 1, 2};
		System.out.println(new Gson().toJson(new Question141().hasCycle(null)));
	}

	public boolean hasCycle(ListNode head) {
		//1、双指针追逐
		//2、set去重方式 空间不对
		int fast;
		int slow;
		while (head.next != null) {
			slow = head.val;
			if (head.next.next == null) {
				return false;
			}
			fast = head.next.next.val;
			if (fast == slow) {
				return true;
			}
			head = head.next;
		}
		return false;
	}
}

