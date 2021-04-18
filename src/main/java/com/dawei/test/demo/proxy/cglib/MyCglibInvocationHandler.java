package com.dawei.test.demo.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author sunzhiwei <sunzhiwei@kuaishou.com>
 * Created on 2021-04-18
 */
public class MyCglibInvocationHandler implements MethodInterceptor {



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("My cglib proxy do before run");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("My cglib proxy do after run");
        return invoke;
    }
}
