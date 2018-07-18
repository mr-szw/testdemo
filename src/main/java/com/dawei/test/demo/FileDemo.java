package com.dawei.test.demo;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Dawei on 2018/4/21.
 */
public class FileDemo {

    @Test
    public void testMethod() {


        InetAddress inetAddress = null;

        try {
            inetAddress = InetAddress.getLocalHost();

            String hostAddress = inetAddress.getHostAddress();
            String hostName = inetAddress.getHostName();
            System.out.println(hostAddress);
            System.out.println(hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        String filepath = "D:\\Work_Space\\TestPath\\123.txt";
        File file = new File(filepath);
        if(file.exists()) {
            System.out.println("文件存在");
        } else {
            System.out.println("文件不存在。。。 创建它");
            try {
                System.out.println(file.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
