package com.dawei.test.demo.reflect;

import com.dawei.test.demo.pojo.DemoPojo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: Dawei
 * @Date: 2018/7/19
 */
public class ReflectDemo {


    public static void main(String[] args) {

        //1、通过类加载器获取类对象
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = null;
        try {
            clazz = classLoader.loadClass("com.dawei.test.demo.pojo.DemoPojo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2、获取类的默认构造器对象 并用它实例化实体对象
        DemoPojo demoPojo = null;
        if(clazz != null) {
            try {
                Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
                demoPojo = (DemoPojo) declaredConstructor.newInstance();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }



    }
}
