package com.dawei.test.demo.hbase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.util.StringUtils;

import com.dawei.test.demo.utils.GsonUtil;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HbaseConverter {

	private static final Map<String, Map<String, String>> ObjectHbaseCellToFiledMap = new ConcurrentHashMap<>();
	private static final Map<String, Map<String, String>> ObjectFieldToHbaseCellMap = new ConcurrentHashMap<>();
	private static final List<String> NEED_FILTERED_FIELD = Stream
			.of("familyName", "filedToHbaseCellMap").collect(Collectors.toList());

	/**
	 * 将result 转化为你的数据对象
	 *
	 * @param hbaseResult hbase 的结果数据
	 * @param objectClazz 返回的对象类型
	 */
	public static <T> T decodingResult(Result hbaseResult, Class<T> objectClazz) throws Throwable {
		T resultObject = objectClazz.newInstance();
		String clazzName = objectClazz.getName();
		String familyName = getFamilyName(objectClazz);
		// 构建或者初始化映射关系
		buildFiledCellMap(objectClazz);
		Map<String, String> hbaseCellToFiledMap = ObjectHbaseCellToFiledMap.get(clazzName);

		for (Map.Entry<String, String> hbaseCellFieldEntry : hbaseCellToFiledMap.entrySet()) {
			byte[] valueByte = hbaseResult.getValue(Bytes.toBytes(familyName),
					Bytes.toBytes(hbaseCellFieldEntry.getKey()));
			if (valueByte == null) {
				continue;
			}
			Field declaredField = objectClazz.getDeclaredField(hbaseCellFieldEntry.getValue());
			declaredField.setAccessible(true);
			Object value = decodingBytes(valueByte, declaredField.getType());
			declaredField.set(resultObject, value);
		}
		return resultObject;
	}

	/**
	 * 构建put对象
	 *
	 * @param rowKey put的rowkey
	 * @param object 要写入的值
	 */
	public <T> Put buildObjectPut(String rowKey, T object) throws Exception {

		String familyName = getFamilyName(object.getClass());
		Map<String, String> filedToHbaseCellMap = ObjectFieldToHbaseCellMap
				.get(object.getClass().getName());
		List<Cell> cellList = new ArrayList<>();
		for (Map.Entry<String, String> filedHbaseCellEntry : filedToHbaseCellMap.entrySet()) {
			Field declaredField = object.getClass().getDeclaredField(filedHbaseCellEntry.getKey());
			declaredField.setAccessible(true);

			Object fieldValue = declaredField.get(object);
			if (fieldValue == null) {
				continue;
			}
			KeyValue cell = new KeyValue(Bytes.toBytes(rowKey), Bytes.toBytes(familyName),
					Bytes.toBytes(filedHbaseCellEntry.getValue()), codingObject(fieldValue));
			cellList.add(cell);
		}
		NavigableMap<byte[], List<Cell>> familyCellMap = new TreeMap<>(Bytes.BYTES_COMPARATOR);
		familyCellMap.put(Bytes.toBytes(familyName), cellList);
		Put objectPut = new Put(Bytes.toBytes(rowKey));
		objectPut.setFamilyCellMap(familyCellMap);
		return objectPut;
	}

	/**
	 * 获取对象存储时的列族
	 */
	private static <T> String getFamilyName(Class<T> objectClazz) throws Exception {
		Field familyNameField = objectClazz.getDeclaredField("familyName");
		familyNameField.setAccessible(true);
		return String.valueOf(familyNameField.get(objectClazz));
	}

	// 生成字母序列
	private static String createLetter(int num) {
		int codePoint = 'A' + --num % 26;
		int higher = num / 26;
		String letter = new String(Character.toChars(codePoint));
		return higher == 0 ? letter : createLetter(higher).concat(letter);
	}

	// 解码byte数据
	private static <T> T decodingBytes(byte[] bytes, Class<T> clazz) {
		if (bytes == null || clazz == null) {
			return null;
		}
		String typeName = clazz.getName();
		Object res = null;
		if ("java.lang.Float".equals(typeName) || "float".equals(typeName)) {
			res = Bytes.toFloat(bytes);
		} else if ("java.lang.Double".equals(typeName) || "double".equals(typeName)) {
			res = Bytes.toDouble(bytes);
		} else if ("java.lang.Short".equals(typeName) || "short".equals(typeName)) {
			res = Bytes.toShort(bytes);
		} else if ("java.lang.Integer".equals(typeName) || "int".equals(typeName)) {
			res = Bytes.toInt(bytes);
		} else if ("java.lang.Long".equals(typeName) || "long".equals(typeName)) {
			res = Bytes.toLong(bytes);
		} else if ("java.lang.Boolean".equals(typeName) || "boolean".equals(typeName)) {
			res = Bytes.toBoolean(bytes);
		} else if ("java.lang.String".equals(typeName)) {
			res = Bytes.toString(bytes);
		}
		return clazz.cast(res);
	}

	// 编码byte数据
	private static <T> byte[] codingObject(T object) {
		if (object == null) {
			return null;
		}
		byte[] resultByte = null;
		String typeName = object.getClass().getTypeName();

		if ("java.lang.Float".equals(typeName) || "float".equals(typeName)) {
			resultByte = Bytes.toBytes((Float) object);
		} else if ("java.lang.Double".equals(typeName) || "double".equals(typeName)) {
			resultByte = Bytes.toBytes((Double) object);
		} else if ("java.lang.Short".equals(typeName) || "short".equals(typeName)) {
			resultByte = Bytes.toBytes((Short) object);
		} else if ("java.lang.Integer".equals(typeName) || "int".equals(typeName)) {
			resultByte = Bytes.toBytes((Integer) object);
		} else if ("java.lang.Long".equals(typeName) || "long".equals(typeName)) {
			resultByte = Bytes.toBytes((Long) object);
		} else if ("java.lang.Boolean".equals(typeName) || "boolean".equals(typeName)) {
			resultByte = Bytes.toBytes((Boolean) object);
		} else if ("java.lang.String".equals(typeName)) {
			resultByte = Bytes.toBytes((String) object);
		}
		return resultByte;
	}

	// 构建对象的映射关系
	private static <T> void buildFiledCellMap(Class<T> clazz) {
		String clazzName = clazz.getName();
		if (ObjectHbaseCellToFiledMap.containsKey(clazzName)
				&& ObjectFieldToHbaseCellMap.containsKey(clazzName)) {
			return;
		}
		try {
			// 获取用户自定义的匹配关系
			doBuildFiledCellMapByCustom(clazz);
			return;
		} catch (RuntimeException runtimeException) {
			log.info(clazzName + " class not set custom info.");
		}
		// 通过反射自动生成
		doBuildFiledCellMapByReflect(clazz);
	}

	/**
	 * 通过反射自动生成
	 *
	 * @param resultClazz 对象类
	 */
	private static <T> void doBuildFiledCellMapByReflect(Class<T> resultClazz) {

		Map<String, String> hbaseCellToFiledMap = new HashMap<>();
		Map<String, String> filedToHbaseCellMap = new HashMap<>();
		Field[] declaredFields = resultClazz.getDeclaredFields();
		int fieldNum = 1;
		for (Field declaredField : declaredFields) {
			declaredField.setAccessible(true);
			String fieldName = declaredField.getName();
			if (NEED_FILTERED_FIELD.contains(fieldName)) {
				continue;
			}
			hbaseCellToFiledMap.put(fieldName, createLetter(fieldNum));
			filedToHbaseCellMap.put(createLetter(fieldNum), fieldName);
			fieldNum++;
		}
		ObjectHbaseCellToFiledMap.put(resultClazz.getName(), hbaseCellToFiledMap);
		ObjectFieldToHbaseCellMap.put(resultClazz.getName(), filedToHbaseCellMap);
		log.info("Mapping: filed map hbase cell info objectName={} mappingInfo={}",
				resultClazz.getTypeName(), GsonUtil.toJson(filedToHbaseCellMap));
		log.info("Mapping: hbase cell map filed info objectName={} mappingInfo={}",
				resultClazz.getTypeName(), GsonUtil.toJson(filedToHbaseCellMap));
	}

	/**
	 * 通过用户自定义去获取匹配关系
	 *
	 * @param resultClazz 对象类
	 */
	private static <T> void doBuildFiledCellMapByCustom(Class<T> resultClazz) {
		try {
			Field filedToHbaseCellMapField = resultClazz.getDeclaredField("filedToHbaseCellMap");
			filedToHbaseCellMapField.setAccessible(true);
			String filedToHbaseCellMapStr = (String) filedToHbaseCellMapField.get(resultClazz);
			if (!StringUtils.isEmpty(filedToHbaseCellMapStr)
					&& !StringUtils.isEmpty(filedToHbaseCellMapStr)) {
				Map<String, String> hbaseCellToFiledMap = GsonUtil.fromJson(filedToHbaseCellMapStr,
						new TypeToken<HashMap<String, String>>() {
						}.getType());
				ObjectHbaseCellToFiledMap.put(resultClazz.getName(), hbaseCellToFiledMap);
				Set<Map.Entry<String, String>> entrySet = hbaseCellToFiledMap.entrySet();
				Map<String, String> filedToHbaseCellMap = new HashMap<>();
				for (Map.Entry<String, String> mapEntry : entrySet) {
					filedToHbaseCellMap.put(mapEntry.getValue(), mapEntry.getKey());
				}
				ObjectFieldToHbaseCellMap.put(resultClazz.getName(), filedToHbaseCellMap);
			}
		} catch (NoSuchFieldException | IllegalAccessException exception) {
			throw new RuntimeException("Not Custom Set Mapping");
		}
	}
}
