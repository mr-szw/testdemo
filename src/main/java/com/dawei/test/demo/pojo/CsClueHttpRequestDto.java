package com.dawei.test.demo.pojo;

import java.io.Serializable;

/**
 * @author Dawei 2019/1/9
 */
public class CsClueHttpRequestDto implements Serializable {

    //修改必穿 需求ID
    private String demandId;
    //来源
    private String hmsr;
    //212(保姆)、205(月嫂)、270(育儿嫂)
    private String category;
    //住家白班钟点工
    private String serviceType;
    //服务内容 多选项
    private String serviceItem;
    //	到岗时间
    private String expectTime;
    //	工作时间
    private String workerTime;
    //	工作地址
    private String workerAddr;
    //	预算价格
    private String expectPrice;
    //	做饭人数 工作内容标签中选择做饭必填
    private String cookCount;
    //	做饭口味 工作内容标签中选择做饭必填
    private String cookrelish;
    //	房屋面积 工作内容标签中选择打扫卫生必填
    private String floorSpace;
    //老人是否自理 工作内容标签中选择照顾老人必填
    private String oldmenCondition;
    //小孩年龄 工作内容标签选择照顾小孩必填
    private String childAge;
    //劳动者假期
    private String workerHoliday;
    //其他需求 客户自定义
    private String otherContent;

    //服务开始时间
    private String serviceBeginDate;
    //服务结束时间
    private String serviceEndDate;
    //服务区间
    private String serviceCycleTime;
    //经度
    private String latitude;
    //纬度
    private String longitude;
    //cityId
    private Integer cityId;


    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getHmsr() {
        return hmsr;
    }

    public void setHmsr(String hmsr) {
        this.hmsr = hmsr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(String serviceItem) {
        this.serviceItem = serviceItem;
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime;
    }

    public String getWorkerTime() {
        return workerTime;
    }

    public void setWorkerTime(String workerTime) {
        this.workerTime = workerTime;
    }

    public String getWorkerAddr() {
        return workerAddr;
    }

    public void setWorkerAddr(String workerAddr) {
        this.workerAddr = workerAddr;
    }

    public String getExpectPrice() {
        return expectPrice;
    }

    public void setExpectPrice(String expectPrice) {
        this.expectPrice = expectPrice;
    }

    public String getCookCount() {
        return cookCount;
    }

    public void setCookCount(String cookCount) {
        this.cookCount = cookCount;
    }

    public String getCookrelish() {
        return cookrelish;
    }

    public void setCookrelish(String cookrelish) {
        this.cookrelish = cookrelish;
    }

    public String getFloorSpace() {
        return floorSpace;
    }

    public void setFloorSpace(String floorSpace) {
        this.floorSpace = floorSpace;
    }

    public String getOldmenCondition() {
        return oldmenCondition;
    }

    public void setOldmenCondition(String oldmenCondition) {
        this.oldmenCondition = oldmenCondition;
    }

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public String getWorkerHoliday() {
        return workerHoliday;
    }

    public void setWorkerHoliday(String workerHoliday) {
        this.workerHoliday = workerHoliday;
    }

    public String getOtherContent() {
        return otherContent;
    }

    public void setOtherContent(String otherContent) {
        this.otherContent = otherContent;
    }


    public String getServiceBeginDate() {
        return serviceBeginDate;
    }

    public void setServiceBeginDate(String serviceBeginDate) {
        this.serviceBeginDate = serviceBeginDate;
    }

    public String getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(String serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public String getServiceCycleTime() {
        return serviceCycleTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
