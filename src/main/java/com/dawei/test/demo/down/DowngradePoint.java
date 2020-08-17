package com.dawei.test.demo.down;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 降级注解
 *
 * @author sinbad on 2020/08/07.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DowngradePoint {

	/**
	 * 定义的资源名 最好搞上 不搞指不定出出啥问题
	 *
	 */
	String resourceName() default "";

	/**
	 * fallbackMethodName name
	 */
	String fallbackMethodName() default "";

	/**
	 * fallbackMethodName name
	 */
	Class<?> fallbackClass() default Void.class;
}
