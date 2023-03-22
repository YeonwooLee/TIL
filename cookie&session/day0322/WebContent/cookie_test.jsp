<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>쿠키테스트</h3>
	<%
		//Cookie cookie = new ookie(String name,String value);
		Cookie cookie = new Cookie("ssafy","16반");
		Cookie cookie2 = new Cookie("kangnam","역삼");
		
		cookie.setPath("/day0322/sub");//저장할 URL 지정
		
		cookie.setMaxAge(60*60);//1시간동안 보관
		cookie2.setMaxAge(0);//cookie2를 삭제
		
		//쿠키를 사용자 PC에 저장하는 요청
		response.addCookie(cookie);
		response.addCookie(cookie2);
		
		
		
	%>
	<form method="post" action="sub/hello_cookie.jsp">
		<button>눌러주세요</button>
	</form>
</body>
</html>