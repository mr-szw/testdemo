package com.dawei.test.demo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	private static final String KEY_AES = "AES";

	//16
	private static final String SALT_KEY = "USER-CENTER-MIUI";

	public static String encryptOpen(String textContext) throws Exception {
		return encrypt(textContext);
	}

	private static String encrypt(String textContext) throws Exception {

		SecretKeySpec skewySpec = new SecretKeySpec(SALT_KEY.getBytes(), KEY_AES);
		Cipher cipher = Cipher.getInstance(KEY_AES);
		cipher.init(Cipher.ENCRYPT_MODE, skewySpec);

		byte[] encrypted = cipher.doFinal(textContext.getBytes());
		return byteToHex(encrypted);
	}

	public static String decryptOpen(String textContext) throws Exception {
		return decrypt(textContext);
	}

	private static String decrypt(String textContext) throws Exception {
		SecretKeySpec skewSpec = new SecretKeySpec(SALT_KEY.getBytes(), KEY_AES);
		Cipher cipher = Cipher.getInstance(KEY_AES);
		cipher.init(Cipher.DECRYPT_MODE, skewSpec);

		byte[] encrypted = hex2byte(textContext);
		byte[] original = cipher.doFinal(encrypted);
		return new String(original);
	}

	public static byte[] hex2byte(String strHex) {
		if (strHex == null) {
			return null;
		}
		int l = strHex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strHex.substring(i * 2, i * 2 + 2), 16);
		}
		return b;
	}

	public static String byteToHex(byte[] byteArray) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (byte b : byteArray) {
			stmp = Integer.toHexString(b & 0XFF);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString().toUpperCase();
	}


	public static void main(String[] args) throws Exception {
		String encryptOpen = encryptOpen("1404277092");
		String decryptOpen = decryptOpen(encryptOpen);

		System.out.println(encryptOpen);
		System.out.println(decryptOpen);
	}

}
