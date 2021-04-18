package com.dawei.test.demo.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author sunzhiwei <sunzhiwei@kuaishou.com>
 * Created on 2021-04-18
 */
public class MyCglibInvocationHandler implements MethodInterceptor {

    /**
     * 目标对象
     */
    private Object targetObject;

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        // 返回代理对象
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("My cglib proxy do before run");
        Object invoke = method.invoke(targetObject, objects);

        System.out.println("My cglib proxy do after run");
        return invoke;
    }
}
