<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>SSAFY-파라미터전달(Single)</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h5 class="text-center mt-3">Spring MVC를 이용한 파라미터 전달(Single)</h5>
		<div class="row justify-content-center">
			<div class="col-4">
			<form name="paramform" method="post" action="${root}/single">
				<div class="form-group mt-2">
					<label for="userid">아이디:</label> <input type="text"
						class="form-control" placeholder="아이디 입력" name="userid">
				</div>
				<div class="form-group mt-2">
					<label for="username">이름:</label> <input type="text"
						class="form-control" placeholder="이름 입력" name="username">
				</div>
				<div class="form-group mt-2">
					<label for="area">지역:</label> 
					<select name="area" class="form-select">
						<option value="0">서울</option>
						<option value="1" selected>대전</option>
						<option value="2">구미</option>
						<option value="3">광주</option>
						<option value="4">부울경</option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary mt-2">전송</button>
			</form>
			</div>
		</div>
	</div>
</body>
</html>