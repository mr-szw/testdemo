package com.dawei.test.demo.bloomfilter;

import java.util.UUID;

import com.dawei.test.demo.bloomfilter.MyBloomFilter.MisjudgmentRate;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author by Dawei on 2019/11/23.
 */
public class GuavaBloomFilterDemo {

	public static void main(String[] args) throws Exception {

		BloomFilter<Long> userIdBloomFilter = BloomFilter.create(Funnels.longFunnel(), 100000000,
				0.001);
	}
}
