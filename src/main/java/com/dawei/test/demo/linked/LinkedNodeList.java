package com.dawei.test.demo.linked;



/**
 * @author Dawei 2019/8/23.
 * 做链表的node
 */
public class LinkedNodeList<E>  {


    private static class LinkedNode<E> {
        E itemNode;
        LinkedNodeList.LinkedNode<E> beforeNode;
        LinkedNodeList.LinkedNode<E> afterNode;

        public LinkedNode(LinkedNode<E> beforeNode, E itemNode, LinkedNode<E> afterNode) {
            this.itemNode = itemNode;
            this.beforeNode = beforeNode;
            this.afterNode = afterNode;
        }
    }

    transient int size = 0;

    private transient LinkedNode<E> beforeNode;

    private transient LinkedNode<E> afterNode;



    public LinkedNodeList() {
    }



    /**
     * 往后加
     * @param itemNode 对象
     */
    public boolean addByLast(E itemNode) {
        addLinkAtLast(itemNode);
        return true;
    }

    /**
     * 往前加
     * @param itemNode 对象
     */
    public boolean addByFirst(E itemNode) {
        addLinkAtFirst(itemNode);
        return true;
    }

    public int size() {
        return size;
    }

    /**
     * Links e as last element.
     */
    private void addLinkAtLast(E itemNode) {
        final LinkedNode<E> theLast = afterNode;
        final LinkedNode<E> newNode = new LinkedNode<>(theLast, itemNode, null);
        afterNode = newNode;
        if (theLast == null)
            beforeNode = newNode;
        else
            theLast.afterNode = newNode;
        size++;
        //modCount++;
    }


    /**
     * Links e as last element.
     */
    private void addLinkAtFirst(E itemNode) {
        final LinkedNode<E> theFirst = beforeNode;
        final LinkedNode<E> newNode = new LinkedNode<>(null, itemNode, theFirst);
        beforeNode = newNode;
        if (theFirst == null)
            afterNode = newNode;
        else
            theFirst.beforeNode = newNode;
        size++;
        //modCount++;
    }


}
