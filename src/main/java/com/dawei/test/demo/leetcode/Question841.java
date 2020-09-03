package com.dawei.test.demo.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 钥匙和房间
 * https://leetcode-cn.com/problems/keys-and-rooms/
 *
 * @author sinbad on 2020/08/19.
 */
public class Question841 {


    public static void main(String[] args) {

      //  System.out.println(new Question841().canVisitAllRooms(grid));
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {


        List<Integer> zeroRoom = rooms.get(0);

        if ((zeroRoom == null || zeroRoom.size() == 0) && rooms.size() != 1) {
            return false;
        } else if (zeroRoom == null || zeroRoom.size() == 0) {
            return true;
        }

        //value 1 浏览过 0 有钥匙

        Map<Integer, Boolean> roomListMap = new HashMap<>();
        Map<Integer, Boolean> visitKeyMap = new HashMap<>();

        visitKeyMap.put(0, true);
        for (Integer integer : zeroRoom) {
            visitKeyMap.put(integer, true);
        }


        for (int i = 1; i < rooms.size(); i++) {
            List<Integer> integers = rooms.get(i);
            for (Integer integer : integers) {
                visitKeyMap.put(integer, true);
                roomListMap.put(integer, false);
            }
        }

        Iterator<Map.Entry<Integer, Boolean>> iterator = roomListMap.entrySet().iterator();

        return false;
    }

}
