package com.dawei.test.demo.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
 * @author by Dawei on 2018/8/12.
 *  EhCache 主体内容
 *  1、CacheManager：Cache的管理类；
 *  2、Cache：具体的cache类信息，负责缓存的get和put等操作
 *  3、CacheConfiguration ：cache的配置信息，包含策略、最大值等信息
 *  4、Element：cache中单条缓存数据的单位
 *
 *  支持集群 数据同步可使用框架的JGroup实现同步
 *
 */
public class EhCacheDemo {


    public static void main(String[] args) {

        //创建EhCache 的缓存管理器 CacheManager  现在支持多例
        //CacheManager cacheManager = new CacheManager();
        CacheManager cacheManager = CacheManager.getInstance();

        //缓存配置 也可以使用ehcache.xml进行配置 缓存配置对象可以针对指定的缓存对象，Cache Manager可管理多个缓存对象
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        /*
        *    缓存对象信息配置 缓存配置信息的所有信息 均可配置 均有默认值
        *    EhCache 功能强大
         */
        //配置缓存对象名
        cacheConfiguration.name("cache_name_default");
        //堆空间中最大的存储对象Element的数目
        cacheConfiguration.maxEntriesLocalHeap(1000);
        //设置最大缓存条目数量
        //cacheConfiguration.maxEntriesInCache(10000L);
        //设置失效策略
        cacheConfiguration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU);


        //通过配置信息创建一个缓存实例
        Cache localCache = new Cache(cacheConfiguration);

        //将缓存实例交由CacheManager管理
        cacheManager.addCache(localCache);

        //存储 数据实体 Element
        localCache.put(new Element("key", "value"));

        System.out.println("localCache.getSize() = " + localCache.getSize());
        System.out.println("localCache.getStatistics() = " + localCache.getStatistics().toString());
        System.out.println("localCache.getName() = " + localCache.getName());
        System.out.println("localCache.get(key) = " + localCache.get("key"));
        System.out.println("localCache.get(key) = " + localCache.get("key").getObjectValue());


    }
}
