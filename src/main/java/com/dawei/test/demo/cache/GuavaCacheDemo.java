package com.dawei.test.demo.cache;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * @author by Dawei on 2018/8/12.
 * Guava 提供的缓存构建（不过貌似被Spring嫌弃，具体原因不清楚）
 *
 * Guava 提供：
 *  全量预热 定时移除所有然后全量预热进入换粗
 *  增量预热 查缓存 没有则查库放入缓存
 * 支持集群 不过集群吉安数据同步需要自己实现
 *
 */
public class GuavaCacheDemo {



    /* 静态单例对象 */
    private static GuavaCacheDemo guavaCacheDemo = null;

    /* 缓存 实例 */
    private LoadingCache<String, Object> loadingCache;


    /* 懒汉式 双重锁 */
    public static synchronized GuavaCacheDemo getInstance() {
        if(guavaCacheDemo == null) {
            synchronized (GuavaCacheDemo.class) {
                if(guavaCacheDemo == null) {
                    guavaCacheDemo = new GuavaCacheDemo();
                }
            }
        }
        return guavaCacheDemo;
    }

    /* 单例 初始化 */
    private GuavaCacheDemo() {
        initCacheLoading();
    }

    /* 初始化 缓存 */
    private void initCacheLoading() {

        loadingCache = CacheBuilder.newBuilder()
                .maximumSize(10)  //最大条目数
                .expireAfterWrite(2000L, TimeUnit.SECONDS)  //创建后的失效时间
                .expireAfterAccess(2000L, TimeUnit.SECONDS)  //最后一次访问后的失效时间
                .removalListener( removalNotification -> {   //设置移除监听器
                    /*
                     * removalNotification 删除通知
                     * 包含 移除的Key value 和 移除原因
                     */
                    System.out.println("remove info : key=" + removalNotification.getKey() + " value= " + removalNotification.getValue().toString() + "  cause=  " + removalNotification.getCause().toString());
                })
                .build( new CacheLoader<String, Object>() {  //通过回调加载缓存
                    @Override
                    public Object load(String keyName) throws Exception {
                        System.out.println(keyName);

                        return "load--" + keyName;
                    }
                }
        );
    }


    /* 塞值 */
    public void putValue(String key, Object value)  throws Exception {
        loadingCache.put(key, value);
    }

    /* 获取值 */
    public Object getValue(String key) throws Exception{
        return loadingCache.get(key);
    }

    /* 获取值 */
    public Long getSize()  throws Exception{
        return loadingCache.size();
    }
}
