package com.dawei.test.demo.tree;


import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author Dawei 2019/8/24.
 *  树遍历
 */
public class TreeTraversing {


    public static void main(String[] args) {

        int treeLen = 10;


        TreeNode[] treeNode = new TreeNode[treeLen];
        //给每个节点设置初始值
        for (int i = 0; i < treeLen; i++) {
            treeNode[i] = new TreeNode(i);
        }
        //设置每个节点的子节点值
        for (int i = 0; i < treeLen; i++) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            if (leftIndex < treeLen) {
                treeNode[i].setLeft(treeNode[leftIndex]);
            } else {
                treeNode[i].setLeft(null);
            }
            if (rightIndex < treeLen) {
                treeNode[i].setRight(treeNode[rightIndex]);
            } else {
                treeNode[i].setRight(null);
            }
        }
        List<Integer> resultList = new ArrayList<>();

        //preOrder(treeNode[0], resultList);
        //modOrder(treeNode[0], resultList);
        //postOrder(treeNode[0], resultList);
        //levelOrder(treeNode[0], resultList);
        //preOrderByStack(treeNode[0], resultList);
        //modOrderByStack(treeNode[0], resultList);
        postOrderByStack(treeNode[0], resultList);
        System.out.println("  ");
        System.out.println(JSON.toJSONString(resultList));
    }


    //先序遍历 根左右
    private static void preOrder(TreeNode rootNode, List<Integer> resultList) {

        //1、先根  首先将根接节点数据输出
        if (rootNode != null) {
            System.out.print(" " + rootNode.getValue());
            resultList.add(rootNode.getValue());



            //2、左
            TreeNode leftNode = rootNode.getLeft();
            if (leftNode != null) {
                preOrder(leftNode, resultList);
            }

            //3、右
            TreeNode rightNode = rootNode.getRight();
            if (rightNode != null) {
                preOrder(rightNode, resultList);
            }
        }
    }

    //先序遍历 根左右  使用栈
    private static void preOrderByStack(TreeNode rootNode, List<Integer> resultList) {

        if (rootNode != null) {
            Stack<TreeNode> treeNodeStack = new Stack<>();

            while (!treeNodeStack.empty() || rootNode != null) {

                //1、输出根节点数据， 并记录根节点 【若是左叶子也会当成根节点输出了】
                while (rootNode != null) {

                    treeNodeStack.push(rootNode);
                    Integer value = rootNode.getValue();
                    System.out.print(" " + value);
                    resultList.add(value);

                    //2、左节点 成为根节点
                    rootNode = rootNode.getLeft();
                }

                //3、从栈底弹出，让右节点做根节点，看看右节点还有什么
                if (!treeNodeStack.empty()) {

                    TreeNode treeNodeRootPop = treeNodeStack.pop();
                    rootNode = treeNodeRootPop.getRight();
                }
            }
        }
    }


    //中序遍历 左根右
    private static void modOrder(TreeNode rootNode, List<Integer> resultList) {


        if (rootNode != null) {
            //1、左
            TreeNode leftNode = rootNode.getLeft();
            if (leftNode != null) {
                //有左孩子继续向下 找到最左
                modOrder(leftNode, resultList);
            }
            //2、没有左孩子子了 输出父节点
            Integer value = rootNode.getValue();
            System.out.print(" " + value);
            resultList.add(value);

            //3、再看右孩子
            TreeNode rightNode = rootNode.getRight();
            if (rightNode != null) {
                modOrder(rightNode, resultList);
            }
        }
    }

    //中序遍历 左根右  使用栈
    private static void modOrderByStack(TreeNode rootNode, List<Integer> resultList) {
        if (rootNode != null) {
            Stack<TreeNode> treeNodeStack = new Stack<>();
            while (!treeNodeStack.empty() || rootNode != null) {

                //1、将根节点入栈 【若是左叶子也会当成根节点入栈】
                while (rootNode != null) {
                    treeNodeStack.push(rootNode);
                    //2、左节点 成为根节点
                    rootNode = rootNode.getLeft();
                }

                //3、从栈底弹出，让右节点做根节点，看看右节点还有什么
                if (!treeNodeStack.empty()) {
                    TreeNode treeNodeRootPop = treeNodeStack.pop();
                    Integer value = treeNodeRootPop.getValue();
                    System.out.print(" " + value);
                    resultList.add(value);
                    rootNode = treeNodeRootPop.getRight();
                }
            }
        }
    }


    //后序遍历 左右根
    private static void postOrder(TreeNode rootNode, List<Integer> resultList) {
        if (rootNode != null) {
            TreeNode leftNode = rootNode.getLeft();
            TreeNode rightNode = rootNode.getRight();
            //1、节点有左节点 继续遍历
            if (leftNode != null) {
                postOrder(leftNode, resultList);
            }
            //2、节点有右节点 继续遍历
            if (rightNode != null) {
                postOrder(rightNode, resultList);
            }
            //3、左右遍历完了 就输出中
            Integer value = rootNode.getValue();
            System.out.print(" " + value);
            resultList.add(value);
        }
    }

    //后序遍历 左右根  使用栈
    private static void postOrderByStack(TreeNode rootNode, List<Integer> resultList) {
        if (rootNode != null) {
            Stack<TreeNode> treeNodeStack = new Stack<>();
            Stack<Boolean> treeNodeCanOutFlag = new Stack<>();
            while (!treeNodeStack.empty() || rootNode != null) {

                //1、将根节点入栈 【若是左叶子也会当成根节点入栈】
                while (rootNode != null) {
                    treeNodeStack.push(rootNode);
                    treeNodeCanOutFlag.push(false);
                    //2、左节点 成为根节点
                    rootNode = rootNode.getLeft();

                }

                //2、从栈底弹出，是否可输出 弹出之前都可输出的
                while (!treeNodeStack.empty() && treeNodeCanOutFlag.peek()) {
                    TreeNode treeNodeRootPop = treeNodeStack.pop();
                    //操作了就弹出
                    treeNodeCanOutFlag.pop();
                    Integer popValue = treeNodeRootPop.getValue();
                    System.out.print(" " + popValue);
                    resultList.add(popValue);
                }

                //3、最后一个左节点 将其标记为可弹出的节点 取其右节点继续判断
                if(!treeNodeStack.empty() && !treeNodeCanOutFlag.peek()) {
                    treeNodeCanOutFlag.pop();
                    treeNodeCanOutFlag.push(true);
                    rootNode = treeNodeStack.peek().getRight();
                }
            }
        }
    }


    //层级遍历
    private static void levelOrder(TreeNode rootNode, List<Integer> resultList) {
        Queue<TreeNode> treeNodeLinkedList  = new ArrayDeque<>();
        if (rootNode != null) {
            treeNodeLinkedList.add(rootNode);

            while (!treeNodeLinkedList.isEmpty()) {
                //将列表中的数据 逐个弹出
                TreeNode firstNode = treeNodeLinkedList.poll();
                Integer value = firstNode.getValue();
                System.out.print(" " + value);
                resultList.add(value);

                //将子节点添加到队列后面
                TreeNode nodeLeft = firstNode.getLeft();
                if (nodeLeft != null) {
                    treeNodeLinkedList.add(nodeLeft);
                }
                //将子节点添加到队列后面
                TreeNode nodeRight = firstNode.getRight();
                if (nodeRight != null) {
                    treeNodeLinkedList.add(nodeRight);
                }
            }
        }

    }







    ////节点结构
static class TreeNode {
    private Integer value;
    private TreeNode left;
    private TreeNode right;

    TreeNode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}


}
