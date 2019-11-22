package com.dawei.test.demo.utils;

import java.util.List;

import lombok.Data;

/**
 * Create by Dawei on 2019/11/4
 *
 * 邮件发送的配置信息
 */
@Data
public class EmailSendConfigInfo {

	private String boardId;

	//发送时间步长
	private int cycleDayLen;

	private List<String> emailAddressList;

	private List<String> adminNameList;


}
