<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 결과화면</title>
</head>
<%--checkOut.jsp --%>
<body>
  <h3>선택된 상품목록</h3>
  <hr>
  <%

    //사과:100원, 수박:200원, 딸기:300원,  바나나:400원,  복숭아:500원 
  HashMap<String,Integer> map = new HashMap<>();
    map.put("사과",100);
    map.put("수박",200);
    map.put("딸기",300);
    map.put("바나나",400);
    map.put("복숭아",500);
  
    int sum=0;
    //장바구니에 담기 과일의 수만큼 출력
  	List<String> basket = (List)session.getAttribute("basket");
	for(int i=0;i<basket.size();i++){
		out.print(basket.get(i)+"<br>");
		sum += map.get(basket.get(i));
	}
    
	
    //사과:100원, 수박:200원, 딸기:300원,  바나나:400원,  복숭아:500원 
  %>
  <%-- <hr>총합계: <%= sum %>원 --%>
  <hr>총합계: <%= sum %>원 
  <br><br>
  
  <!-- 미션) 아래의 버튼을 클릭했을때 selProduct.jsp페이지로 이동해서 장바구니 비우기 -->
  <!-- <a href="selProduct.jsp">장바구니 비우기(결제)</a> -->
  <!-- 
  <form action="selProduct.jsp" method="post">
  	<input type="hidden" name="flush" value="true">
  	<input type="submit" value="장바구니 비우기(결제)">
  </form>
   -->
   <input type="button" value="장바구니 비우기(결제)"
   			onclick="location.href='selProduct.jsp?flush=trueString'">

</body>
</html>






