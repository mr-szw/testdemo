package com.dawei.test.demo.hystrix;

import com.alibaba.fastjson.JSON;

import rx.Observable;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Dawei 2019/1/8
 */
public class MainClientDemoTest {


    public static void main(String[] args) {
        HystrixTestDemo hystrixTestDemo = new HystrixTestDemo("a");
        //1、阻塞执行
        List<String> execute = hystrixTestDemo.execute();
        System.out.println("execute:" + JSON.toJSONString(execute));

        //2、异步执行
        Future<List<String>> listFuture = hystrixTestDemo.queue();
        try {
            if (listFuture.isDone()) {
                //2.1 get 时阻塞 等待返回
                List<String> list = listFuture.get();
                System.out.println("queue  执行完 " + JSON.toJSONString((list)));
            } else {
                boolean cancel = listFuture.cancel(true);

                System.out.println("queue 已经执行取消 执行完， 取消操作， cancel= " + cancel);

                if (listFuture.isCancelled()) {
                    System.out.println("queue 已经取消完成");
                } else {
                    System.out.println("取消失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //2.2 执行超时  -->  中断 返回
            System.out.println("中断指定返回值 ");
        }
        Observable<List<String>> observe = hystrixTestDemo.observe();


    }
}
