package com.dawei.test.demo.pojo;

import org.junit.Test;

import com.dawei.test.demo.utils.GsonUtil;

import lombok.Data;

/**
 * @author by Dawei on 2018/8/22.
 * 新的返回实体Dto
 */
@Data
public class ResultDto<T>  {

    private Integer code;

    private String codeMsg;

    private T data;

}
