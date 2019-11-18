package com.dawei.test.demo.file;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dawei on 2018/4/21.
 */
public class FileDemo {
    private static final long DAY_MILLIS = 1000 * 24 * 60 * 60L;

    @Test
    public void testMethod() {
        String fileName = "asv";

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String lastDateStr = format.format(new Date(System.currentTimeMillis() - DAY_MILLIS));

        String delFilePath = fileName + lastDateStr + ".*\\.csv";
        System.out.println(delFilePath);


//
//        InetAddress inetAddress = null;
//
//        try {
//            inetAddress = InetAddress.getLocalHost();
//
//            String hostAddress = inetAddress.getHostAddress();
//            String hostName = inetAddress.getHostName();
//            System.out.println(hostAddress);
//            System.out.println(hostName);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//
//
//        String filepath = "D:\\Work_Space\\TestPath\\123.txt";
//        File file = new File(filepath);
//        if(file.exists()) {
//            System.out.println("文件存在");
//        } else {
//            System.out.println("文件不存在。。。 创建它");
//            try {
//                System.out.println(file.createNewFile());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }


}
