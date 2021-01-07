package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/
 * 拼写单词
 *
 * @author sinbad on 2020/08/29.
 */
public class Question1160 {


	public static void main(String[] args) {


		String[] words = new String[]{"dyiclysmffuhibgfvapygkorkqllqlvokosagyelotobicwcmebnpznjbirzrzsrtzjxhsfpiwyfhzyonmuabtlwin", "ndqeyhhcquplmznwslewjzuyfgklssvkqxmqjpwhrshycmvrb", "ulrrbpspyudncdlbkxkrqpivfftrggemkpyjl", "boygirdlggnh", "xmqohbyqwagkjzpyawsydmdaattthmuvjbzwpyopyafphx", "nulvimegcsiwvhwuiyednoxpugfeimnnyeoczuzxgxbqjvegcxeqnjbwnbvowastqhojepisusvsidhqmszbrnynkyop", "hiefuovybkpgzygprmndrkyspoiyapdwkxebgsmodhzpx", "juldqdzeskpffaoqcyyxiqqowsalqumddcufhouhrskozhlmobiwzxnhdkidr", "lnnvsdcrvzfmrvurucrzlfyigcycffpiuoo", "oxgaskztzroxuntiwlfyufddl", "tfspedteabxatkaypitjfkhkkigdwdkctqbczcugripkgcyfezpuklfqfcsccboarbfbjfrkxp", "qnagrpfzlyrouolqquytwnwnsqnmuzphne", "eeilfdaookieawrrbvtnqfzcricvhpiv", "sisvsjzyrbdsjcwwygdnxcjhzhsxhpceqz", "yhouqhjevqxtecomahbwoptzlkyvjexhzcbccusbjjdgcfzlkoqwiwue", "hwxxighzvceaplsycajkhynkhzkwkouszwaiuzqcleyflqrxgjsvlegvupzqijbornbfwpefhxekgpuvgiyeudhncv", "cpwcjwgbcquirnsazumgjjcltitmeyfaudbnbqhflvecjsupjmgwfbjo", "teyygdmmyadppuopvqdodaczob", "qaeowuwqsqffvibrtxnjnzvzuuonrkwpysyxvkijemmpdmtnqxwekbpfzs", "qqxpxpmemkldghbmbyxpkwgkaykaerhmwwjonrhcsubchs"};
		String chars = "usdruypficfbpfbivlrhutcgvyjenlxzeovdyjtgvvfdjzcmikjraspdfp";

		System.out.println(new Question1160().countCharacters(words, chars));
	}


	public int countCharacters(String[] words, String chars) {

		if (chars == null || chars.length() == 0) {
			return 0;
		}

		//整理字符串

		char[] charArray = chars.toCharArray();
		Map<Character, Integer> charNumMap = new HashMap<>();
		for (char charWord : charArray) {
			charNumMap.put(charWord, charNumMap.getOrDefault(charWord, 0) + 1);
		}


		int resultLen = 0;

		for (String word : words) {

			boolean ok = true;

			//整理每个词含有的单词数
			char[] wordCharArray = word.toCharArray();
			Map<Character, Integer> charNumMapTemp = new HashMap<>();
			for (char charWord : wordCharArray) {
				//若字符串中不含有单词的字符就是不存在
				if (charNumMap.get(charWord) == null) {
					ok = false;
					break;
				}
				charNumMapTemp.put(charWord, charNumMapTemp.getOrDefault(charWord, 0) + 1);
				if (charNumMapTemp.getOrDefault(charWord, 0) > charNumMap.getOrDefault(charWord, 0)) {
					ok = false;
					break;
				}
			}

			if (ok) {
				resultLen += word.length();
			}
		}
		return resultLen;
	}


}
