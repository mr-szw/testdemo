package com.dawei.test.demo;

import com.alibaba.fastjson.JSON;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.Test;

/**
 * @author Dawei  on 2018/3/25.
 */
public class DemoTestMain {


    class Solution {

        public int removeDuplicates(int[] nums) {
            int length = nums.length;
            for (int i = 1; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    if(nums[i] == nums[j]) {

                    }
                }
            }

            int numCount = 1;
            int temp = nums[0];

            int tempIndex = 0;
            for (int i = 1; i < nums.length - 1; ) {
                if (temp != nums[i]) {
                    numCount++;
                    temp = nums[i];
                } else {
                    tempIndex = i;
                    nums[i] = nums[tempIndex];
                }
            }
            return numCount;
        }
    }


    @Test
    public void madin() {


        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put("a", "as");
        linkedHashMap.put("b", "as");


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parse = simpleDateFormat.parse("0000-00-00 00:00:00");
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Integer> workerListAll = new ArrayList<>();
        for(int i= 0; i< 30; i++) {
            workerListAll.add(i);
        }

        Object k = 6;
        System.out.println(((6) ^ (k.hashCode() >>> 16)));
        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("1", "2");



        int pageSize = 30;
        int pageNo = 0;
        int size = workerListAll.size();

        while (size > pageNo) {
            try {
                List<Integer> workerListTemp = new ArrayList<>();
                if (size > (pageNo + 30)) {
                    workerListTemp = workerListAll.subList(pageNo, pageNo + 30);
                } else {
                    workerListTemp = workerListAll.subList(pageNo, size);
                }
                pageNo += pageSize;
                System.out.println(JSON.toJSONString(workerListTemp));
            } catch (Exception e) {

            }

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            System.out.println(dateFormat.parse("2000-01-01 00:00:00").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(new Date(946656000000L));
//        int[] nums = {2, 2, 2, 2};
//        //System.out.println(new Solution().removeDuplicates(nums));
//
//
///*
//
//        //存储数据的结构
//        List<List<String>> parentList = new ArrayList<>();
//
//        //线程二 读文件 做请求
//        while (true) {
//            while (parentList.size() > 1) {
//                List<String> list = parentList.get(0);
//                for (String s : list) {
//                    //异步請求数据
//                }
//                //請求结束 将数据移除
//                parentList.remove(0);
//            }
//            if("文件读完了".contains("完了")) {
//                break;
//            }
//        }
//        List<String> childrenList = new ArrayList<>();
//        //  线程一 读文件 readline的循环
//        while (true) {
//            if (childrenList.size() < 1000) {
//                childrenList.add("读到的数据");
//
//            } else {
//                parentList.add(childrenList);
//                childrenList = new ArrayList<>();
//                childrenList.add("读到的数据");
//            }
//        }
//*/
//
//        List<Integer> integerList = new ArrayList<>();
//        integerList.add(1);
//        integerList.add(1);
//        integerList.add(2);
//        integerList.add(3);
//        integerList.add(4);
//        integerList.add(15);
//        integerList.add(16);
//        integerList.add(15);
//        System.out.println(Arrays.toString(integerList.subList(2, 5).toArray()));
//        System.out.println(new Date(1544778031000L).toString());
//        System.out.println("create :" + new Date(1544778031000L).toString());
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        Date time = calendar.getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat.format(time);
//
//        String messageBody = "{\"head\":\"CONTRACT_STATUS_CHANGE\",\"body\":{\"templateType\":2,\"orderId\":1012538767323881088,\"oldSignStatus\":29,\"signStatus\":22,\"eventTime\":\"2018-11-0217:22:51\",\"contractId\":2538721084366464,\"agentName\":\"刘玲\",\"categoryId\":212}}";
////        ContractMessageBody contractMessageBody = null;
////        ContractMessage contractMessage = JSON.parseObject(messageBody, ContractMessage.class);
////        if (contractMessage != null) {
////            String head = contractMessage.getHead();
////            contractMessageBody = contractMessage.getBody();
////        }
//
////        System.out.println(JSON.toJSONString(contractMessageBody));
//
//        //QueueProducerDemo.messageProducer();
//
//        System.out.println("this Main method");
//        // System.out.println(execute());
//        System.out.println("main method over");
    }

    public static String execute() {
        new ThreadT().start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "execute over";
    }

    static class ThreadT extends Thread {

        @Override
        public void run() {
            System.out.println("to do Run now ");
            try {
                Thread.sleep(1000);
                System.out.println("this run first");
                Thread.sleep(5000);
                System.out.println("this run Second ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
