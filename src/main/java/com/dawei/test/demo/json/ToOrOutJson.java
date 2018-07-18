package com.dawei.test.demo.json;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author by Dawei on 2018/7/13.
 */
public class ToOrOutJson {


    public static void main(String[] args) {
        String config = "[{\"name\":\"三星月嫂\",\"starnumber\":\"3\"},{\"name\":\"四星月嫂\",\"starnumber\":\"4\"},{\"name\":\"五星月嫂\",\"starnumber\":\"5\"},{\"name\":\"金牌高级\",\"starnumber\":\"6\"}]";


        List<HashMap> hashMaps = JSON.parseArray(null, HashMap.class);
        System.out.println(Arrays.toString(hashMaps.toArray()));
    }
}
