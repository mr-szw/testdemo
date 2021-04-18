package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 721. 账户合并
 * https://leetcode-cn.com/problems/accounts-merge/
 *
 * @author sinbad on 2021/1/18.
 */
public class Question721 {


	public static void main(String[] args) {

	}

	public List<List<String>> accountsMerge(List<List<String>> accounts) {

		List<List<String>> resultUserEmailListList = new ArrayList<>();

		int emailCount = 0;
		Map<String, String> emailUserMap = new HashMap<>();
		Map<String, Integer> markEmailIndexMap = new HashMap<>();
		for (List<String> accountList : accounts) {
			int size = accountList.size();
			String userName = accountList.get(0);

			for (int i = 1; i < size; i++) {
				String email = accountList.get(i);
				if (!emailUserMap.containsKey(email)) {

					//为每个邮箱编号
					markEmailIndexMap.put(email, emailCount++);
					//记录匹配关系 ？？   一个邮箱对应多个用户名？？？？
					emailUserMap.put(email, userName);
				}

			}
		}
		//为每个邮箱建立联通量
		UnionGroup unionGroup = new UnionGroup(emailCount);

		//将每个数据放入联通量中
		//将每个数组中的数据联通起来 然后 相互连通
		for (List<String> accountList : accounts) {
			int size = accountList.size();
			String firstEmail = accountList.get(1);
			Integer firstIndex = markEmailIndexMap.get(firstEmail);
			for (int i = 2; i < size; i++) {
				String email = accountList.get(i);
				Integer nextIndex = markEmailIndexMap.get(email);
				unionGroup.union(firstIndex, nextIndex);
			}
		}


		Map<Integer, List<String>> indexEmailMap = new HashMap<>();
		//遍历所有的key 查看他的联通代表量
		for (String email : markEmailIndexMap.keySet()) {
			Integer index = markEmailIndexMap.get(email);
			int parent = unionGroup.findParent(index);
			List<String> emailList = indexEmailMap.getOrDefault(parent, new ArrayList<>());
			emailList.add(email);
			indexEmailMap.put(parent, emailList);
		}


		//获取分类好的 email
		for (Map.Entry<Integer, List<String>> emailIndexEntry : indexEmailMap.entrySet()) {
			List<String> emailList = emailIndexEntry.getValue();
			Collections.sort(emailList);
			String userName = emailUserMap.get(emailList.get(0));

			List<String> userEmailList = new ArrayList<>();
			userEmailList.add(userName);
			userEmailList.addAll(emailList);
			resultUserEmailListList.add(userEmailList);
		}

		return resultUserEmailListList;

	}

	static class UnionGroup {

		private int[] parents;

		public UnionGroup(int n) {
			parents = new int[n];
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}
		}


		public void union(int m, int n) {
			parents[findParent(n)] = findParent(m);
		}

		public int findParent(int m) {
			if (parents[m] != m) {
				parents[m] = findParent(parents[m]);
			}
			return parents[m];
		}


	}

}
