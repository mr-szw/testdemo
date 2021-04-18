package com.dawei.test.demo.reflect.hbase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;

import com.dawei.test.demo.pojo.RecordRawPo;

public class ReflectHbaseService {

	public static void main(String[] args) throws IllegalAccessException {
		NavigableMap<byte[], List<Cell>> familyCellMap = new TreeMap<>(Bytes.BYTES_COMPARATOR);

		RecordRawPo object = new RecordRawPo();
		object.setUserType(1);
		object.setRowKey("wqwwe");
		object.setPostId("123434");
		object.setUserId(1L);
		object.setJoinUserCount(1L);
		object.setJoinRecordMark("MarkInfo");
		object.setUserIds("1,3");
		String[] types = { "java.lang.Integer", "java.lang.Double", "java.lang.Float",
				"java.lang.Long", "java.lang.Short", "java.lang.Byte", "java.lang.Boolean",
				"java.lang.Character", "java.lang.String", "int", "double", "long", "short", "byte",
				"boolean", "char", "float" };

		String tableName = null;
		String rowKey = null;
		String familyName = null;
		try {
			Field familyNameField = object.getClass().getField("familyName");
			Field rowKeyField = object.getClass().getField("rowKey");
			familyName = (String) familyNameField.get(object);
			rowKey = (String) rowKeyField.get(object);
		} catch (NoSuchFieldException noSuchFieldException) {
			noSuchFieldException.fillInStackTrace();
		}
		int fieldNum = 1;
		Field[] declaredFields = object.getClass().getDeclaredFields();
		List<Cell> cellList = new ArrayList<>();
		for (Field declaredField : declaredFields) {
			declaredField.setAccessible(true);
			String fieldName = declaredField.getType().getName();
			if (fieldName.equals("familyName")) {
				continue;
			}
			Object fieldValue = declaredField.get(object);
			KeyValue cell = new KeyValue(rowKey.getBytes(), familyName.getBytes(),
					createLetter(fieldNum++).getBytes(), fieldValue.toString().getBytes());
		}

	}

	public static String createLetter(int num) {
		int codePoint = 'A' + --num % 26;
		int higher = num / 26;
		String letter = new String(Character.toChars(codePoint));
		return higher == 0 ? letter : createLetter(higher).concat(letter);
	}
}