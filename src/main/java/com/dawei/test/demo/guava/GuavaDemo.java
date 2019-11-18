package com.dawei.test.demo.guava;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author by Dawei on 2018/7/31
 */
public class GuavaDemo {


    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("asa", "123", "asa", "454", "sdsd", "45");
//        CharMatcher.
        //Multimap<String, String> multimap  =
        //Joiner.on("&").withKeyValueSeparator("?").join()/**/

        File file = new File("E:\\Tomcat\\apache-tomcat-8.5.31\\conf\\server.xml");
        try {
            List<String> list1 = Files.readLines(file, Charsets.UTF_8);
//            Files.copy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String testStr = "abc  23r we";


        Set<Integer> set = Sets.newHashSet();
        for (int i = 5; i < 10; i++) {
            set.add(i);
        }
        for (int i = 1; i < 5; i++) {
            set.add(i);
        }

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }


    }
}
