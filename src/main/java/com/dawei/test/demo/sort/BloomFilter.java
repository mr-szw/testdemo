//package com.dawei.test.demo.sort;
//
//import com.dawei.test.demo.utils.MD5Util;
//import java.util.BitSet;
//
//public class BloomFilter implements visitedFrontier {
//
//    private static final int DEFAULT_SIZE = 2 << 24;
//    private static final int[] seeds = new int[]{7, 11, 13, 19, 23, 31, 37, 61};
//    private BitSet bits = new BitSet(DEFAULT_SIZE);//二进制列表32M
//    private Hash[] func = new Hash[seeds.length];  //8个哈希函数
//
//    private static int size = 0;//保存已经插入的元素个数
//
//    public BloomFilter() {
//        for (int i = 0; i < seeds.length; i++) {
//            func[i] = new Hash(DEFAULT_SIZE, seeds[i]);
//        }
//    }
//
//    @Override
//    public void put(Url url) {
//        // TODO Auto-generated method stub
//        if (url != null) {
//            put(url.getOriUrl());
//        }
//    }
//
//    @Override
//    public void put(String value) {
//        // TODO Auto-generated method stub
//        size++;
//        for (Hash h : func)//映射位置true
//        {
//            bits.set(h.getHash(caculateUrl(value)), true);
//        }
//    }
//
//    @Override
//    public boolean contains(Url url) {
//        // TODO Auto-generated method stub
//        return contains(url.getOriUrl());
//    }
//
//    @Override
//    public boolean contains(String value) {
//        // TODO Auto-generated method stub
//        if (value == null) {
//            return false;
//        }
//
//        boolean ret = true;
//        for (Hash h : func)//检测每一个映射到的bit位是否为true
//        {
//            ret &= bits.get(h.getHash(caculateUrl(value)));
//        }
//        return ret;
//    }
//
//    public static class Hash {
//
//        private int cap;//保证映射范围在BitSet内
//        private int seed;
//
//        public Hash(int cap, int seed) {
//            this.cap = cap;
//            this.seed = seed;
//        }
//
//        public int getHash(String value) {
//            int result = 0;
//            for (int i = 0; i < value.length(); i++) {//没一位加权相加
//                result = seed * result + value.charAt(i);
//            }
//            return (cap - 1) & result;
//        }
//    }
//
//    private String caculateUrl(String url) {
//        //将没一个url都映射为128个字节的十六进制数，因为有些url相似度很高
//        return MD5Util.MD5Encode(url, "utf-8");
//    }
//
//    public int size() {
//        // TODO Auto-generated method stub
//        return size;
//    }
//
//    public static void main(String[] args) {
//        String value = new String("http://www.baidu.com");
//        BloomFilter filter = new BloomFilter();
//        System.out.println(filter.contains(value));
//        filter.put(value);
//        System.out.println(filter.contains(value));
//
//    }
//}
//
//public interface visitedFrontier {
//
//    public void put(Url url);
//
//    public void put(String value);
//
//    public boolean contains(Url url);
//
//    public boolean contains(String value);
//}