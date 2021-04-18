package com.dawei.test.demo.proxy;

/**
 * @author sunzhiwei <sunzhiwei@kuaishou.com>
 * Created on 2021-04-18
 */
public class BusinessServiceImpl implements BusinessService {
    @Override
    public String businessMethod(String argStr) {


        System.out.println("Impl do something ... on " + argStr);

        return "OK : " + argStr.toString();
    }
}
