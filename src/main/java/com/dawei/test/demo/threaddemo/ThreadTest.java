package com.dawei.test.demo.threaddemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawei on 2018/3/25.
 */
public class ThreadTest {


    public static List<Integer> numberList = new ArrayList<>();

    public static class AddList implements Runnable {

        int startNum = 0;

        public AddList(int startNumber) {
            startNum = startNumber;
        }

        @Override
        public void run() {
            int count = 0;
            while (count < 1000000) {
                numberList.add(startNum);
                startNum += 2;
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new AddList(0));
        Thread thread2 = new Thread(new AddList(1));

        thread1.start();
        thread2.start();

        while (thread1.isAlive() || thread2.isAlive()) {
            Thread.sleep(1);
        }

        System.out.println(numberList.size());

    }
}
