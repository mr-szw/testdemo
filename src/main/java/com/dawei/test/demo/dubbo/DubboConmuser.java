package com.dawei.test.demo.dubbo;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboConmuser {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-dubbo.xml"});
        context.start();
/*
        LotteryDeliveryService lotteryDeliveryService = (LotteryDeliveryService) context.getBean("lotteryDeliveryService");
        Integer integer = lotteryDeliveryService.delPrizeDelivery(1231L);*/
       /* TestMethod demoService = (TestMethod)context.getBean("testMethod"); // 获取远程服务代理
        String s = demoService.testMethod("2342");// 执行远程方法

        System.out.println("123" + s); // 显示调用结果
*/
/*
        TestService testService = (TestService) context.getBean("testService");
        String sbc = testService.testMethod("sbc");*/

    }
}
