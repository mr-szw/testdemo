package com.dawei.test.demo.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author by Dawei on 2018/6/11.
 */
public class DateDemo {


    public static void main(String[] args) {
        Date date = new Date(1525335058000L);
        System.out.println(date.toString());


        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.atStartOfDay());

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }
}
