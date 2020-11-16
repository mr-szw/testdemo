package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 406. 根据身高重建队列- https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 *
 * @author sinbad on 2020/11/16.
 *         https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/406-gen-ju-shen-gao-zhong-jian-dui-lie-java-xian-p/
 */
public class Question406 {

	public static void main(String[] args) {

		System.out.println(new Question406().reconstructQueue(new int[][] {}));
	}

	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people,
				(item1, item2) -> item1[0] == item2[0] ? item1[1] - item2[1] : item2[0] - item1[0]);
		List<int[]> resultList = new ArrayList<>();
		for (int[] person : people) {
			resultList.add(person[1], person);
		}
		return resultList.toArray(people);
	}
}
