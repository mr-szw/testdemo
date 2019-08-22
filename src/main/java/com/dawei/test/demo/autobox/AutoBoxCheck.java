package com.dawei.test.demo.autobox;

/**
 * @author by Dawei on 2019/8/21.
 * 自动拆装箱的操作校验
 */
public class AutoBoxCheck {

    public static void main(String[] args) {
        int a = 1;
        Integer aa = 1;

        System.out.println("a aa    " + (a == aa));

        int aA = 200;
        Integer aaA = 200;

        System.out.println("aA aaA    " + (aA == aaA));
        System.out.println("aA E aaA    " + (aaA.equals(aA)));
        Integer aAB = 200;
        Integer aaAB = 200;

        System.out.println("aAB aaAB    " + (aAB == aaAB));


        Integer aABB = 10_1;
        Integer aaABB = 10_1;

        System.out.println("aABB aaABB    " + (aABB == aaABB));
    }
}
