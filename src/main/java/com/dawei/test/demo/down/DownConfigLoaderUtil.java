package com.dawei.test.demo.down;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.dawei.test.demo.utils.GsonUtil;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sinbad on 2020/07/02.
 *
 *         数据更新策略
 */
@Slf4j
public class DownConfigLoaderUtil {

	private static volatile List<DownStrategyConfig> localConfigList = null;

	private static final AtomicLong updateTime = new AtomicLong(0L);

	private static final long UPDATE_CYCLE = TimeUnit.MICROSECONDS.toMinutes(1);


	// 上一次的配置
	private static String LAST_TIME_CONFIG = null;



	public static void loadDownStrategyConfig()  {

		long currentTimeMillis = System.currentTimeMillis();
		long updateTime = DownConfigLoaderUtil.updateTime.get();
		if (currentTimeMillis - updateTime > UPDATE_CYCLE) {
			if (!DownConfigLoaderUtil.updateTime.compareAndSet(updateTime, currentTimeMillis)) {
				return;
			}
		}

		//String sentinelJsonConfig = "[{\"configType\":2,\"resourceName\":\"getBoardLiveAnnounceList_TEST\",\"gradeType\":0,\"rtOrExcRateNum\":350.0,\"windowTime\":3},{\"configType\":2,\"resourceName\":\"getBoardLiveAnnounceList\",\"gradeType\":1,\"rtOrExcRateNum\":0.2,\"windowTime\":3},{\"configType\":2,\"resourceName\":\"getHomeRecommendContentList\",\"gradeType\":0,\"rtOrExcRateNum\":700.0,\"windowTime\":1},{\"configType\":2,\"resourceName\":\"getBoardLiveAnnounceList_TEST\",\"gradeType\":1,\"rtOrExcRateNum\":0.2,\"windowTime\":5}]";
		String sentinelJsonConfig = "[{\"configType\":2,\"resourceName\":\"getBoardLiveAnnounceList_TEST\",\"gradeType\":1,\"rtOrExcRateNum\":0.2,\"windowTime\":5}]";
		if (!StringUtils.isEmpty(sentinelJsonConfig)
				&& !sentinelJsonConfig.equals(LAST_TIME_CONFIG)) {
			log.info("###### New sentinel configJson={}", sentinelJsonConfig);
			LAST_TIME_CONFIG = sentinelJsonConfig;
			try {
				localConfigList = GsonUtil.fromJson(sentinelJsonConfig,
						new TypeToken<List<DownStrategyConfig>>() {
						}.getType());
			} catch (Exception e) {
				log.error("Turn config to entry failed, sentinelJsonConfig={}", sentinelJsonConfig);
			}
			if (!CollectionUtils.isEmpty(localConfigList)) {
				setDownStrategyConfig(localConfigList);
			}
		} else {
			log.warn("Load sentinel config is empty !!! ");
		}
	}

