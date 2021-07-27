package com.dawei.test.demo.turn;

import com.dawei.test.demo.pojo.TurnDemoDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sinbad on 2021/7/27.
 */
public class MapToObject {


	private static final int COUNT_NUM = 100000;

	private static Map<String, Field[]> OBJECT_FILED_LIST_MAP = new ConcurrentHashMap<>();

	public static void main(String[] args) {


		List<Map<String, Object>> paramMapList = new ArrayList<>();

		int count = COUNT_NUM;
		while (count-- > 0) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", new Random().nextLong());
			//paramMap.put("age", new Random().nextInt());
			paramMap.put("age", "A");
			paramMap.put("name", UUID.randomUUID().toString());
			paramMap.put("birthday", new Date(System.currentTimeMillis()));
			paramMap.put("addressList", Lists.newArrayList("A", "B", "C"));

			Map<String, String> workMap = Maps.newHashMap();
			workMap.put("A", UUID.randomUUID().toString());
			workMap.put("B", UUID.randomUUID().toString());
			paramMap.put("workMap", workMap);

			paramMapList.add(paramMap);
		}

		List<TurnDemoDto> turnDemoDtoList = new ArrayList<>(COUNT_NUM * 2);
		long startTime = System.currentTimeMillis();
		for (Map<String, Object> paramMap : paramMapList) {

			TurnDemoDto turnDemoDto = mapToObject(paramMap, TurnDemoDto.class);
			//TurnDemoDto turnDemoDto = mapToObjectByCache(paramMap, TurnDemoDto.class);
			turnDemoDtoList.add(turnDemoDto);

		}
		System.out.println("cost time=" + (System.currentTimeMillis() - startTime));

		//System.out.println(turnDemoDtoList.toString());
	}


	public static <T> T mapToObject(Map<String, Object> paramMap, Class<T> clazz) {
		if (paramMap == null || paramMap.size() == 0) {
			return null;
		}
		T object = null;
		try {
			object = clazz.newInstance();
			Field[] declaredFields = clazz.getDeclaredFields();
			return turnObjectFromMap(paramMap, object, declaredFields);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}


	public static <T> T mapToObjectByCache(Map<String, Object> paramMap, Class<T> clazz) {
		if (paramMap == null || paramMap.size() == 0 || clazz == null) {
			return null;
		}

		String name = clazz.getName();
		Field[] fields = OBJECT_FILED_LIST_MAP.get(name);

		T object = null;
		try {
			object = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		//初始化过 并为空
		if (fields != null && fields.length == 0) {
			return object;
		}
		if (fields == null) {
			fields = clazz.getDeclaredFields();
			OBJECT_FILED_LIST_MAP.put(name, fields);
		}


		return turnObjectFromMap(paramMap, object, fields);
	}


	public static <T> T turnObjectFromMap(Map<String, Object> paramMap, T object, Field[] fields) {
		if (fields.length > 0) {
			for (Field declaredField : fields) {
				int mod = declaredField.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				declaredField.setAccessible(true);
				if (paramMap.containsKey(declaredField.getName())) {
					try {
						declaredField.set(object, paramMap.get(declaredField.getName()));
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
			}
		}
		return object;
	}

}
