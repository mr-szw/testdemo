package com.dawei.test.demo.future;

import java.util.function.ObjLongConsumer;

import com.dawei.test.demo.pojo.DemoPojo;
import com.dawei.test.demo.utils.GsonUtil;

/**
 * 通用配置
 *
 * @see VipJsonObjClazz
 */

public class VipJsonObjectBiz {


	/**
	 * 获取的是缓存的对象，不要改动
	 */
	public <T> T getT(long jsonId, int type, ObjLongConsumer<T> jsonIdInitializer) {

		VipJsonObject jsonObj = get(jsonId);
		if (jsonObj == null) {

			return null;
		}
		if (jsonObj.getType() != type) {
			throw new RuntimeException("未找到或类型不匹配");
		}

		return mapper(jsonObj, jsonIdInitializer);
	}

	/**
	 * init VipJsonObjectId
	 */
	private <T> T mapper(VipJsonObject obj, ObjLongConsumer<T> jsonIdInitializer) {
		T ret = obj.getObject();

		if (ret != null) {
			jsonIdInitializer.accept(ret, obj.getId());
		}

		return ret;
	}

	public VipJsonObject get(long jsonId) {
		VipJsonObject vipJsonObject = new VipJsonObject();
		vipJsonObject.setId(10L);
		vipJsonObject.setStatus(1);
		vipJsonObject.setType(1);
		vipJsonObject.setJson("{}");
		return vipJsonObject;
	}


	public static void main(String[] args) {
		VipJsonObjectBiz vipJsonObjectBiz = new VipJsonObjectBiz();
		DemoPojo t = vipJsonObjectBiz.getT(1L, 1, DemoPojo::setId);
		System.out.println(GsonUtil.toJson(t));
	}
}
