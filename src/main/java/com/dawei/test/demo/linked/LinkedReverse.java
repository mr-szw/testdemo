package com.dawei.test.demo.linked;


import com.alibaba.fastjson.JSON;
import com.dawei.test.demo.pojo.DemoPojo;

import java.util.Date;
import java.util.Random;

/**
 * @author Dawei 2019/8/24.
 *
 * linkedlist倒叙
 */
public class LinkedReverse {


    public static void main(String[] args) {

        LinkedNode linkedNodeA = new LinkedNode();

//        while (true) {
//            LinkedNode linkedNodeNext = new LinkedNode(new Random().nextInt());
//            linkedNodeA.setRightNode(linkedNodeNext);
//            linkedNodeA = linkedNodeA.getRightNode();
//        }

        DemoPojo demoPojo1 = new DemoPojo();
        demoPojo1.setName("A");
        demoPojo1.setBirthday(new Date(199999999L));
        DemoPojo demoPojo2 = new DemoPojo();
        demoPojo2.setName("B");
        demoPojo2.setBirthday(new Date(299999999L));
        System.out.println("demoPojo1 = " + JSON.toJSONString(demoPojo1));
        System.out.println("demoPojo2 = " + JSON.toJSONString(demoPojo2));
        Date temp  = demoPojo1.getBirthday();
        demoPojo1.setBirthday(demoPojo2.getBirthday());
        demoPojo2.setBirthday(temp);
        System.out.println("demoPojo1 = " + JSON.toJSONString(demoPojo1));
        System.out.println("demoPojo2 = " + JSON.toJSONString(demoPojo2));


        int treeLen = 10;


        LinkedNode[] linkedNodeList =  new LinkedNode[treeLen];

        //给每个节点设置初始值
        linkedNodeList[0] = new LinkedNode(0);
        for (int i = 1; i < treeLen; i++) {
            linkedNodeList[i] = new LinkedNode(i);
            linkedNodeList[i-1].setRightNode(linkedNodeList[i]);
        }

        for (LinkedNode linkedNode : linkedNodeList) {
            System.out.print(" " + linkedNode.getItemValue());
        }
        System.out.println(" ");
        System.out.println(" Result");
        //reverseLinkedList(linkedNodeList);
        reverseLinkedListByRecursive(linkedNodeList[0]);

        LinkedNode linkedNodeFirstRight = linkedNodeList[linkedNodeList.length -1];
        while (linkedNodeFirstRight != null) {
            System.out.print("  " + linkedNodeFirstRight.getItemValue());
            linkedNodeFirstRight = linkedNodeFirstRight.getRightNode();
        }
        System.out.println(" ");

        for (LinkedNode linkedNode : linkedNodeList) {
            System.out.print(" " + linkedNode.getItemValue());
        }
        System.out.println(" ");
    }




    //链表数据倒叙
    private static void reverseLinkedList(LinkedNode[] linkedNodeList) {
        int length = linkedNodeList.length;
        for (int i=0; i < length / 2; i++) {
            LinkedNode linkedNodeLeft = linkedNodeList[i];
            LinkedNode linkedNodeRight = linkedNodeList[length - i -1];
            Integer leftValueTemp = linkedNodeLeft.getItemValue();
            LinkedNode leftTempRightNode = linkedNodeLeft.getRightNode();

            linkedNodeLeft.setRightNode(linkedNodeRight.getRightNode());
            linkedNodeLeft.setItemValue(linkedNodeRight.getItemValue());

            linkedNodeRight.setRightNode(leftTempRightNode);
            linkedNodeRight.setItemValue(leftValueTemp);
        }

    }

    //链表数据倒叙
    private static LinkedNode reverseLinkedListByRecursive(LinkedNode firstNode) {

        //当前元素是最后一个 或者 其为null
        if (firstNode == null || firstNode.getRightNode() == null) {
            return firstNode;
        }

        //若数据为 A -> B
        //firstNode                = A
        //firstNode.getRightNode() = B

        //找到最后一个 这里返回的是B
        LinkedNode linkedNode = reverseLinkedListByRecursive(firstNode.getRightNode());

        //这里就是 B 的下一个是 A
        firstNode.getRightNode().setRightNode(firstNode);
        //firstNode.getRightNode().setItemValue(firstNode.getItemValue());
        // A的下一个是 空
        firstNode.setRightNode(null);


        //若A -> B -> C
        //firstNode                = A
        //firstNode.getRightNode() = B

        //递归开始--->
        //firstNode 变为 B
        //firstNode.getRightNode() = C
        //递归返回 C
        //这里就是 C 的下一个是 B
        //这里就是 B 的下一个是 null
        //递归结束 <---

        //递归结束返回 C
        //这里就是 B 的下一个是 A
        //这里就是 A 的下一个是 null
        //结果返回 C


        return linkedNode;
    }





    ////节点结构
    static class LinkedNode {
        private Integer itemValue;
        private LinkedNode leftNode;
        private LinkedNode rightNode;

        public LinkedNode() {
        }

        public LinkedNode(Integer itemValue) {
            this.itemValue = itemValue;
        }

        public Integer getItemValue() {
            return itemValue;
        }

        public void setItemValue(Integer itemValue) {
            this.itemValue = itemValue;
        }

        public LinkedNode getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(LinkedNode leftNode) {
            this.leftNode = leftNode;
        }

        public LinkedNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(LinkedNode rightNode) {
            this.rightNode = rightNode;
        }

        @Override
        public String toString() {
            return itemValue.toString();
        }
    }

}



