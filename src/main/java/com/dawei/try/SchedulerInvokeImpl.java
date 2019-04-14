package com.dawei.demo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SchedulerInvokeImpl {



    public void doInvokeJob(String jobInfoStr) {
        if (jobInfoStr != null) {
            String[] jobSplit = jobInfoStr.split("-");
            String jobClassName = null;
            String jobClassMethodName = null;
            String methodParamValue = null;
            if (jobSplit.length >= 2) {
                jobClassName = jobSplit[0];
                jobClassMethodName = jobSplit[1];
            }
            if (jobSplit.length == 3) {
                methodParamValue = jobSplit[2];
            }




            //初始化类实例
            if (jobClassName == null || jobClassMethodName == null) {
                System.out.println("参数异常");
                return;
            }


            //
            List<String> realClassName = new ArrayList<>();
            //获取全类名
            List<String> classNameList = PackageUtil.getClassName("");
            if(classNameList != null) {
                for(String realName : classNameList) {
                    if(jobClassName.equalsIgnoreCase(realName.substring(realName.lastIndexOf(".") + 1))) {
                        realClassName.add(realName);
                    }
                }
            }

            if(realClassName.size() != 1) {
                System.out.println("类名不唯一");
                return;
            }
            String realJobClassName = realClassName.get(0);







            Class<?> jobClass = null;
            try {
                jobClass = Class.forName(realJobClassName);
            } catch (ClassNotFoundException e) {
                System.out.println("无此方法类");
            }
            Object newInstance = null;
            try {
                if (jobClass != null) {
                    newInstance = jobClass.newInstance();
                }
            } catch (Exception e) {
                System.out.println("方法类初始化异常");
            }


            //获取方法
            Method jobMethod = null;
            if (jobClass != null) {

                try {
                    if (methodParamValue != null) {
                        //带参的方法
                        jobMethod = jobClass.getMethod(jobClassMethodName, String.class);
                    } else {
                        //无参的方法
                        jobMethod = jobClass.getMethod(jobClassMethodName);
                    }
                } catch (NoSuchMethodException e) {
                    System.out.println("无此方法");
                }
            }

            Object resultInfo = null;
            //执行方法
            if (jobMethod != null) {
                try {
                    if (methodParamValue != null) {
                        //带参的方法
                        resultInfo = jobMethod.invoke(newInstance, methodParamValue);
                    } else {
                        //无参的方法
                        resultInfo = jobMethod.invoke(newInstance);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println(resultInfo);

        }


    }
}
