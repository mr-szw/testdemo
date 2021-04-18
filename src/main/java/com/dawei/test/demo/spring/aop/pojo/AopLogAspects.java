package com.dawei.test.demo.spring.aop.pojo;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author sinbad on 2021/02/19.
 */
@Aspect
public class AopLogAspects {

	@Pointcut("execution(public int com.dawei.test.demo.spring.aop.pojo.MathCalculator.*(..))")
	public void pointCut() {}


	@Before(value = "pointCut()")
	public void runBefore(JoinPoint joinPoint) {
		System.out.println("runBefore on " + joinPoint.getSignature().getName() + " args: " + Arrays.toString(joinPoint.getArgs()));
	}


	@After(value = "pointCut()")
	public void runAfter(JoinPoint joinPoint) {
		System.out.println("runAfter on " + joinPoint.getSignature().getName());
	}

	@AfterReturning(value = "pointCut()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("afterReturning on " + joinPoint.getSignature().getName() + " result: " + result);
	}

	@AfterThrowing(value = "pointCut()", throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		System.out.println("afterThrowing on " + joinPoint.getSignature().getName() + " exception: " + exception);
	}


//	@Around(value = "pointCut()")
//	public Object around(ProceedingJoinPoint proceedingJoinPoint) {
//		System.out.println("around on " + proceedingJoinPoint.getSignature().getName());
//		return proceedingJoinPoint.getTarget();
//	}
}
