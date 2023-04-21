package com.ssafy.aop.step04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssafy.board.controller.BoardController;
import com.ssafy.board.model.BoardDto;

public class AopTest {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		ApplicationContext context = new ClassPathXmlApplicationContext("com/ssafy/aop/step04/config/applicationContext.xml");

		BoardController boardController = context.getBean("boardController", BoardController.class);
		BoardDto boardDto = new BoardDto();
		boardDto.setUserId("ssafy");
		System.out.print("제목 : ");
		boardDto.setSubject(in.readLine());
		System.out.print("내용 : ");
		boardDto.setContent(in.readLine());

		try {
			boardController.writeArticle(boardDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("================================== 글목록 ================================== ");
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("pgno", "1");
			map.put("key", "");
			map.put("word", "");
			List<BoardDto> list = boardController.listArticle(map);
//			for (BoardDto article : list) {
//				System.out.println(article);
//			}
			System.out.println("총글수 : " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
