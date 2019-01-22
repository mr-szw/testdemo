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
        str = "";
        String[] split = str.split(",");
        for(String s : split) {
            System.out.println(s);
        }
        String[] strs = new String[1];
        //strs[0] = "1";
       /* strs[1] = "1";
        strs[2] = "1";*/
        System.out.println(" ______________" + String.join("@", strs));
        System.out.println(Arrays.toString(split));

        String teplate = "asabdb${V1}${v2}sdv${c2}";
        System.out.println(teplate.substring(0, teplate.length() - 1));
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
