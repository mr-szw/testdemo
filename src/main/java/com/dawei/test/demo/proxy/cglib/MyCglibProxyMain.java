package com.dawei.test.demo.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

import com.dawei.test.demo.proxy.BusinessServiceImpl;

/**
 * @author sunzhiwei <sunzhiwei@kuaishou.com>
 * Created on 2021-04-18
 */
public class MyCglibProxyMain {


    public static void main(String[] args) {

        //代理类class文件存入本地磁盘方便我们反编译查看源码
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");

        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(BusinessServiceImpl.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new MyCglibInvocationHandler());
        // 创建代理对象
        BusinessServiceImpl proxyInstance = (BusinessServiceImpl) enhancer.create();


        System.out.println(proxyInstance.businessMethod("Test"));


        System.out.println("------ ");


        System.out.println(proxyInstance.businessMethod(null));
    }
}
