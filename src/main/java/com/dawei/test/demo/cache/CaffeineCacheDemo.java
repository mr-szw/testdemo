package com.dawei.test.demo.cache;



import com.dawei.test.demo.pojo.DemoPojo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by Dawei on 2018/8/10.
 * 本地缓存 caffeine
 * 与应用 同步存活
 *
 * 通过AOP实现
 *  插入操作在 after激活
 *  获取操作在 before之前
 *
 */
@Service
public class CaffeineCacheDemo {


    @CachePut(key = "#demoPojo.uId", value = "demoPojo")
    public DemoPojo save(DemoPojo demoPojo) {
        Map<Long, Object> map = new HashMap<>();
        map.put(demoPojo.getId(), demoPojo);
        return demoPojo;
    }

    @Cacheable(key = "#uId", value = "demoPojo")
    public DemoPojo get(Long uId) {
        DemoPojo demoPojo = new DemoPojo();
        demoPojo.setId(123L);
        System.out.println("2222222222222");
        return  demoPojo;
    }

    @CacheEvict(key = "#uId", value = "demoPojo")
    public void delete(Long uId) {
        System.out.println("DDDDDDD2222222222222");
    }

}
