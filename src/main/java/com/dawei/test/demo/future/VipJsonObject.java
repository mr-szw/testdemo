package com.dawei.test.demo.future;

import org.springframework.util.StringUtils;

import com.dawei.test.demo.pojo.DemoPojo;
import com.dawei.test.demo.utils.GsonUtil;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VipJsonObject {

	private long id;
	private int type;
	private String json;

	private int status;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private transient Object object;

	public static VipJsonObject create(int type) {
		VipJsonObject jsonObj = new VipJsonObject();
		jsonObj.setType(type);
		jsonObj.setStatus(1);

		return jsonObj;
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject() {
		if (object == null) {
			if (!StringUtils.isEmpty(json)) {
				Class<?> clazz = DemoPojo.class;
				object = GsonUtil.fromJson(json, clazz);
			}
		}
		return (T) object;
	}

	public void updateObject(Object obj) {
		if (type == VipJsonObjClazz.UNDEFINED) {
			throw new RuntimeException("undefined type");
		}
		if (obj == null) {
			this.json = null;
			return;
		}

		Class<?> clazz = VipJsonObjClazz.getTypeClazz(type);
		if (!clazz.isAssignableFrom(obj.getClass())) {
			throw new RuntimeException("can not cast.");
		}

		this.json = GsonUtil.toJson(obj);
	}



	public static VipJsonObject deepCopy(VipJsonObject other){
		Preconditions.checkNotNull(other);

		VipJsonObject jsonObject = new VipJsonObject();
		jsonObject.setId(other.getId());
		jsonObject.setType(other.getType());
		jsonObject.setJson(other.getJson());
		jsonObject.setStatus(other.getStatus());

		return jsonObject;
	}
}
