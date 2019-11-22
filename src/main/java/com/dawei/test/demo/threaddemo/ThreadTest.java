//package com.dawei.test.demo.threaddemo;
//
///**
// * Created by Dawei on 2018/3/25.
// */
//public class ThreadTest implements Runnable{
//
//    private static final Object object = new Object();
//
//        int startNum = 0;
//
//        public AddList(int startNumber) {
//            startNum = startNumber;
//        }
//
//    private static volatile Boolean numFlag = true;
//    private TestTryDemo testTryDemo;
//    public TestTryDemo getTestTryDemo() {
//        return testTryDemo;
//    }
//
//    public void setTestTryDemo(TestTryDemo testTryDemo) {
//        this.testTryDemo = testTryDemo;
//    }
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(new AddList(0));
//        Thread thread2 = new Thread(new AddList(1));
//
//    public ThreadTest(TestTryDemo testTryDemo) {
//        this.testTryDemo = testTryDemo;
//    }
//
//    /**
//     * When an object implementing interface <code>Runnable</code> is used to create a thread, starting the thread causes the object's
//     * <code>run</code> method to be called in that separately executing
//     * thread.
//     * <p>
//     * The general contract of the method <code>run</code> is that it may take any action whatsoever.
//     *
//     * @see Thread#run()
//     */
//    @Override
//    public void run() {
//        synchronized (testTryDemo) {
//            Integer countNum = testTryDemo.getCountNum();
//            while (countNum <= 52) {
//                countNum++;
//                testTryDemo.setCountNum(countNum);
//                if(numFlag) {
//                    System.out.println("Out num = " + countNum);
//                    if(countNum % 2 == 0){
//                        numFlag = false;
//                        object.notifyAll();
//                    }
//                } else {
//                    try {
//                        object.wait();
//                    }catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//}
