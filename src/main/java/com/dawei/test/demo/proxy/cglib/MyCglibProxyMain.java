package com.dawei.test.demo.proxy.cglib;

import com.dawei.test.demo.proxy.BusinessServiceImpl;

/**
 * @author sunzhiwei <sunzhiwei@kuaishou.com>
 * Created on 2021-04-18
 */
public class MyCglibProxyMain {


    public static void main(String[] args) {


        MyCglibInvocationHandler myCglibInvocationHandler = new MyCglibInvocationHandler();
        BusinessServiceImpl proxyInstance =
                (BusinessServiceImpl) myCglibInvocationHandler.createProxyObject(new BusinessServiceImpl());


        System.out.println(proxyInstance.businessMethod("Test"));



        System.out.println("------ ");


        System.out.println(proxyInstance.businessMethod(null));
    }
}
