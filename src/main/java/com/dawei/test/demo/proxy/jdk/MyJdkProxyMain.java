package com.dawei.test.demo.proxy.jdk;

import java.lang.reflect.Proxy;

import com.dawei.test.demo.proxy.BusinessService;
import com.dawei.test.demo.proxy.BusinessServiceImpl;

/**
 * @author sunzhiwei <sunzhiwei@kuaishou.com>
 * Created on 2021-04-18
 */
public class MyJdkProxyMain {


    public static void main(String[] args) {


        // =========================第一种==========================
        // 1、生成$Proxy0的class文件
        // 2、获取动态代理类
        // 3、获得代理类的构造函数，并传入参数类型InvocationHandler.class
        // 4、通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        // 5、通过代理对象调用目标方法

        // 设置JDK Proxy生成的代理类输出到文件中
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        BusinessService proxyInstance = (BusinessService) Proxy
                .newProxyInstance(BusinessService.class.getClassLoader(), new Class[] {BusinessService.class},
                        new MyJdkInvocationHandler(new BusinessServiceImpl()));

        System.out.println(proxyInstance.businessMethod("Test"));
        System.out.println(proxyInstance.businessMethod(null));
    }
}
