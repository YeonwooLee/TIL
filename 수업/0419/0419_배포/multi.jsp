<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>SSAFY-파라미터전달(Multi)</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h5 class="text-center mt-3">Spring MVC를 이용한 파라미터 전달(Multi)</h5>
		<div class="row justify-content-center">
			<div class="col-4">
			<form name="paramform" method="post" action="${root}/multi">
				<div class="form-group mt-2">
					<label for="userid">아이디:</label> <input type="text"
						class="form-control" placeholder="아이디 입력" name="userId">
				</div>
				<div class="form-group mt-2">
					<label for="username">이름:</label> <input type="text"
						class="form-control" placeholder="이름 입력" name="userName">
				</div>
				<div class="form-group mt-2">
					<label for="">좋아하는 과일:</label>
				</div>
				<div class="form-group form-check">
					<div class="form-check form-check-inline">
						<label class="form-check-label"> <input type="checkbox"
							name="fruit" class="form-check-input" value="딸기">딸기
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label"> <input type="checkbox"
							name="fruit" class="form-check-input" value="수박">수박
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label"> <input type="checkbox"
							name="fruit" class="form-check-input" value="포도">포도
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label"> <input type="checkbox"
							name="fruit" class="form-check-input" value="사과">사과
						</label>
					</div>
				</div>
				<button type="submit" class="btn btn-primary mt-2">전송</button>
			</form>
			</div>
		</div>
	</div>
</body>
</html>