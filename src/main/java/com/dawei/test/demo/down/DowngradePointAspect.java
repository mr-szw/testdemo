package com.dawei.test.demo.down;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.MethodUtil;
import com.alibaba.csp.sentinel.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 降级注解
 *
 * @author sinbad on 2020/08/07.
 */
@Aspect
@Slf4j
public class DowngradePointAspect {

	@Pointcut("@annotation(DowngradePoint)")
	public void downgradePointAspectAnnotationPointcut() {
	}

	@Around("downgradePointAspectAnnotationPointcut()")
	public Object invokeResourceWithSentinel(ProceedingJoinPoint joinPoint) throws Throwable {

		Method originMethod = resolveMethod(joinPoint);

		DowngradePoint annotation = originMethod.getAnnotation(DowngradePoint.class);
		if (annotation == null) {
			// Should not go through here.
			throw new IllegalStateException("Wrong state for SentinelResource annotation");
		}
		// String resourceName = getResourceName(annotation.resourceName(), originMethod);
		String resourceName = annotation.resourceName();
		try (Entry entry = SphU.entry(resourceName)) {
			Object result = joinPoint.proceed();
			return result;
		} catch (Throwable throwable) {
			log.warn("On call {} failed been down", resourceName);
			return handleBlockException(joinPoint, annotation, throwable);
		}
	}

	protected String getResourceName(String resourceName, /* @NonNull */ Method method) {
		// If resource name is present in annotation, use this value.
		if (StringUtil.isNotBlank(resourceName)) {
			return resourceName;
		}
		// Parse name of target method.
		return MethodUtil.resolveMethodName(method);
	}

	protected Object handleBlockException(ProceedingJoinPoint joinPoint, DowngradePoint annotation,
                                          Throwable throwable) throws Throwable {

		// Execute block handler if configured.
		Method blockHandlerMethod = extractBlockHandlerMethod(joinPoint,
				annotation.fallbackMethodName(), annotation.fallbackClass());
		if (blockHandlerMethod != null) {
			Object[] originArgs = joinPoint.getArgs();
			// Construct args.
			Object[] args = Arrays.copyOf(originArgs, originArgs.length + 1);
			args[args.length - 1] = throwable;
			try {
				if (staticMethod(blockHandlerMethod)) {
					return blockHandlerMethod.invoke(null, args);
				}
				return blockHandlerMethod.invoke(joinPoint.getTarget(), args);
			} catch (InvocationTargetException e) {
				// throw the actual exception
				throw e.getTargetException();
			}
		} else {
			throw throwable;
		}

		// If no block handler is present, then go to fallback.
	}

	private Method extractBlockHandlerMethod(ProceedingJoinPoint joinPoint, String name,
                                             Class<?> locationClass) {
		if (StringUtil.isBlank(name)) {
			return null;
		}

		Class<?> clazz = locationClass;
		if (locationClass == Void.class) {
			// By default current class.
			clazz = joinPoint.getTarget().getClass();
		}

		// First time, resolve the block handler.
		Method method = resolveBlockHandlerInternal(joinPoint, name, clazz);
		return method;
	}

	private Method resolveBlockHandlerInternal(ProceedingJoinPoint pjp, /* @NonNull */ String name,
                                               Class<?> clazz) {
		Method originMethod = resolveMethod(pjp);
		Class<?>[] originList = originMethod.getParameterTypes();
		Class<?>[] parameterTypes = Arrays.copyOf(originList, originList.length + 1);
		parameterTypes[parameterTypes.length - 1] = BlockException.class;
		return findMethod(clazz, name, originMethod.getReturnType(), parameterTypes);
	}

	protected Method resolveMethod(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Class<?> targetClass = joinPoint.getTarget().getClass();

		Method method = getDeclaredMethodFor(targetClass, signature.getName(),
				signature.getMethod().getParameterTypes());
		if (method == null) {
			throw new IllegalStateException(
					"Cannot resolve target method: " + signature.getMethod().getName());
		}
		return method;
	}

	/**
	 * 在重载方法下判断参数获取真实方法
	 */
	private Method findMethod(Class<?> clazz, String name, Class<?> returnType,
			Class<?>... parameterTypes) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (name.equals(method.getName()) && staticMethod(method)
					&& returnType.isAssignableFrom(method.getReturnType())
					&& Arrays.equals(parameterTypes, method.getParameterTypes())) {

				log.info("Resolved method [{}] in class [{}]", name, clazz.getCanonicalName());
				return method;
			}
		}
		// Current class not found, find in the super classes recursively.
		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null && !Object.class.equals(superClass)) {
			return findMethod(superClass, name, returnType, parameterTypes);
		} else {
			log.warn("Cannot find method [{}] in class [{}] with parameters {}", name,
					clazz.getCanonicalName(), Arrays.toString(parameterTypes));
			return null;
		}
	}

	/**
	 * Get declared method with provided name and parameterTypes in given class and its super
	 * classes. All parameters should be valid.
	 *
	 * @param clazz class where the method is located
	 * @param name method name
	 * @param parameterTypes method parameter type list
	 * @return resolved method, null if not found
	 */
	private Method getDeclaredMethodFor(Class<?> clazz, String name, Class<?>... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			Class<?> superClass = clazz.getSuperclass();
			if (superClass != null) {
				return getDeclaredMethodFor(superClass, name, parameterTypes);
			}
		}
		return null;
	}

	private boolean staticMethod(Method method) {
		return Modifier.isStatic(method.getModifiers());
	}
}
