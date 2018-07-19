package com.dawei.test.demo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Dawei
 * @Date: 2018/7/19
 */
public class DemoPojo implements Serializable {


    private Long id;
    private String name;
    private Date birthday;

    public DemoPojo() {
    }

    public DemoPojo(Long id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "DemoPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
