package com.dawei.test.demo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import sun.misc.BASE64Encoder;

public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	private static final long DAY_MILLIS = 1000 * 24 * 60 * 60L;

	// 服务器工作路径
	public static final String SERVICE_WORK_PATH = "/home/sinbad/work/workspace/emailTempPath/";

	private FileUtil() {
		throw new IllegalStateException();
	}

	public static List<String> readStringFile(BufferedReader reader) throws IOException {
		Preconditions.checkNotNull(reader);

		List<String> contentList = new ArrayList<>();

		String curLine;
		while ((curLine = reader.readLine()) != null) {
			if (StringUtils.isEmpty(curLine)) {
				continue;
			}
			contentList.add(curLine);
		}

		return contentList;
	}

	/**
	 * 格式转换
	 *
	 * @param mailContext 内容
	 * @param finalCSVFile 目标文件 带文件路径和后缀名字
	 */
	public static void writeToCSV(String mailContext, File finalCSVFile) {
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileOutputStream = new FileOutputStream(finalCSVFile);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, Charsets.UTF_8);
			// 手动加上BOM标识
			outputStreamWriter
					.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			bufferedWriter.append(mailContext);
		} catch (Exception e) {
			logger.error("Do file turn failed, e=", e);
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
					bufferedWriter = null;
				}
				if (outputStreamWriter != null) {
					outputStreamWriter.close();
					outputStreamWriter = null;
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
					fileOutputStream = null;
				}
			} catch (Exception e) {
				logger.error("Close source failed, e=", e);
			}
		}
	}

	/**
	 * 创建Csv文件用作邮件发送
	 *
	 * @param fileNamePrefix 文件名前缀 注意 ###简单的文件名就好不要带 路径和后缀 ####
	 */
	public static File createCsvFileForEmail(String fileNamePrefix) {
		File file = null;
		if (!StringUtils.isEmpty(fileNamePrefix)) {
			if (fileNamePrefix.contains(".")) {
				fileNamePrefix = fileNamePrefix.substring(0, fileNamePrefix.indexOf("."));
			}
			if (fileNamePrefix.contains("/")) {
				fileNamePrefix = fileNamePrefix.substring(fileNamePrefix.indexOf("/") + 1);
			}

			String substring = UUID.randomUUID().toString().substring(0, 6);

			File serviceFlePath;
			try {
				serviceFlePath = new File(SERVICE_WORK_PATH);
				if (!serviceFlePath.exists()) {
					boolean mkdir = serviceFlePath.mkdir();
					if (!mkdir) {
						logger.error("Create file failed,  SERVICE_WORK_PATH={}",
								SERVICE_WORK_PATH);
					}
				}
			} catch (Exception e) {
				logger.info("Can`t open file path={}", SERVICE_WORK_PATH, e);
			}
			file = new File(SERVICE_WORK_PATH + fileNamePrefix
					+ DateFormatUtils.format(new Date(), "yyyyMMdd") + "_" + substring + ".csv");

		}
		return file;
	}

	/**
	 * 删除昨天的文件
	 *
	 * @param currentDayInMillis 的时间戳
	 * @param fileName 文件 内容 不包含工作路径和后缀
	 *
	 */
	public static void deleteHistoryFile(long currentDayInMillis, String fileName) {

		Date date = new Date(currentDayInMillis - DAY_MILLIS);
		String lastDateStr = DateFormatUtils.format(date, "yyyyMMdd");

		String delFilePath = fileName + lastDateStr + ".*\\.csv";

		logger.info("Will to delete file name is delFilePath={}", delFilePath);
		File serviceFlePath;
		try {
			serviceFlePath = new File(SERVICE_WORK_PATH);
			if (!serviceFlePath.exists()) {
				logger.error("The path ={} is not exist, don`t need to delete", SERVICE_WORK_PATH);
				return;
			}
		} catch (Exception e) {
			logger.info("Can`t open file path={}", SERVICE_WORK_PATH, e);
			return;
		}

		List<String> filePathList = new ArrayList<>(getFileNameList(serviceFlePath, delFilePath));

		if (!CollectionUtils.isEmpty(filePathList)) {
			for (String filePathInfo : filePathList) {
				try {
					File file = new File(SERVICE_WORK_PATH + "/" + filePathInfo);
					boolean delete = file.delete();
					logger.info(String.format("The history fileName=%s delete result=%s",
							filePathInfo, delete));
				} catch (Exception e) {
					logger.error("Delete file failed, filePathInfo=" + filePathInfo, e);
				}
			}
		} else {
			logger.info("No history file need delete");
		}
		logger.info("The history file delete finished");
	}

	private static List<String> getFileNameList(File serviceFlePath, String fFileName) {
		String[] proposalFileList = serviceFlePath.list(new FilenameFilter() {
			private Pattern pattern = Pattern.compile(fFileName);

			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		});
		if (proposalFileList != null) {
			return Arrays.asList(proposalFileList);
		}
		return Collections.emptyList();
	}

	// 生成二维码到制定文件
	public static String generateQRCodeStreamToFile(String content, String onlyMarkId, boolean setWhite, int whiteWidth) {
		return generateQRCodeFileOnService(content, SERVICE_WORK_PATH + "imagePath/", onlyMarkId,
				"png", 300, setWhite, whiteWidth);
	}

	/**
	 * 内容转换为二维码
	 *
	 * @param contentInfo 文本内容
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 * @return 文件路径
	 */
	public static BitMatrix generateQRCodeFile(String contentInfo, int width,
			int height) {
		BitMatrix bitMatrix = null;
		try {
			Map<EncodeHintType, Object> hintTypeObjectMap = new HashMap<>();
			hintTypeObjectMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
			hintTypeObjectMap.put(EncodeHintType.MARGIN, 1);
			// 参数分别为：编码内容、编码类型、图片宽度、图片高度，设置参数
			bitMatrix = new MultiFormatWriter().encode(contentInfo, BarcodeFormat.QR_CODE, width,
					height, hintTypeObjectMap);
		} catch (Throwable e) {
			logger.error("create BitMatrix file failed, e=", e);
		}
		return bitMatrix;
	}

	/**
	 * 在服务器i上生成二维码
	 *
	 * @param contentInfo 文本内容
	 * @param filePathOnService 服务器位置 最后带/
	 * @param fileOnlyMarkIdName 文件名 唯一性 不带后缀
	 * @param fileFormat 文件后缀
	 * @param width 二维码宽度
	 * @return 文件路径
	 */
	public static String generateQRCodeFileOnService(String contentInfo, String filePathOnService,
			String fileOnlyMarkIdName, String fileFormat, int width, boolean setWhite,
			int whiteWidth) {

		String filePathReal = filePathOnService + fileOnlyMarkIdName + "." + fileFormat;

		File serviceFlePath;
		try {
			serviceFlePath = new File(filePathOnService);
			if (!serviceFlePath.exists()) {
				boolean mkdir = serviceFlePath.mkdir();
				logger.info("Don`t exists this path={}, and to create result={}", serviceFlePath,
						mkdir);
			}
		} catch (Throwable throwable) {
			logger.error("Create servicePath = {} failed, e=", filePathOnService, throwable);
			return null;
		}
		try {
			BitMatrix bitMatrix = generateQRCodeFile(contentInfo, width, width);
			if (setWhite) {
				bitMatrix = deleteQRCodeWhite(bitMatrix, whiteWidth);
			}
			File outputFile = new File(filePathReal);
			MatrixToImageWriter.writeToFile(bitMatrix, fileFormat, outputFile);
		} catch (Throwable e) {
			logger.error("Write file failed, filePathReal={} , e=", filePathReal, e);
			filePathReal = null;
		}
		return filePathReal;
	}

	/**
	 * 删除二维码白边
	 *
	 * @param oldMatrix 原文件
	 */
	private static BitMatrix deleteQRCodeWhite(BitMatrix oldMatrix, int whiteWidth) {
		if (oldMatrix == null) {
			return null;
		}
		int oldMatrixWidth = oldMatrix.getWidth();
		int oldMatrixHeight = oldMatrix.getHeight();
		// 原白边宽度
		int oldWidthWhite = 1;
		int oldHeightWhite = 1;
		boolean outLoop = false;
		for (oldWidthWhite = 0; oldWidthWhite < (oldMatrixWidth / 2); oldWidthWhite++) {
			for (oldHeightWhite = 0; oldHeightWhite < (oldMatrixHeight / 2); oldHeightWhite++) {
				if (oldMatrix.get(oldWidthWhite, oldHeightWhite)) {
					outLoop = true;
				}
				if (outLoop) {
					break;
				}
			}
			if (outLoop) {
				break;
			}
		}
		//多留一个像素点
		oldWidthWhite--;
		oldHeightWhite--;
		// 确定的白边宽度
		int newCQWhiteWidth = Math.min(whiteWidth, oldWidthWhite);
		int newCQWhiteHeight = Math.min(whiteWidth, oldHeightWhite);

		int newWidth = oldMatrixWidth - ((oldWidthWhite - newCQWhiteWidth) * 2);
		int newHeight = oldMatrixHeight - ((oldHeightWhite - newCQWhiteHeight) * 2);
		BitMatrix resMatrix = new BitMatrix(newWidth, newHeight);
		try {
			for (int width = 0; width < newWidth; width++) {
				for (int height = 0; height < newWidth; height++) {
					System.out.println(width);
					System.out.println(height);
					if (oldMatrix.get(width + oldWidthWhite - newCQWhiteWidth, height + oldHeightWhite - newCQWhiteHeight))
						resMatrix.set(width, height);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resMatrix;
	}


	private static BitMatrix deleteWhite(BitMatrix matrix) {
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}
	/**
	 * 获取路径下的文件名 全路径
	 */
	public static List<String> getFileNameList(String filePathOnService) throws Throwable {
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

	/**
	 * 获取路径下的文件名 全路径 仅名字 包含放重码 c
	 */
	public static List<String> getFileOnlyNameListWithCheckNum(String filePathOnService)
			throws Throwable {
		List<String> fileOnlyNameList = new ArrayList<>();
		List<String> fileNameList = getFileNameList(filePathOnService);
		if (!CollectionUtils.isEmpty(fileNameList)) {
			for (String allFileName : fileNameList) {
				fileOnlyNameList.add(allFileName.substring(allFileName.lastIndexOf("/") + 1));
			}
		}
		return fileOnlyNameList;
	}

	/**
	 * 获取路径下的文件夹列表
	 */
	public static List<String> getFilePathList(String filePathOnService) throws Throwable {
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

	// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	public static String getImageBASE64Str(String imgFileAllPath) {
		if (imgFileAllPath == null) {
			return null;
		}
		InputStream inputStream = null;
		// 读取图片字节数组
		byte[] data;
		try {
			inputStream = new FileInputStream(imgFileAllPath);
			data = new byte[inputStream.available()];
			int read = inputStream.read(data);
			logger.warn("File available size is ={}", read);
			// 对字节数组Base64编码
			BASE64Encoder encoder = new BASE64Encoder();
			// 返回Base64编码过的字节数组字符串
			return encoder.encode(data);
		} catch (Throwable e) {
			logger.error("Read image failed, ");
		} finally {
			// 释放资源
			data = null;
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Throwable throwable) {
					logger.error("inputStream close failed, e=", throwable);
				}
			}
		}
		return null;
	}

}
