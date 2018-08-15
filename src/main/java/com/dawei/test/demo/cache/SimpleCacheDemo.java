package com.dawei.test.demo.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by Dawei on 2018/8/12.
 *
 * 简单的单例模式实现 本地缓存策略
 *
 * 特点：
 *  1、没有缓存大小的设置，无法限定缓存体的大小以及存储数据的限制；
 *  2、持久性存储在jvm 中；
 *  3、没有缓存的失效策略, 会一直存下去, 危险；
 *  4、没有弱键引用，在内存占用吃紧的情况下，JVM是无法回收的；
 */
public class SimpleCacheDemo {


    /* 做本地存储的Map  */
    private Map<String, Object> localStore = new HashMap<>();


    /* 饿汉形式 开始就创建 对象单例 */
    private static SimpleCacheDemo simpleCacheDemo = new SimpleCacheDemo();

    /* 声明成私有的对象类型 保证 外部无法创建 */
    private SimpleCacheDemo() {
    }

    /* 开放非外部的 可调用的实例方法 */
    public SimpleCacheDemo getInstance() {
        return simpleCacheDemo;
    }

    /* 存值 */
    public void putValue(String key, Object value) {
        localStore.put(key, value);
    }

    /* 取值 */
    public Object getValue(String key) {
        return localStore.get(key);
    }

}
