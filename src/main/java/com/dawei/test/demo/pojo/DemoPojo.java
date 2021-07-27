package com.dawei.test.demo.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dawei.test.demo.utils.GsonUtil;

import lombok.Data;

/**
 * @author: Dawei
 * @Date: 2018/7/19
 */
@Data
public class DemoPojo implements Serializable {


	private Long id;
	private String name = "我是爹";
	private Date birthday;

	private int test;

	private List<String> pathList;


	public static void main(String[] args) {
		List<DemoPojo> list = new ArrayList<>();
		list.add(new DemoPojoSub());
		list.add(new DemoPojoSub2());

		System.out.println(GsonUtil.toJson(list));
	}
}
