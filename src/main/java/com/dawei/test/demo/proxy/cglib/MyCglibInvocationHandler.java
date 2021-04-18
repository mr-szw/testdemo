package com.dawei.test.demo.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author sunzhiwei <sunzhiwei@kuaishou.com>
 * Created on 2021-04-18
 */
public class MyCglibInvocationHandler implements MethodInterceptor {


    /**
     *
     * @param o 增强的对象 实现借口的对象
     * @param method  被拦截的对象
     * @param objects 参数
     * @param methodProxy 被代理的目标累的方法代理
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("My cglib proxy do before run");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("My cglib proxy do after run");
        return invoke;
    }
}
