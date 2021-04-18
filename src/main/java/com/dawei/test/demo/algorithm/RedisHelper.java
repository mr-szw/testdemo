package com.dawei.test.demo.algorithm;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author Dawei on 2018/11/28.
 */
public class RedisHelper {

	private static final Logger logger = LoggerFactory.getLogger(RedisHelper.class);

	private static final String FAILED_SETKEY = "redis set failed,key ={}. e=";
	private static final String NULL_KEY = "redis key must not be null";
	private static final String NULL_VALUE = "redis value  not be null";

	// private static JedisSentinelPool jedisSentinelPool =
	// SpringWrapper.getBean(JedisSentinelPool.class);
	private static JedisSentinelPool jedisSentinelPool;

	private RedisHelper() {
	}

	/**
	 * 包装get命令
	 *
	 * @param key 待查询的key，非空
	 * @return String
	 */
	public static String get(String key) {
		Assert.hasLength(key, NULL_KEY);
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			logger.error("redis get failed,key =" + key, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 包装set命令
	 *
	 * @param key key
	 * @param value value
	 * @return boolean
	 */
	public static boolean set(String key, String value) {
		Assert.hasLength(key, NULL_KEY);
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			String ret = jedis.set(key, value);
			return "ok".equalsIgnoreCase(ret);
		} catch (Exception e) {
			logger.error(FAILED_SETKEY, key, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 包装待失效时间的set命令
	 *
	 * @param key key
	 * @param value value
	 * @return boolean
	 */
	public static boolean set(String key, String value, int seconds) {
		Assert.hasLength(key, NULL_KEY);
		Jedis jedis = null;
		try {
			if (seconds > 0) {
				jedis = jedisSentinelPool.getResource();
				String ret = jedis.set(key, value);
				jedis.expire(key, seconds);
				return "ok".equalsIgnoreCase(ret);
			} else {
				return set(key, value);
			}
		} catch (Exception e) {
			logger.error(FAILED_SETKEY + key, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	public static boolean rpush(String key, String value, int seconds) {
		Assert.hasLength(key, NULL_KEY);
		Assert.hasLength(value, NULL_VALUE);
		Jedis jedis = null;
		try {
			if (seconds > 0) {
				jedis = jedisSentinelPool.getResource();
				Long rPush = jedis.rpush(key, value);
				jedis.expire(key, seconds);
				return (rPush != null && rPush > 0);
			} else {
				return rpush(key, value);
			}
		} catch (Exception e) {
			logger.error(FAILED_SETKEY + key, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	public static boolean rpush(String key, String value) {
		Assert.hasLength(key, NULL_KEY);
		Assert.hasLength(value, NULL_VALUE);
		Jedis jedis = null;
		try {

			jedis = jedisSentinelPool.getResource();
			Long rPush = jedis.rpush(key, value);
			return (rPush != null && rPush > 0);

		} catch (Exception e) {
			logger.error(FAILED_SETKEY + key, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 封装del命令
	 *
	 * @param key 待删除的key
	 * @return boolean
	 */
	public static boolean del(String key) {
		Assert.hasLength(key, NULL_KEY);
		Long removedSize = 0L;
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			removedSize = jedis.del(key);
		} catch (Exception e) {
			logger.error("redis del failed,key = " + key, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return removedSize > 0;
	}

	/**
	 * 通过前缀获取 Key 集合
	 *
	 * @param keyPre keyPre
	 */
	public static Set<String> getKeys(String keyPre) {
		Set<String> keySet = null;
		Assert.hasLength(keyPre, NULL_KEY);
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			keySet = jedis.keys(keyPre + "*");
		} catch (Exception e) {
			logger.error("redis del failed,keyPre = {}", keyPre, e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return keySet;
	}

}