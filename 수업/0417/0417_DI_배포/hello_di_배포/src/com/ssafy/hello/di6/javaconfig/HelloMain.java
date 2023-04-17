package com.ssafy.hello.di6.javaconfig;

public class HelloMain {

	public static void main(String[] args) {
//		TODO : ApplicationConfig.class를 사용하여 ApplicationContext 생성
		
		HelloMessage helloMessage = null;
		
		String greeting = helloMessage.hello("안효인");
//		String greeting = helloMessage.hello("Mr. An");
		
		System.out.println(greeting);
		
		System.out.println("----------------------------------------");
		
//		HelloMessage kor1 = context.getBean("kor", HelloMessageKor.class);
//		HelloMessage kor2 = context.getBean("kor", HelloMessageKor.class);
//		System.out.println(kor1 + " ::::: " + kor2);
	}
	
}
