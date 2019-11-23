package com.dawei.test.demo;

import com.dawei.test.demo.bloomfilter.BloomFilter;
import com.dawei.test.demo.bloomfilter.BloomFilter.MisjudgmentRate;
import java.util.UUID;
import org.junit.Test;

/**
 * @author Dawei  on 2018/3/25.
 */
public class DemoTestMain {


    @Test
    public void testMethod() throws Throwable {
        BloomFilter bloomFilter = new BloomFilter(100, MisjudgmentRate.MIDDLE, 0.8);

        int i = 9000;
        String dataTemp = UUID.randomUUID().toString();
        for (;i-- > 0;) {
            String data = UUID.randomUUID().toString();
            bloomFilter.add(data);
        }
        bloomFilter.add(dataTemp);

        System.out.println(bloomFilter.checkExist(dataTemp));

        int j = 100000;
        int failedCount = 0;
        int dataCount = 0;
        for (;j-- > 0;) {

            String dataA = UUID.randomUUID().toString();
            boolean checkExist = bloomFilter.checkExist(dataA);
            dataCount++;
            if (checkExist ){
                System.out.println(String.format("Count : %s filedNum = %s ", dataCount , ++failedCount));
            }
        }
    }


    /**
     * 第一个异常数据
     * @throws Throwable
     */
    private void firstFaild() throws Throwable{
        BloomFilter bloomFilter = new BloomFilter(10000, MisjudgmentRate.MIDDLE, null);

        int failedCount = 0;
        int dateCount = 0;
        while (true) {
            String data = UUID.randomUUID().toString();
            dateCount++;
            if(bloomFilter.checkExist(data)) {
                System.out.println(String.format("Count : %s filedNum = %s failedData= %s", dateCount , ++failedCount, data));
                break;
            }
            bloomFilter.add(data);
        }
    }
}
