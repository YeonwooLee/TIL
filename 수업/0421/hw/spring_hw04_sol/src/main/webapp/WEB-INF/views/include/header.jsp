<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.hw.model.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="root"></c:set>
<style>
#user-info {
	text-align: right;
}
</style>
<div id="user-info">
	<!-- 코드작성 -->
	<%-- session에서 loginUser를 가져와서 존재 여부에 따라 로그인 폼 또는 사용자 정보를 출력한다. --%>
		<%--로그인 정보가 없을 경우는 로그인을 위한 폼을 제공한다. --%>
		<c:if test="${empty loginUser }">	
			<form method="post" action="${root }/login">
				<div>
					<input type="text" name="id" placeholder="아이디">
					<input type="password" name="password" placeholder="비밀번호">
					<input type="submit" value="로그인">
				</div>
			</form>
		</c:if>		
		<%--로그인 정보가 있는 경우는 사용자 이름과 로그아웃을 위한 링크를 생성한다.--%>	
		<c:if test="${!empty loginUser }">	
			<div>
				${loginUser.name }님 반갑습니다.
				<a href="${root }/logout">로그아웃</a>
			</div>		
		</c:if>	
</div>
<hr>
<script>
	// request 영역에 msg라는 이름의 attribute가 있다면 화면에 alert으로 출력한다.
	let msg = "${msg}";
	if (msg) {
		alert(msg)
	}
</script>