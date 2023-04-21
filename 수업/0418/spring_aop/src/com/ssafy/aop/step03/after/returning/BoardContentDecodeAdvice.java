package com.ssafy.aop.step03.after.returning;

import java.util.List;

import org.aspectj.lang.JoinPoint;

import com.ssafy.board.model.BoardDto;

public class BoardContentDecodeAdvice {

	public void decode(JoinPoint joinPoint, Object returnObj) throws Throwable{
		System.out.println("HistoryAdvice : " + returnObj);
		System.out.println("목록의 내용을 복호화하자!!!");
		if(returnObj instanceof List) {
			List<BoardDto> list = (List)returnObj;
			for(BoardDto boardDto : list) {
				boardDto.setContent(boardDto.getContent().replace("암호화", "복호화"));
			}
		}
	}
	
}
