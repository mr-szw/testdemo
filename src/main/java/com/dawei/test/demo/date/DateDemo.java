package com.dawei.test.demo.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author by Dawei on 2018/6/11.
 */
public class DateDemo {


    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Date date = new Date(1525335058000L);
        System.out.println(date.toString());
        System.out.println(" currentTimeMillis" + System.currentTimeMillis());

        Date parse = null;
        try {
            parse = dateFormat.parse("2018-10-26 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(parse.getTime());

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.atStartOfDay());

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }
}
