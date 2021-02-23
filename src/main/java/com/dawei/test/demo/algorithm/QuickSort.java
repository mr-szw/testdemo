package com.dawei.test.demo.algorithm;

import java.util.Arrays;

/**
 * 快排
 * 
 * @author sinbad on 2021/2/23.
 */
public class QuickSort {

	private static int partition(int[] arr, int start, int end) {
		// arr[start]为挖的第一个坑
		int target = arr[start];
		while (start < end) {
			while (arr[end] >= target && end > start) {
				end--;
			}
			arr[start] = arr[end];
			while (arr[start] < target && end > start) {
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = target;
		return start;
	}

	public static void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int index = partition(arr, start, end);
			quickSort(arr, start, index - 1);
			quickSort(arr, index + 1, end);
		}
	}

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 10, 6, 11, 4, 9, 8, 13, 15 };
		// quickSort(arr);
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void sort(int[] arr) {
		sortQuick(arr, 0, arr.length - 1);
	}

	private static int sortQuickIndex(int[] arr, int start, int end) {
		int target = arr[start];
		while (start < end) {
			while (start < end && arr[end] >= target) {
				end--;
			}
			arr[start] = arr[end];
			while (start < end && arr[start] <= target) {
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = target;
		return start;
	}

	private static void sortQuick(int[] arr, int start, int end) {
		if (start < end) {
			int quickIndex = sortQuickIndex(arr, start, end);
			sortQuick(arr, 0, quickIndex - 1);
			sortQuick(arr, quickIndex + 1, end);
		}

	}

}
