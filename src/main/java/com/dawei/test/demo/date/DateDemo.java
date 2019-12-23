package com.dawei.test.demo.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @author by Dawei on 2018/6/11.
 */
public class DateDemo {


    public static void main(String[] args) {

        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("r");
        testList.add("e");
        testList.add("e");
        testList.add("e");
        testList.add("e");

        Map<String, Integer> collect = testList.stream()
                .collect(Collectors.toMap(Function.identity(), item -> 1));

        Date date1 = new Date();
        System.out.println(date1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.DATE, -1);
        String endTimeStr = DateFormatUtils.format(calendar.getTime(), "yyyyMMdd");
        calendar.add(Calendar.DATE, -0);
        String startTimeStr = DateFormatUtils.format(calendar.getTime(), "yyyyMMdd");

        System.out.println(endTimeStr);
        System.out.println(startTimeStr);

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
