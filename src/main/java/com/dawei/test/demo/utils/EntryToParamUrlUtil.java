package com.dawei.test.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.util.StringUtils;

import com.dawei.test.demo.pojo.DemoPojo;

/**
 * @author sinbad on 2020/6/9 建议操作的entry的成员属性使用包装类型 目前仅支持到 list内基本类型的数据解析 后面劳烦各类大佬补充
 */
public class EntryToParamUrlUtil {

	public static String getParamUrlPath(Object entry, String pathPrefix) throws Exception {

		if (entry == null) {
			return pathPrefix;
		}

		StringBuilder paramUrl = new StringBuilder(pathPrefix);
		Class<? extends Object> boardSearchClass = entry.getClass();
		Field[] fieldList = boardSearchClass.getDeclaredFields();
		for (Field field : fieldList) {
			field.setAccessible(true);
			String name = field.getName();
			// list 类型的
			if (List.class.isAssignableFrom(field.getType())) {
				Type genericType = field.getGenericType();
				if (genericType instanceof ParameterizedType) {
					// 获取对象list属性的class
					Class<?> fieldClazz = field.get(entry).getClass();
					System.out.println(fieldClazz.getName());
					// 获取list属性的size方法
					Method method = fieldClazz.getDeclaredMethod("size");
					int size = (Integer) method.invoke(field.get(entry));
					for (int i = 0; i < size; i++) {
						Method getM = fieldClazz.getDeclaredMethod("get", int.class);
						// 调用get方法获取list中的对象
						Object listElement = getM.invoke(field.get(entry), i);
						if (listElement != null) {
							if (i == 0) {
								paramUrl.append(name).append("=");
							}
							if (listElement instanceof String) {
								if (!StringUtils.isEmpty(listElement)) {
									paramUrl.append(
											URLEncoder.encode((String) listElement, "UTF-8"))
											.append(",");
								}
							} else {
								paramUrl.append(listElement).append(",");
							}
						}
					}
					paramUrl = new StringBuilder(paramUrl.substring(0, paramUrl.length() - 1));
					paramUrl.append("&");
				}
			} else {
				Object value = field.get(entry);
				if (value != null) {
					if (value instanceof String) {
						paramUrl.append(name).append("=")
								.append(value);

					} else {
						paramUrl.append(name).append("=").append(value);
					}
					paramUrl.append("&");
				}
			}

		}
		return paramUrl.substring(0, paramUrl.length() - 1);
	}


	public static void main(String[] args) throws Exception {
		DemoPojo demoPojo = new DemoPojo();
		demoPojo.setName("大伟");
		System.out.println(getParamUrlPath(demoPojo, "sdrsdf"));
	}
}
