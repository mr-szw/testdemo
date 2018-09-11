package com.dawei.test.demo.newworkplan;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author by Dawei on 2018/9/11.
 * 预约信息
 */
public class ReservationSellerDto implements Serializable{

    private static final long serialVersionUID = -1753224947385449263L;

    /* 预约信息ID */
    private Long detailId;
    /* 商家Id */
    private Long workerId;
    /* 业务线ID */
    private Integer serviceId;
    /* 服务名称 [保姆：///] [月嫂：几星几星月嫂] */
    private String serviceName;
    /* 加密的workerId  */
    private String encryptWorkerId;
    /* 工作类型 :[保姆：住家|白班|钟点工]  */
    private String workType;
    /* 月嫂星级 */
    private String starLevel;
    /* 预约城市名称  */
    private String cityStr;
    /* 服务地址 */
    private String serviceAddress;
    /* 详情页URL */
    private String detailUrl;
    /* 服务开始日期 */
    private String serviceStarDate;
    /* 服务时间段 */
    private String serviceTimePart;
    /* 服务时间周期 */
    private String serviceCycle;
    /* 匹配状态 */
    private String matchStatus;
    /* 预约状态 删除/更新状态 */
    private String reservationStatus;
    /* 技能 list决定顺序 key为技能项 value为技能值  */
    private List<HashMap<String, String>> skillsMap;

}
