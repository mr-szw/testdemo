package com.dawei.test.demo;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.http.Consts;
import org.junit.Test;

/**
 * @author Dawei  on 2018/3/25.
 */
public class DemoTestMain {


    @Test
    public void testMethod() throws Throwable {

        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Consts.UTF_8), 1);

    }
}
