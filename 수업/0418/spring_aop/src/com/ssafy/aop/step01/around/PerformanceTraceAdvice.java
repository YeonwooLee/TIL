package com.ssafy.aop.step01.around;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class PerformanceTraceAdvice {

	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		String signature = joinPoint.getSignature().toShortString();
//		System.out.println("PerformanceTraceAdvice : " + signature);
		long start = System.currentTimeMillis();
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			long finish = System.currentTimeMillis();
			System.out.println("PerformanceTraceAdvice : " + signature + " 실행 시간 - " + (finish - start) + "ms");
		}
		
//		StopWatch stopWatch = new StopWatch();
//		stopWatch.start();
//		
//		Object result = joinPoint.proceed();
//
//		stopWatch.stop();
//		System.out.println("PerformanceTraceAdvice : " + signature + " 실행 시간 - " + stopWatch.getTotalTimeMillis());
//		
//		return result;
	}

}
