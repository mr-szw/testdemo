package com.dawei.test.demo.threaddemo;

/**
 * Created by Dawei on 2018/3/25.
 */
public class ThreadTest2 implements Runnable{


    private static Object object = new Object();



    private static volatile Boolean numFlag = true;

    private TestTryDemo testTryDemo;
    public TestTryDemo getTestTryDemo() {
        return testTryDemo;
    }

    public void setTestTryDemo(TestTryDemo testTryDemo) {
        this.testTryDemo = testTryDemo;
    }

    public ThreadTest2(TestTryDemo testTryDemo) {
        this.testTryDemo = testTryDemo;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        synchronized (object) {
            Integer countNum = testTryDemo.getCountNum();
            while (countNum <= 26) {
                countNum++;
                testTryDemo.setCountNum(countNum);
                if(numFlag) {
                    System.out.println("Letters is  = " + ((char) (countNum % 26 + (int) 'A')));
                    if(countNum % 3 == 0){
                        numFlag = false;
                        object.notifyAll();
                    }
                } else {
                    try {
                        object.wait();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
