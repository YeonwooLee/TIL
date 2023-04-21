package com.ssafy.aop.step05.after;

import org.aspectj.lang.JoinPoint;

public class CountAdvice {

	public void after(JoinPoint joinPoint) {
		String name = joinPoint.toShortString();
		System.out.println("CountAdvice : " + name);
	}
	
}
