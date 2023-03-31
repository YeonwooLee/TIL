<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
﻿<%@ include file="head.jsp" %>
<body>
	﻿<%@ include file="header.jsp"%>
	<form class="row gy-2 gx-3 justify-content-md-center" action="user" method="post">
		<input type="hidden" name="action" value="modify"/>
		<div class="col-4">
			<div class="disabled">
			    <label for="id" class="form-label">id</label>
				<input class="form-control disabled" type="text" name="user_id" value="${user.id }" readonly />
			</div>
			<div>
			    <label for="user_name" class="form-label">이름</label>
				<input class="form-control" type="text" name="user_name" value="${user.name }" />
			</div>
			<div>    
				<label for="user_email" class="form-label">E-mail</label>
				<input class="form-control" type="text" name="user_email" value="${user.email }" />
			</div>
			<div>
			    <label for="user_password" class="form-label">pw</label>
				<input class="form-control" type="password" name="user_password" />
			</div>
			<div>
			    <label for="change_password" class="form-label">변경할 비밀번호</label>
				<input class="form-control" type="password" name="change_password" />
			</div>
			<div>
	    		<label for="change_password2" class="form-label">변경할 비밀번호 확인</label>
				<input class="form-control" type="password" name="change_password2" />
			</div>		
			<input class="btn btn-primary" type="submit" value="수정하기">
		</div>
	</form>
   	<script type="text/javascript" src="assets/js/login.js"></script> 	
</body>
</html>
