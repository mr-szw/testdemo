package com.dawei.test.demo.pojo;

import lombok.Data;

/**
 *
 * 圈子更多配置数据项
 * @author sinbad on 2020/4/3
 */
@Data
public class BoardMoreConfigVo {

	// 是否打开圈内热门提案卡片展示 1是
	private Integer hotProposal;

	// 圈子对应app 包名
	private String packageName;

	// 模块id 项目中唯一
	private String componentId;
	// 项目名字id
	private Long projectId;


	// 从属等级关系 1 ：父级 2: 子集
	// 这个关系实属复杂是社区这边的等级定位，父子集定位，子集才有权利创建jira
	private Integer belongLevel;





}
