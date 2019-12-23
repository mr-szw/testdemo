package com.dawei.test.demo.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author: Dawei
 * @Date: 2018/7/19
 */
@Data
public class DemoPojo implements Serializable {


    private Long id;
    private String name;
    private Date birthday;


}
