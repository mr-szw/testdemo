package com.dawei.test.demo.Interview;

import java.util.Scanner;

/**
 * @author by Dawei on 2019/2/15.
 *
 * 经典题目100列
 */
public class InterviewTopic {


    public static void main(String[] args) {

        //1、闰年
        //leapYear();

        //2、水仙花数
        daffodil();
    }


    /**
     * 1、闰年 是4的整数倍不是100 的整数倍 儿400 的整数倍是闰年
     */
    private static void leapYear() {

        System.out.println("输入需要校验的年份[0~3000]");
        Integer inputYear = null;
        Scanner scanner = new Scanner(System.in);
        inputYear = scanner.nextInt();
        while (inputYear < 1 || inputYear > 3000) {

            System.out.println("请输入需要校验的年份[0~3000]");
            inputYear = scanner.nextInt();
        }

        if ((inputYear % 4 == 0 && inputYear % 100 != 0) || (inputYear % 400 == 0)) {
            System.out.println(inputYear + " 是闰年");
        } else {
            System.out.println(inputYear + " 不是闰年");
        }
    }


    /**
     * 2、水仙花数 三位数 每个数的立方和等于这个三位数
     */
    private static void daffodil() {
        System.out.println("请输入三位数整数");
        Integer inputNum = null;
        Scanner scanner = new Scanner(System.in);
        inputNum = scanner.nextInt();
        while (inputNum < 100 || inputNum > 999) {
            System.out.println("请输入正确的三位整数");
            inputNum = scanner.nextInt();
        }

        int hundred = inputNum / 100;
        int ten = (inputNum - hundred * 100) / 10;
        int one = (inputNum - hundred * 100 - ten * 10);

        System.out.println("hundred " + hundred + " ten " + ten + " one " + one);
        if (hundred * hundred * hundred + ten * ten * ten + one * one * one == inputNum) {
            System.out.println(inputNum + " 是水仙花数");
        } else {
            System.out.println(inputNum + " 不是水仙花数");
        }
    }
}
