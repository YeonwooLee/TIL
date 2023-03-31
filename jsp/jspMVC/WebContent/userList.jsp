<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
﻿<%@ include file="head.jsp" %>
<body>
﻿<%@ include file="header.jsp" %>
	<div class="container">
		<div class="row justify-content-md-center">
			<table class="table" border="1">
				<thead>
					<tr>
						<th scope="col">id</th>
						<th scope="col">name</th>
						<th scope="col">email</th>
						<th scope="col">상세</th>
						<th scope="col">삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList }">
						<tr>
							<td>${ user.id}</td>
							<td>${ user.name}</td>
							<td>${ user.email}</td>
							<td><a class="btn btn-primary" href="/board6_final/user?action=detail&id=${user.id }">수정</a></td>
							<td><a class="btn btn-primary" href="/board6_final/user?action=remove&id=${user.id }">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${hasMsg}">
				<%session.removeAttribute("hasMsg"); %>
				<script>
					alert('${msg}');
				</script>
			</c:if>
		
			
			
			<c:if test="${removeSuccess}">
				<%session.removeAttribute("removeSuccess"); %>
				<script>
					alert("회원정보 삭제 성공");
				</script>
			</c:if>
		</div>
	</div>
   	<script type="text/javascript" src="assets/js/login.js"></script> 	
</body>
</html>