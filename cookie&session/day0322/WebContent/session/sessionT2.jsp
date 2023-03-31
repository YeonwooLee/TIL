<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>사용자 인증</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
		
		.normal {  font-family: "굴림", "돋움"; font-size: x-small; font-style: normal; font-weight: normal; text-decoration: none}
		.normalbold {  font-family: "굴림", "돋움"; font-size: x-small; font-style: normal; font-weight: bold; text-decoration: none}
		
		</style>
	</head>
<%-- sessionT2.jsp --%>
<%
   /*
         로그인폼에 입력된 아이디와 비밀번호를 검사(인증)!!
        ---> DB비교
           ---> 일치(id존재, pass일치)경우: 밑의 HTML출력
           ---> 불일치경우: 로그인페이지로 이동
   */
   
   HashMap<String,String> map = new HashMap<>();//map == DataBase
   //key:아이디, value:패스워드
   //데이터저장!!
      map.put("gildong","1234");
      map.put("lime","5678");
      map.put("juwon","3333");
      
      //map.put(String key,Object value)
      //Object value = map.get(String key)
      //String str = map.get("gildong");//str==> "1234"
      //String str2 = map.get("park");//str2 ==> null
      
    //클라이언트 입력데이터 얻기
    String id = request.getParameter("id");
    String pass = request.getParameter("pass");

      
    //아이디, 비번 검사 ==> 페이지 이동
    String dbPass = map.get(id);
    
    /*
    if(dbPass==null){//아이디가 존재하지 않음
    	
    	
    }else{//아이디가 존재한다면
    	if(dbPass.equals(pass)){비ㅣㄹ번호 일치
    		
    	}else{
    		
    	}
    }
    */
    if(dbPass == null || !dbPass.equals(pass)){//로긘실패시
    	response.sendRedirect("sessionT1.jsp");
    }else{//로그인 성공
    	session.setAttribute("login","success");
    }
    
    
    //sessionT1.jsp에 입력된 id,pass를 map과 비교해서 만약 일치하지 않는다면 sessionT1.jsp로 이동~!!

%>
  <body>    
   <center>
   <p class="normalbold">사용자 인증 완료</p>
   <p class="normal"><a href="sessionT3.jsp">서비스페이지</a></p>
   </center>  
  </body>
</html>

















