package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.checkerframework.checker.units.qual.C;

import com.google.gson.Gson;

/**
 * 146. LRU 缓存机制
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * @author sinbad on 2021/02/19.
 * <p>
 */
public class Question146 {


	public static void main(String[] args) {

	}

	class LRUCache {

		Map<Integer, CacheNode> cacheNodeMap;
		int cacheSize;
		int currSize;
		CacheNode headNode;
		CacheNode tailNode;

		public LRUCache(int capacity) {
			this.cacheNodeMap = new HashMap<>(capacity);
			this.cacheSize = capacity;
			//队头队尾不存储元素
			this.headNode = new CacheNode();
			this.tailNode = new CacheNode();
			this.headNode.nextNode = this.tailNode;
			this.tailNode.prevNode = this.headNode;
		}

		public int get(int key) {

			CacheNode cacheNode = getCacheNode(key);
			if (cacheNode == null) {
				return -1;
			}

			//移动当前数据到队尾
			moveNodeToTail(cacheNode);
			return cacheNode.value;

		}

		public void put(int key, int value) {
			CacheNode cacheNode = getCacheNode(key);
			if (cacheNode == null) {
				cacheNode = new CacheNode(key, value);
			} else {
				cacheNode.value = value;
				//移动到队尾
				moveNodeToTail(cacheNode);
			}

			//满员了 删除队头
			if (currSize == cacheSize) {
				//存储中删除第一个元素
				cacheNodeMap.remove(headNode.nextNode.key);

				//第二个第前面指针指向 当前
				CacheNode secondNode = headNode.nextNode.nextNode;

				headNode.nextNode = secondNode;
				secondNode.prevNode = headNode;

				//添加元素到队尾
				addNodeToTail(cacheNode);
			} else {

				//没满
				addNodeToTail(cacheNode);
				currSize++;
			}
			if (currSize < cacheSize) {
				tailNode.nextNode = cacheNode;
			}

		}

		private CacheNode getCacheNode(int key) {
			return cacheNodeMap.get(key);
		}

		//队尾是最近的   tailNode 是个标记元素
		private void addNodeToTail(CacheNode cacheNode) {

			tailNode.prevNode.nextNode = cacheNode;
			cacheNode.prevNode = tailNode.prevNode;

			cacheNode.nextNode = tailNode;
			tailNode.prevNode = cacheNode;
		}

		//移动到队尾
		private void moveNodeToTail(CacheNode currNode) {

			CacheNode nextNode = currNode.nextNode;
			CacheNode prevNode = currNode.prevNode;

			prevNode.nextNode = nextNode;
			nextNode.prevNode = prevNode;

			addNodeToTail(currNode);
		}
	}


	class CacheNode {

		int key;
		int value;
		CacheNode prevNode;
		CacheNode nextNode;

		public CacheNode() {

		}

		public CacheNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}
