package com.dawei.test.demo.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author sunzhiwei <sunzhiwei@kuaishou.com>
 * Created on 2021-04-18
 */
public class MyJdkInvocationHandler implements InvocationHandler {

    /**
     * 目标对象
     */
    private Object target;

    public MyJdkInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("MyJdk proxy do before run");
        Object invoke;
        try {
            invoke = method.invoke(target, args);
        } catch (InvocationTargetException invocationTargetException) {
            /*
                这里的异常为啥会需要get target
                invoke在正经执行的时候会返回原本借口定义的异常，若没有显示标记那么不算，要是有的话会主动上抛，但是会被proxy包装成invocationTargetException
                通过 getTargetException可以获取原本标记的异常
             */

            return invocationTargetException.getTargetException();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        System.out.println("MyJdk proxy do after run");
        return invoke;
    }
}
