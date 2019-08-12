package com.dawei.test.demo.threaddemo;


/**
 * @author Dawei 2019/3/13
 */
public class TestTryDemo {

    private volatile Integer countNum;

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public static void main(String[] args) {
        mainTest1();
    }

    public static void mainTest1() {

        TestTryDemo testTryDemo = new TestTryDemo();
        testTryDemo.setCountNum(0);


        ThreadTest threadTest = new ThreadTest(testTryDemo);
        ThreadTest2 threadTest2 = new ThreadTest2(testTryDemo);

        new Thread(threadTest).start();
        new Thread(threadTest2).start();

    }


}
