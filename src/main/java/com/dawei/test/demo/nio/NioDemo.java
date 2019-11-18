package com.dawei.test.demo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author by Dawei on  2018/8/15.
 * <p>
 * 了解NIO 实现原理与运行过程
 * NIO 多路复用器
 */
public class NioDemo {


    //文件读取例子
    public static void simpleIOMethod(String sourceFilePath, String destFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File descFile = new File(destFilePath);
        if (!descFile.exists()) {
            boolean newFileResult = descFile.createNewFile();
            System.out.println("descFile.createNewFile result: " + newFileResult);
        }

        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        FileOutputStream fileOutputStream = new FileOutputStream(descFile);

        byte[] buffer = new byte[512];

        int len = 0;
        while ((len = fileInputStream.read()) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
        fileOutputStream.close();
        fileInputStream.close();

    }

    //文件读取例子
    public static void nioMethod(String sourceFilePath, String destFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File descFile = new File(destFilePath);
        if (!descFile.exists()) {
            boolean newFileResult = descFile.createNewFile();
            System.out.println("descFile.createNewFile result: " + newFileResult);
        }

        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        FileOutputStream fileOutputStream = new FileOutputStream(descFile);

        /*  通道  */
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

        fileOutputStream.close();
        fileInputStream.close();

    }


}
