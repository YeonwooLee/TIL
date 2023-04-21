package com.ssafy.aop.step04.after.throwing;

public class ExceptionAdvice {

	public void exceptionAfter(Exception ex){
		System.out.println("ExceptionAdvice :: " + ex.getMessage());
		ex.printStackTrace();
	}
	
}
