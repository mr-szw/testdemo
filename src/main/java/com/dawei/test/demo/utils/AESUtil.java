package com.dawei.test.demo.utils;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {


    private static final String KEY_AES = "AES";
    // 16
    private static final String SALT_KEY = "USER-CENTER-MIUI";

    // 加密
    public static String encryptOpen(String textContext) throws Exception {
        return encrypt(textContext);
    }

    // 解密
    public static String decryptOpen(String textContext) throws Exception {
        return decrypt(textContext);
    }

    private static String encrypt(String textContext) throws Exception {

        SecretKeySpec skewySpec = new SecretKeySpec(SALT_KEY.getBytes(), KEY_AES);
        Cipher cipher = Cipher.getInstance(KEY_AES);
        cipher.init(Cipher.ENCRYPT_MODE, skewySpec);

        byte[] encrypted = cipher.doFinal(textContext.getBytes());
        return byteToHex(encrypted);
    }

    private static String decrypt(String textContext) throws Exception {
        SecretKeySpec skewSpec = new SecretKeySpec(SALT_KEY.getBytes(), KEY_AES);
        Cipher cipher = Cipher.getInstance(KEY_AES);
        cipher.init(Cipher.DECRYPT_MODE, skewSpec);

        byte[] encrypted = parseHexStr2Byte(textContext);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original);
    }


    /**
     * 将16进制转换为二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr == null || hexStr.length() < 1)
            return null;
        int l = hexStr.length();
        byte[] result = new byte[l / 2];
        for (int i = 0; i < l / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将二进制转换成16进制
     */
    public static String byteToHex(byte[] byteArray) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (byte b : byteArray) {
            stmp = Integer.toHexString(b & 0XFF);
            if (stmp.length() == 1) {
                hs.append("0");
            }
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }


    public static void main(String[] args) throws Exception {

        int total = 1000000;
        long startTime = System.nanoTime();
        for (int i = 0; i < total; i++) {


            Random random = new Random(System.currentTimeMillis());
            long next = random.nextLong() + 6000000000L;
            String encryptOpen = encryptOpen(String.valueOf(next));
            String decryptOpen = decryptOpen(encryptOpen);

            if (!String.valueOf(next).equals(decryptOpen)) {
                System.out.println("################# failed  " + next);
            }
        }
        long endTime = System.nanoTime();


        System.out.println(" startTime " + startTime);
        System.out.println(" endTime " + endTime);
        System.out.println(" total " + (endTime - startTime));
        System.out.println(" avg " + (endTime - startTime) / total);


    }

}
