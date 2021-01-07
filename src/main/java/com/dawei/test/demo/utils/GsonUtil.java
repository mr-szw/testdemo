package com.dawei.test.demo.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/*
 * 添加对原始gson的thread local优化，优化的考虑和原因主要基于两点：
 * 1 目前项目中大量直接new gson，十分不友好，操作比较重，
 * 2 由于版本的原因，目前线上使用的gson版本比较老，存在线程安全问题
 *
 * 讲道理，用thread_local没啥问题，但也可能有坑，比如引用ClassLoader导致溢出，观察一下
 *
 * 通过定制方式生成的gson暂时没有处理
 * 这里似乎还有很多事情可以做，比如gson版本的升级，gson定制代码的升级，thread local优化
 * */
public class GsonUtil {
	private static final ThreadLocal<Gson> THREAD_LOCAL_GSON = ThreadLocal
			.withInitial(GsonUtil::newGson);

	public static final Type TOKEN_LIST_INT = new TypeToken<List<Integer>>() {
	}.getType();

	public static final Type TOKEN_LIST_LONG = new TypeToken<List<Long>>() {
	}.getType();

	public static final Type TOKEN_LIST_STRING = new TypeToken<List<String>>() {
	}.getType();

	public static String toJson(Object obj) {
		return obj == null ? null : gson().toJson(obj);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		return fromJson(json, (Type) clazz);
	}

	public static <T> T fromJson(String json, Type typeOfT) {
		return gson().fromJson(json, typeOfT);
	}

	public static Gson gson() {
		return THREAD_LOCAL_GSON.get();
	}

	/*
	 * 准确的说，不建议使用这个方法来暴力new一个对象
	 */
	public static Gson newGson() {
		return new GsonBuilder().registerTypeAdapter(AtomicInteger.class, ADAPTER_ATOMIC_INTEGER)
				.registerTypeAdapter(AtomicLong.class, ADAPTER_ATOMIC_LONG).create();
	}

	static final TypeAdapter<AtomicInteger> ADAPTER_ATOMIC_INTEGER = new TypeAdapter<AtomicInteger>() {
		@Override
		public AtomicInteger read(JsonReader in) throws IOException {
			try {
				return new AtomicInteger(in.nextInt());
			} catch (IllegalStateException e) {
				// try to explain as origin object
				in.beginObject();
				in.nextName();
				in.peek();
				int value = in.nextInt();
				in.endObject();
				return new AtomicInteger(value);
			}
		}

		@Override
		public void write(JsonWriter out, AtomicInteger value) throws IOException {
			out.value(value.get());
		}
	}.nullSafe();

	static final TypeAdapter<AtomicLong> ADAPTER_ATOMIC_LONG = new TypeAdapter<AtomicLong>() {
		@Override
		public AtomicLong read(JsonReader in) throws IOException {
			try {
				return new AtomicLong(in.nextInt());
			} catch (IllegalStateException e) {
				// try to explain as origin object
				in.beginObject();
				in.nextName();
				in.peek();
				long value = in.nextLong();
				in.endObject();
				return new AtomicLong(value);
			}
		}

		@Override
		public void write(JsonWriter out, AtomicLong value) throws IOException {
			out.value(value.get());
		}
	}.nullSafe();

	public static List<Integer> getIntegerListFromJson(String json) {
		if (Strings.isNullOrEmpty(json)) {
			return Lists.newArrayList();
		}

		return fromJson(json, TOKEN_LIST_INT);
	}

	public static List<Long> getLongListFromJson(String json) {
		if (Strings.isNullOrEmpty(json)) {
			return Lists.newArrayList();
		}

		return fromJson(json, TOKEN_LIST_LONG);
	}

	public static List<String> getStringListFromJson(String json) {
		if (Strings.isNullOrEmpty(json)) {
			return Lists.newArrayList();
		}

		return fromJson(json, TOKEN_LIST_STRING);
	}
}
