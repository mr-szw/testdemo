package com.dawei.test.demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 参与情况记录
 *
 * @author sinbad on 2020/5/22
 */
@Data
@NoArgsConstructor
public class RecordRawPo {

	private static final String familyName = "";
	private int rowType;
	private String rowKey;
	private String postId;
	private Long userId;
	private Integer userType;
	private Long joinUserCount;
	// 参与的帖子记录ID
	private String joinRecordMark;
	// 前几个参与的 用户Id
	private String userIds;
	// 存储认领的 员工ID
	private String employeeIds;

}
