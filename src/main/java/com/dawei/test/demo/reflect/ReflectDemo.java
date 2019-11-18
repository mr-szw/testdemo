package com.dawei.test.demo.reflect;

import com.dawei.test.demo.pojo.DemoPojo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author by Dawei 2018/7/19
 */
public class ReflectDemo {

    /*
     *
     * JVM装在类的时候使用“全盘负责委托原则”，当一个ClassLoader装载一个类的时候这个类的引用类和依赖类都将由这个类加载器加载，除非有显式地指定了另一个ClassLoader
     * 委托机制 即先委托父装载器先寻找类目标，当其找不到时再从自己的 类路径下查找并装载目标类。
     * */

    public static void main(String[] args) {

        //1、获取类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        /* 类加载器：
         *       AppClassLoader 加载ClassPath下的类 是ClassLoader的子类
         *       ExtClassLoader 加载 jre之外的扩展类 例如：引入的JAR中的类 是ClassLoader的子类
         *       根装载器 加载 jre中的类 是C++语言编写 java中看不见
         */
        /* 当前类的加载器 */
        System.out.println("Curent classLoader:=> " + classLoader);
        /* 加载当前类加载器的加载器 */
        System.out.println("Curent classLoader `s loader:=> " + classLoader.getParent());
        /* 加载当前类加载器的加载器的加载器 */
        System.out.println("Curent classLoader `s loader `s loader :=> " + classLoader.getParent().getParent());

        Class<?> clazz = null;
        try {
            //2、通过类加载器获取类类型
            clazz = classLoader.loadClass("com.dawei.test.demo.pojo.DemoPojo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2、获取类的默认构造器对象 并用它实例化实体对象
        DemoPojo demoPojo = null;
        if (clazz != null) {
            try {
                //3、通过类类型获取无参构造器
                Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
                //4、通过无参构造器获取类对象
                demoPojo = (DemoPojo) declaredConstructor.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                //5、通过类类型获取类的方法 (方法名 参数类型，参数类型为数组类型)
                Method setName = clazz.getMethod("setName", String.class);
                //6、反射进行方法的调用
                setName.invoke(demoPojo, "小明");


                //7、获取类的属性
                Field birthday = clazz.getDeclaredField("birthday");
                //8、绕开私有属性权限  私有方法同样
                birthday.setAccessible(true);
                //9、对属性设值
                birthday.set(demoPojo, new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(demoPojo.toString());
        }


    }
}
