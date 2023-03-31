<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="ko">
﻿<%@ include file="head.jsp" %>
<body>
	﻿<%@ include file="header.jsp" %>
	<main class="container">
	<div class="content-img">
		<img id="main-img" src="assets/img/travel.jpg" alt="travel" />
	</div>
	<div class="content-text">
		<div class="center-content-inner">
			<p class="text-center" style="font-size: 4em">Enjoy Trip</p>
			<p class="text-center" style="font-size: 1.5em">여행을 떠나고 싶은 당신을 위한
				여행정보 사이트</p>
		</div>
	</div>
	</main>
	<footer class="footer">
		<ul class="nav justify-content-center border-bottom pb-3 mb-3">
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">개인정보 처리방침</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">이용 약관</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">오시는 길</a></li>
		</ul>
		<p class="text-center text-muted">© SSAFY</p>
	</footer>
	﻿<%@ include file="loginModal.jsp" %>
	﻿<%@ include file="signUpModal.jsp" %>
	<script type="text/javascript" src="assets/js/login.js"></script>
</body>
</html>