	private static void setDownStrategyConfig(List<DownStrategyConfig> configList) {
		log.info("Set new DownStrategyConfig  configList={}", GsonUtil.toJson(configList));
		if (CollectionUtils.isEmpty(configList)) {
			return;
		}
		List<DownStrategyConfig> configListTemp = configList.stream()
				.map(DownConfigLoaderUtil::checkConfigInfo).filter(Objects::nonNull)
				.collect(Collectors.toList());

		if (CollectionUtils.isEmpty(configListTemp)) {
			return;
		}
		List<FlowRule> flowRuleList = new ArrayList<>();
		List<DegradeRule> degradeRuleList = new ArrayList<>();
		for (DownStrategyConfig config : configListTemp) {
			Integer configType = config.getConfigType();
			String resourceName = config.getResourceName();
			if (configType == DownStrategyConfig.ConfigTypeEnum.FlowRule.getKey()) {
				FlowRule flowRule = new FlowRule(resourceName);

				// 策略类型 QPS（1） 或 并发线程数（0）
				flowRule.setGrade(config.getGradeType());
				// 限流量 ： qps 数或 线程数
				flowRule.setCount(config.getQpsOrThreadNum());

				// Rate limiter control behavior.
				// 0. default(reject directly), 1. warm up, 2. rate limiter, 3. warm up + rate
				// limiter
				flowRule.setControlBehavior(
						config.getControlBehavior() == null ? flowRule.getControlBehavior()
								: config.getControlBehavior());
				// 冷启动之类的 最大等待时间
				// flowRule.setMaxQueueingTimeMs(config.getWindowTime());
				// 对应上面 warm up 的尝试恢复时间
				flowRule.setWarmUpPeriodSec(
						config.getWarmUpPeriodSec() == null ? flowRule.getWarmUpPeriodSec()
								: config.getWarmUpPeriodSec());

				// 调用关心限流策略：直接，链路，关联 根据资源本身（直接）
				flowRule.setStrategy(0);
				flowRuleList.add(flowRule);

			} else if (configType == DownStrategyConfig.ConfigTypeEnum.DegradeRule.getKey()) {
				DegradeRule degradeRule = new DegradeRule(resourceName);

				// 中断策略 返回时长 异常比例 异常数
				degradeRule.setGrade(config.getGradeType());
				// RT threshold or exception ratio threshold count.
				degradeRule.setCount(config.getRtOrExcRateNum());

				// 恢复的时间窗口
				degradeRule.setTimeWindow(config.getWindowTime());

				// 达不到都不去关心是否降级触发电路中断的最小请求数 设计成关心
				degradeRule.setMinRequestAmount(1);

				// 触发中断的 最小慢请求数 请求数量太少不关心 设计成关心
				degradeRule.setRtSlowRequestAmount(1);

				if (DegradeRuleManager.isValidRule(degradeRule)) {
					degradeRuleList.add(degradeRule);
				}
			}
		}

		log.info("Do sentinel config update flowRuleList={} degradeRuleList={}",
				GsonUtil.toJson(flowRuleList), GsonUtil.toJson(degradeRuleList));
		if (!CollectionUtils.isEmpty(flowRuleList)) {
			FlowRuleManager.loadRules(flowRuleList);
		}
		if (!CollectionUtils.isEmpty(degradeRuleList)) {
			DegradeRuleManager.loadRules(degradeRuleList);
		}
		log.info(" ######## Finish Do sentinel config update! ########");

	}

	private static DownStrategyConfig checkConfigInfo(DownStrategyConfig downStrategyConfig) {
		if (downStrategyConfig == null || downStrategyConfig.getConfigType() == null
				|| downStrategyConfig.getGradeType() == null
				|| StringUtils.isEmpty(downStrategyConfig.getResourceName())) {
			return null;
		}

		Integer configType = downStrategyConfig.getConfigType();
		if (DownStrategyConfig.ConfigTypeEnum.FlowRule.getKey() == configType) {
			Integer qpsOrThreadNum = downStrategyConfig.getQpsOrThreadNum();
			if (qpsOrThreadNum == null || qpsOrThreadNum <= 0) {
				return null;
			}
		} else if (DownStrategyConfig.ConfigTypeEnum.DegradeRule.getKey() == configType) {
			Double rtOrExcRateNum = downStrategyConfig.getRtOrExcRateNum();
			Integer windowTime = downStrategyConfig.getWindowTime();
			if (rtOrExcRateNum == null || rtOrExcRateNum <= 0) {
				return null;
			}
			if (windowTime == null || windowTime <= 0) {
				return null;
			}
		}
		return downStrategyConfig;
	}

}

/**
 * resourceName| gradeType 均必须
 *
 * DegradeRule 必须： windowTime 降级窗口时间s rtOrExcRateNum 异常|异常比|相应时间ms FlowRule 必须： windowTime 降级窗口时间s
 * rtOrExcRateNum 异常|异常比|相应时间ms
 */
