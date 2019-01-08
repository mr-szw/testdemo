package com.dawei.test.demo;

import com.alibaba.fastjson.JSON;
import com.dawei.test.demo.pojo.ContractMessage;
import com.dawei.test.demo.pojo.ContractMessageBody;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Dawei  on 2018/3/25.
 */
public class DemoTestMain {

    @Test
    public void madin() {

/*

        //存储数据的结构
        List<List<String>> parentList = new ArrayList<>();

        //线程二 读文件 做请求
        while (true) {
            while (parentList.size() > 1) {
                List<String> list = parentList.get(0);
                for (String s : list) {
                    //异步請求数据
                }
                //請求结束 将数据移除
                parentList.remove(0);
            }
            if("文件读完了".contains("完了")) {
                break;
            }
        }
        List<String> childrenList = new ArrayList<>();
        //  线程一 读文件 readline的循环
        while (true) {
            if (childrenList.size() < 1000) {
                childrenList.add("读到的数据");

            } else {
                parentList.add(childrenList);
                childrenList = new ArrayList<>();
                childrenList.add("读到的数据");
            }
        }
*/


        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(15);
        integerList.add(16);
        integerList.add(15);
        System.out.println(Arrays.toString(integerList.subList(2, 5).toArray()));
        System.out.println(new Date(1544778031000L).toString());
        System.out.println("create :" + new Date(1544778031000L).toString());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(time);

        String messageBody = "{\"head\":\"CONTRACT_STATUS_CHANGE\",\"body\":{\"templateType\":2,\"orderId\":1012538767323881088,\"oldSignStatus\":29,\"signStatus\":22,\"eventTime\":\"2018-11-0217:22:51\",\"contractId\":2538721084366464,\"agentName\":\"刘玲\",\"categoryId\":212}}";
        ContractMessageBody contractMessageBody = null;
        ContractMessage contractMessage = JSON.parseObject(messageBody, ContractMessage.class);
        if (contractMessage != null) {
            String head = contractMessage.getHead();
            contractMessageBody = contractMessage.getBody();
        }

        System.out.println(JSON.toJSONString(contractMessageBody));


        //QueueProducerDemo.messageProducer();


        System.out.println("this Main method");
        // System.out.println(execute());
        System.out.println("main method over");
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
