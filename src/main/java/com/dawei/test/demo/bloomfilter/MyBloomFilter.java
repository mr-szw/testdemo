package com.dawei.test.demo.bloomfilter;

import java.io.Serializable;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 布隆过滤器
 *
 * @author by Dawei on 2019/11/23.
 */
public class MyBloomFilter implements Serializable {

    private static final long serialVersionUID = 8746284993613083826L;

    private final int[] hashSeeds;

    //过滤器长度 容量
    private final int size;

    //数据存储集合
    private final BitSet noteBook;

    //设置误判率， 通过误判率来调整hash次数
    private final MisjudgmentRate misjudgmentRate;

    //存储计数器
    private final AtomicInteger usedCount = new AtomicInteger(0);

    private final Double maxCoverFactor;


    /**
     * 设置过滤器容量
     */
    public MyBloomFilter(int filterSize) {
        this(filterSize, MisjudgmentRate.MIDDLE, null);
    }


    public MyBloomFilter(int filterSize, MisjudgmentRate misjudgmentRate, Double maxCoverFactor) {

        this.hashSeeds = misjudgmentRate.getHashSeeds();

        long bizSize = this.hashSeeds.length * filterSize;
        if (bizSize < 0 || bizSize > Integer.MAX_VALUE) {
            throw new RuntimeException("filter size illegal");
        }
        this.size = (int) bizSize;
        this.noteBook = new BitSet(size);
        this.misjudgmentRate = misjudgmentRate;
        this.maxCoverFactor = maxCoverFactor;
    }


    public void add(String data) throws Exception {
        checkFull();
        for (int hashSeed : this.hashSeeds) {
            int index = getHashCode(data, hashSeed);
            setBitBookPoint(index);
        }
    }

    public boolean checkExist(String data) {
        for (int hashSeed : this.hashSeeds) {
            int index = getHashCode(data, hashSeed);
            if (!this.noteBook.get(index)) {
                return false;
            }
        }
        return true;
    }

    public boolean addIfNoExist(String data) throws Exception {
        checkFull();
        boolean exist = false;
        for (int hashSeed : this.hashSeeds) {
            int index = getHashCode(data, hashSeed);
            if (!this.noteBook.get(index)) {
                exist = true;
                setBitBookPoint(index);
            }
        }
        return exist;
    }

    public void clear() {
        this.usedCount.set(0);
        this.noteBook.clear();
    }

    private void checkFull() {

        if (this.maxCoverFactor != null) {
            if (this.maxCoverFactor <= getUseCountRate()) {
                throw new RuntimeException("Out set max cover factor");
            }
        }
    }


    private double getUseCountRate() {
        return this.usedCount.get() / this.size;
    }

    private void setBitBookPoint(int index) {
        this.usedCount.incrementAndGet();
        this.noteBook.set(index, true);
    }


    public String getNoteBook() {
        return new String(this.noteBook.toByteArray());
    }

    private int getHashCode(String date, int hashSeed) {
        char[] dataChars = date.toCharArray();
        int hash = 0;
        if (dataChars.length > 0) {
            for (int i = 0; i < dataChars.length; i++) {
                //瞎捣鼓的 但是真的是快了不少
                hash = (hash = i * hash + hashSeed + dataChars[i]) ^ (hash >>> 16);
            }
        }
        hash = hash * hashSeed % this.size;
        //防止溢出int 值 结果溢出成为复数
        return Math.abs(hash);
    }

    /**
     * 误判率设置 是 空间与正确率的博弈
     */
    public enum MisjudgmentRate {

        //4
        VERY_SMALL(new int[]{2, 3, 5, 7}),
        //8
        SMALL(new int[]{2, 3, 5, 7, 11, 13, 17, 19}),
        //16
        MIDDLE(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53}),
        HIGH(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
            61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131});


        private int[] hashSeeds;

        MisjudgmentRate(int[] hashSeeds) {
            this.hashSeeds = hashSeeds;
        }

        public int[] getHashSeeds() {
            return hashSeeds;
        }
    }
}
