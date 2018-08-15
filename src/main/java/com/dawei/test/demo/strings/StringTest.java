package com.dawei.test.demo.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by Dawei on 2018/6/10.
 */
public class StringTest {

    public static void main(String[] args) {
        String str = "你想説些生麽#$#就説寫生麽吧";
        String[] result = str.split("#\\$#");


        String teplate = "asabdb${V1}${v2}sdv${c2}";
        String temp = teplate;
        while (temp.contains("$")) {
            int length = temp.length();

            int i = temp.indexOf("${");
            int j = temp.indexOf("}");
            String substring = temp.substring(i + 2, j);
            System.out.println(("i   " + i + "   j  " + j + "  sub=" + substring));
            temp = temp.substring(j+1, length);

        }



    }
}
