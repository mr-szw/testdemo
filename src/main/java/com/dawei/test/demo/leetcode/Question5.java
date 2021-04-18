package com.dawei.test.demo.leetcode;

/**
 * 5. 最长回文子串 . https://leetcode-cn.com/problems/longest-palindromic-substring/
 * 
 * @author sinbad on 2021/2/16.
 *
 * 暴力
 * 1、挨个尝试
 * 2、中心轴方式
 * 3、动态规划
 *
 */
public class Question5 {

	public static void main(String[] args) {
		//String grid = "cbbd";
		String grid = "bb";
		System.out.println(new Question5().longestPalindrome(grid));
	}

	/**
	 * 回文是两侧是符合条件的
	 * dp[i][j] = (str[j] == str[j] && dp[i+1][j-1])
	 * 边界就是 i <= j  && j -1 >= 1 得到 ==>  j -1 - （i + 1） >= 1 得到 => j -i <= 2
	 */
	public String longestPalindrome(String s) {

		int length = s.length();
		if (length < 2) {
			return s;
		}

		boolean[][] dp = new boolean[length][length];
		//单个单词确定是回文
		for (int i = 0; i < length; i++) {
			dp[i][i] = true;
		}

		int startIndex = 0;
		int endIndex = 0;
		for (int j = 1; j < length; j++) {
			for (int i = 0; i < j; i++) {
				if (s.charAt(i) != s.charAt(j)) {
					dp[i][j] = false;
				} else {
					//这块是因为 已经有两个边界相同了 那么三个元素的时候也是相同的
					if (j - i < 3) {
						dp[i][j] = true;
					} else {
						//用到的是上一列的前一个元素
						dp[i][j] = dp[i+1][j -1];
					}
				}
				if(dp[i][j] && (endIndex - startIndex) < (j -i +1)) {
					//substr 左闭右开
					endIndex = j +1;
					startIndex = i ;

				}
			}
		}
		return s.substring(startIndex, endIndex);
	}






	/* ###############################*/

	// 暴力 中心轴
	public String longestPalindrome1(String s) {

		int length = s.length();
		if (length < 2) {
			return s;
		}
		int start = 0;
		int end = 0;
		int max = 0;
		for (int i = 0; i < length; i++) {
			//数为轴
			int len1 = palindromeCheck1(s, i, i);
			//两数中间为轴
			int len2 = palindromeCheck1(s, i, i + 1);

			int len = Math.max(len1, len2);
			if (len > end - start) {
				max = len;
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}


		private int palindromeCheck1(String checkStr, int left, int right) {
			//两侧扩散
			while (left >= 0 && right < checkStr.length()) {
				if ( checkStr.charAt(left) != checkStr.charAt(right)) {
					break;
				}
				--left;
				++right;
			}
			return right - left - 1;
  
	}

  /* ###############################*/
	// 暴力 挨个尝试
	public String longestPalindrome0(String s) {

		int length = s.length();
		if (length < 2) {
			return s;
		}
		int[] result = new int[2];
		int startIndex = 0;
		int maxLength = 0;
		for (; startIndex < length - 1; startIndex++) {
			for (int endIndex = startIndex + 1; endIndex  <= length; endIndex++) {
				if (endIndex - startIndex > maxLength && palindromeCheck0(s, startIndex, endIndex)) {
					if (endIndex - startIndex > maxLength) {
						maxLength = endIndex - startIndex;
						result = new int[] { startIndex, endIndex };
					}
				}
			}
		}
		return s.substring(result[0], result[1]);
	}

	private boolean palindromeCheck0(String checkStr, int startIndex, int endIndex) {
		if (checkStr.length() <= 1) {
			return true;
		}
		while (startIndex < endIndex) {
			if (checkStr.charAt(startIndex) != checkStr.charAt(endIndex)) {
				return false;
			}
			startIndex++;
			endIndex--;
		}
		return true;
	}

}
