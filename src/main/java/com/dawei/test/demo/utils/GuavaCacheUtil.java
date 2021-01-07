package com.dawei.test.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.util.CollectionUtils;

import com.dawei.test.demo.future.TaskGetSupplier;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.Getter;

/**
 * @author by Dawei on 2020/5/11
 */
public class GuavaCacheUtil<T> {


	private TaskGetSupplier<T> taskGetSupplier;

	/* 静态单例对象 */
	private volatile static GuavaCacheUtil guavaCacheDemo = null;
	private volatile static ConcurrentHashMap<String, LoadingCache<?,?>> loadingCacheConcurrentHashMap = new ConcurrentHashMap<>();

	/* 缓存 实例 */
	@Getter
	private LoadingCache<String, Object> loadingCache;

	/* 懒汉式 双重锁 */
	public static synchronized GuavaCacheUtil getInstance() {
		if (guavaCacheDemo == null) {
			synchronized (GuavaCacheUtil.class) {
				if (guavaCacheDemo == null) {
					guavaCacheDemo = new GuavaCacheUtil<>();
				}
			}
		}
		return guavaCacheDemo;
	}

	private GuavaCacheUtil() {

	}

	/* 单例 初始化 */
	private GuavaCacheUtil(String cacheName, int maxSize, long expireAfterWrite, long expireAfterAccess, TaskGetSupplier<T> taskGetSupplier) {
		this.taskGetSupplier = taskGetSupplier;

		initCacheLoading(cacheName, maxSize, expireAfterWrite, expireAfterAccess);
	}

	/* 初始化 缓存 */
	private synchronized LoadingCache<String, Object> initCacheLoading(String cacheName, int maxSize, long expireAfterWrite, long expireAfterAccess ) {
		loadingCacheConcurrentHashMap.put(cacheName, this.loadingCache);
		LoadingCache<String, Object> build = CacheBuilder.newBuilder().maximumSize(maxSize)
				.expireAfterWrite(expireAfterWrite, TimeUnit.MILLISECONDS)
				.expireAfterAccess(expireAfterAccess, TimeUnit.MILLISECONDS)
				.weakKeys()
				.weakValues()
				.recordStats()
				.removalListener(removalNotification ->
						loadAndPutValue(removalNotification.getKey().toString())
				).build(new CacheLoader<String, Object>() {
					@Override
					public Object load(String keyName) throws Exception {
						return loadValue(keyName);
					}
				});
		return build;

	}

	/* 塞值 */
	public void putValue(String key, Object value) throws Exception {
		loadingCache.put(key, value);
	}

	/* 获取值 */
	public Object getValue(final String key) throws Exception {
		return loadingCache.get(key, () -> taskGetSupplier.get(key));
	}

	private String loadValue(String key) {
		System.out.println("load value out method" + key);
		return key + System.currentTimeMillis();
	}


	/**
	 * 从外部加载并存储值
	 *
	 * @param key
	 * @return
	 */
	private String loadAndPutValue(String key) {
		System.out.println("load value out method" + key);
		String value = loadValue(key);
		loadingCache.put(key, value);
		return value;
	}

	/* 获取值 */
	public Long getSize() throws Exception {
		return loadingCache.size();
	}

	/**
	 * 可以调用Cache的invalidateAll或invalidate方法显示删除Cache中的记录
	 */
	public void invalidateCache(List<String> invalidateKeyList) {

		if (CollectionUtils.isEmpty(invalidateKeyList)) {
			loadingCache.invalidateAll();
		} else {
			loadingCache.invalidateAll(invalidateKeyList);
		}
	}

	public void invalidateCache(String invalidateKey) {
		loadingCache.invalidate(invalidateKey);
	}


}
