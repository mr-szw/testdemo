package com.dawei.test.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Dawei
 * @Date: 2018/7/19
 */
@Data
public class TurnDemoDto implements Serializable {


	private Long id;
	private int age;
	private String name;
	private Date birthday;
	private List<String> addressList;
	private Map<String, String> workMap;

}
