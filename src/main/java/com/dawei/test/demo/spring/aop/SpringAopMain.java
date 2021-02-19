package com.dawei.test.demo.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dawei.test.demo.spring.aop.config.AopBeanConfig;
import com.dawei.test.demo.spring.aop.pojo.MathCalculator;

/**
 * @author sinbad on 2021/02/19.
 */
public class SpringAopMain {


	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AopBeanConfig.class);
		MathCalculator mathCalculator = annotationConfigApplicationContext.getBean(MathCalculator.class);
		System.out.println(mathCalculator.mathDiv(2, 1));


	}


}
