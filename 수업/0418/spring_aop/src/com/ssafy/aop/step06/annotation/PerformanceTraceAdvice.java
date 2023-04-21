package com.ssafy.aop.step06.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Aspect
public class PerformanceTraceAdvice {

	@Pointcut("execution(public * com.ssafy.board..*Dao.*(..))")
	public void profileTarget() {
	}

	@Around("profileTarget()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		String signature = joinPoint.getSignature().toShortString();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object result = joinPoint.proceed();

		stopWatch.stop();
		System.out.println("PerformanceTraceAdvice : " + signature + " 실행 시간 - " + stopWatch.getTotalTimeMillis());

		return result;
	}

}
