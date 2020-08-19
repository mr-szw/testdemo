package com.dawei.test.demo.down;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author sinbad on 2020/4/24
 */
@Data
public class DownStrategyConfig {

	/**
	 * 注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意
	 *
	 * 记录已经注册的方法名类型不可重复
	 *
	 * 注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意注意
	 */
	@AllArgsConstructor
	public enum ResourceMarkEnum {
		BOARD_LIVE(1, "getBoardLiveAnnounceList", "逛圈子页面拉取帖子数据"),
		BOARD_LIVE_TEST(1, "getBoardLiveAnnounceList_TEST", "逛圈子页面拉取帖子数据测试"),
		HOME_RECOMMEND(2, "getHomeRecommendContentList", "首页推荐内容数据"),

		;

		private int id;
		@Getter
		private String key;
		private String desc;
	}

	/**
	 * 配置类型 降级规则 必填
	 *
	 * @see ConfigTypeEnum
	 */
	private Integer configType;

	// 资源名字 必填
	private String resourceName;
	/**
	 * 必填
	 *
	 * @see RuleConstant
	 *
	 *  #configType=1 限流阈值类型： QPS（1） 或 并发线程数（0）
	 *
	 *  #configType=2 中断： (0: average RT, 1: exception ratio, 2: exception count).
	 *
	 */
	private Integer gradeType;

	/* ############ flow ########### */

	// Qps or Thread num 必填
	private Integer qpsOrThreadNum;

	/**
	 * 流控效果 0. default(reject directly), 1. warm up, 2. rate limiter, 3. warm up + rate
	 */
	private Integer controlBehavior;
	// 慢恢复策略等待时间
	private Integer warmUpPeriodSec;

	/* ############ degrade ########### */

	// 响应时间 或异常比例 异常数 必填
	private Double rtOrExcRateNum;
	// 窗口时间 秒 必填
	private Integer windowTime;

	@Getter
	@AllArgsConstructor
	public enum ConfigTypeEnum {
		FlowRule(1, "流量控制规则"),

		DegradeRule(2, "熔断降级规则"),

		;

		private int key;
		private String name;
	}

}
