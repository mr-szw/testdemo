package com.dawei.test.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.dawei.test.demo.utils.EmailSendConfigInfo;
import com.dawei.test.demo.utils.FileUtil;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import sun.misc.BASE64Encoder;

/**
 * @author Dawei  on 2018/3/25.
 */
public class DemoTestMain {

    public static final String SERVICE_WORK_PATH = "/home/sinbad/work//workspace/emailTempPath/";

    class Solution {

        public int removeDuplicates(int[] nums) {
            int length = nums.length;
            for (int i = 1; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    if(nums[i] == nums[j]) {

                    }
                }
            }

            int numCount = 1;
            int temp = nums[0];

            int tempIndex = 0;
            for (int i = 1; i < nums.length - 1; ) {
                if (temp != nums[i]) {
                    numCount++;
                    temp = nums[i];
                } else {
                    tempIndex = i;
                    nums[i] = nums[tempIndex];
                }
            }
            return numCount;
        }
    }


    public static List<String> getFileNameList(String filePathOnService) throws Throwable{
        List<String> fileNameList = new ArrayList<>();
        File file = new File(filePathOnService);
        File[] files = file.listFiles();
        if (files != null) {
            for (File fileInPath : files) {
                if (fileInPath.isFile()) {
                    fileNameList.add(fileInPath.getPath());
                }
            }
        }
        return fileNameList;
    }


    public static List<String> getFilePathList(String filePathOnService) throws Throwable{
        List<String> filePathList = new ArrayList<>();
        File file = new File(filePathOnService);
        File[] files = file.listFiles();
        if (files != null) {
            for (File fileInPath : files) {
                if (fileInPath.isDirectory()) {
                    filePathList.add(fileInPath.getPath());
                }
            }
        }
        return filePathList;
    }

    /**
     * 获取路径下的文件名 全路径 仅名字
     */
    public static List<String> getFileOnlyNameList(String filePathOnService) throws Throwable{
        List<String> fileOnlyNameList = new ArrayList<>();
        List<String> fileNameList = getFileNameList(filePathOnService);
        if (!CollectionUtils.isEmpty(fileNameList)) {
            for (String allFileName : fileNameList) {
                fileOnlyNameList.add(allFileName.substring(allFileName.lastIndexOf("/") + 1));
            }
        }
        return fileOnlyNameList;
    }


    public static String generateQRCodeStreamToFile(String filePath, String content, String boardId) {
        int width = 300;
        int height = 300;
        String format = "png";
        Map<EncodeHintType, Object> hintTypeObjectMap = new HashMap<>();
        hintTypeObjectMap.put(EncodeHintType.CHARACTER_SET, "utf-8");

        File serviceFlePath;
        try {
            serviceFlePath = new File(filePath);
            if (!serviceFlePath.exists()) {
                boolean mkdir = serviceFlePath.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //参数分别为：编码内容、编码类型、图片宽度、图片高度，设置参数
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hintTypeObjectMap);
            File outputFile = new File(filePath + "boardQRCode_" + boardId);
            MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        }catch(Throwable e) {
            e.printStackTrace();
        }
        return filePath + boardId;
    }


    @Test
    public void madin() throws Throwable {

        String a = FileUtil.generateQRCodeStreamToFile("a", "sasa", true, 2);

    }

    // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    public static String getImageBASE64Str(String imgFileAllPath) {
        if(imgFileAllPath == null) {
            return "";
        }
        InputStream inputStream = null;
        // 读取图片字节数组
        byte[] data;
        try {
            inputStream = new FileInputStream(imgFileAllPath);
            data = new byte[inputStream.available()];
            int read = inputStream.read(data);
            //logger.warn("File available size is ={}", read);
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            // 返回Base64编码过的字节数组字符串
            return encoder.encode(data);

        } catch (Throwable e) {
            e.printStackTrace();
            //logger.error("Read image failed, ");
        } finally {
            // 释放资源
            data = null;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable throwable) {
                   // logger.error("inputStream close failed, e=", throwable);
                }
            }
        }
        return null;
    }

    private String getImageForLink(String boardId) {
        String imageBASE64Str = "";
        try {
            // 找之前二维码
            String filePath = null;

            List<String> fileNameWithPathList =
                    getFileNameList(SERVICE_WORK_PATH + "imagePath/");
            if (!CollectionUtils.isEmpty(fileNameWithPathList)) {
                for (String fileNameWithPath : fileNameWithPathList) {
                    if (fileNameWithPath.contains(boardId)) {
                        filePath = fileNameWithPath;
                        break;
                    }
                }
            }
            if (filePath == null) {
                filePath = generateQRCodeStreamToFile(SERVICE_WORK_PATH + "imagePath/",
                        boardId,
                        boardId + "-" + System.currentTimeMillis());
            }
            imageBASE64Str = getImageBASE64Str(filePath);
        } catch (Throwable throwable) {
            //logger.error("Get file info failed, e=", throwable);
        }
        String resultStr = "";
        if (imageBASE64Str != null) {
            resultStr = "<div>请扫描下方二维码，处理圈子中的提案： 强势传送门</div>\n<div><img src='data:image/jpg;base64," + imageBASE64Str
                    + "'/>";
        }

        return resultStr;
    }
}
