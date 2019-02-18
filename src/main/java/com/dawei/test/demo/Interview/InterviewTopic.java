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
        //daffodil();

        //3、斐波那契额数列
        //fibonacci();

        //4、填充九宫格
        ninePalace();
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

    /**
     * 3、斐波那契数列
     */
    private static void fibonacci() {

        System.out.println("请输入需要第几位斐波那契数");
        int inputNum;
        Scanner scanner = new Scanner(System.in);
        inputNum = scanner.nextInt();
        while (inputNum < 1) {
            System.out.println("请输入正确的整数");
            inputNum = scanner.nextInt();
        }
        int i = 0;
        int zero = 0;
        int temp = 1;
        int result = temp;
        System.out.print("斐波那契数列： 1 ");
        while (i < inputNum - 1) {
            result = zero + temp;
            System.out.print(result + " ");
            zero = temp;
            temp = result;
            i++;
        }

        System.out.println("数值为 ： " + result);

    }

    /**
     * 4、九宫格横竖斜和相等 规律： 起始值在首行或首列的中间位置 依次左下或右上移动填充
     */
    private static void ninePalace() {
        System.out.println("请输入需要九宫格的行数（奇数）");
        int inputNum;
        Scanner scanner = new Scanner(System.in);
        inputNum = scanner.nextInt();
        while (inputNum % 2 == 0 || inputNum < 1) {
            System.out.println("请输入正确的正奇数");
            inputNum = scanner.nextInt();
        }

        //定义保存九宫格的数组
        int[][] result = new int[inputNum][inputNum];

        //方式一：
        //行 初始位置
        int row = 0;
        //列 初始位置,因为列由 0 开始，故 inputNum/2 是中间位置
        int col = inputNum / 2;
        for (int i = 1; i <= inputNum * inputNum; i++) {
            System.out.println("Success : Input num row = " + row + ", col = " + col + " num = " + i);
            result[row][col] = i;
            //选址。。。。。 右上移动
            row--;
            col++;
            if (row < 0 && col >= inputNum) {
                System.out.println("行列都越界");
                col--;
                row += 2;
            } else if (row < 0) {
                System.out.println("行越界");
                row = inputNum - 1;
            } else if (col >= inputNum) {
                System.out.println("列越界");
                col = 0;
            } else if (result[row][col] != 0) {
                System.out.println("有冲突 ");
                row += 2;
                col--;
            }
        }

        //打印出九宫格
        for (int i = 0; i < inputNum; i++) {
            for (int j = 0; j < inputNum; j++) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();

        //方式二：
        //行 初始位置
        row = 0;
        //列 初始位置,因为列由 0 开始，故 inputNum/2 是中间位置
        //方式一：
        col = inputNum / 2;
        for (int i = 1; i <= inputNum * inputNum; i++) {
            System.out.println("Success : Input num row = " + row + ", col = " + col + " num = " + i);


            result[row][col] = i;
            if (row == col) {
                break;
            }
            if(result[col][row] == 0) {
                result[col][row] = inputNum * inputNum - i + 1;
            }

            //选址。。。。。 右上移动
            row--;
            col++;
            if (row < 0) {
                row = inputNum - 1;
            } else if (col >= inputNum) {
                col = 0;
            }
        }

        //打印出九宫格
        for (int i = 0; i < inputNum; i++) {
            for (int j = 0; j < inputNum; j++) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
