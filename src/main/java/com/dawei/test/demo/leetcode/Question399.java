package com.dawei.test.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

/**
 * 399. 除法求值
 * https://leetcode-cn.com/problems/evaluate-division/
 *
 * @author sinbad on 2021/1/6.
 * 这题没意思 有高级算法的方式解
 *
 */
public class Question399 {


	public static void main(String[] args) {

		System.out.println(Arrays.toString(new Question399().calcEquation(null, null, null)));

	}

	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		int nvars = 0;
		Map<String, Integer> variables = new HashMap<String, Integer>();

		int n = equations.size();
		for (int i = 0; i < n; i++) {
			if (!variables.containsKey(equations.get(i).get(0))) {
				variables.put(equations.get(i).get(0), nvars++);
			}
			if (!variables.containsKey(equations.get(i).get(1))) {
				variables.put(equations.get(i).get(1), nvars++);
			}
		}

		// 对于每个点，存储其直接连接到的所有点及对应的权值
		List<Pair>[] edges = new List[nvars];
		for (int i = 0; i < nvars; i++) {
			edges[i] = new ArrayList<Pair>();
		}
		for (int i = 0; i < n; i++) {
			int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
			edges[va].add(new Pair(vb, values[i]));
			edges[vb].add(new Pair(va, 1.0 / values[i]));
		}

		int queriesCount = queries.size();
		double[] ret = new double[queriesCount];
		for (int i = 0; i < queriesCount; i++) {
			List<String> query = queries.get(i);
			double result = -1.0;
			if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
				int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
				if (ia == ib) {
					result = 1.0;
				} else {
					Queue<Integer> points = new LinkedList<Integer>();
					points.offer(ia);
					double[] ratios = new double[nvars];
					Arrays.fill(ratios, -1.0);
					ratios[ia] = 1.0;

					while (!points.isEmpty() && ratios[ib] < 0) {
						int x = points.poll();
						for (Pair pair : edges[x]) {
							int y = pair.index;
							double val = pair.value;
							if (ratios[y] < 0) {
								ratios[y] = ratios[x] * val;
								points.offer(y);
							}
						}
					}
					result = ratios[ib];
				}
			}
			ret[i] = result;
		}
		return ret;
	}
}

class Pair {
	int index;
	double value;

	Pair(int index, double value) {
		this.index = index;
		this.value = value;
	}
}


