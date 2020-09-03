package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 钥匙和房间
 * https://leetcode-cn.com/problems/keys-and-rooms/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question841 {


	public static void main(String[] args) {


		String resources = "[[1,3],[3,0,1],[2],[0]]";
		List<List<Integer>>  rooms = new Gson().fromJson(resources, new TypeToken<List<List<Integer>>>() {
		}.getType());
		System.out.println(new Question841().canVisitAllRooms(rooms));
	}

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		List<Integer> zeroRoom = rooms.get(0);

		if ((zeroRoom == null || zeroRoom.size() == 0) && rooms.size() != 1) {
			return false;
		} else if (zeroRoom == null || zeroRoom.size() == 0) {
			return true;
		}

		boolean[] booleanVisit = new boolean[rooms.size()];

		booleanVisit[0] = true;
		visitRoom(booleanVisit, rooms, zeroRoom);

		for (boolean visited : booleanVisit) {
			if (!visited) {
				return false;
			}
		}
		return true;
	}

	private void visitRoom(boolean[] booleanVisit, List<List<Integer>> zoomList, List<Integer> keyList) {
		if (keyList == null || keyList.size() == 0) {
			return;
		}
		for (Integer integer : keyList) {
			if (!booleanVisit[integer]) {
				booleanVisit[integer] = true;
				visitRoom(booleanVisit, zoomList, zoomList.get(integer));
			}
		}
	}

}
