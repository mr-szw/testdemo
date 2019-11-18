package com.dawei.test.demo.random;

import java.util.Date;
import java.util.Random;

/**
 * @author by Dawei on 2018/7/18.
 */
public class RandomDemo {


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            double random = Math.random();
//            System.out.println(random);
        }
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {

            int j = random.nextInt(5);
            System.out.println(j);
        }


    }
}
