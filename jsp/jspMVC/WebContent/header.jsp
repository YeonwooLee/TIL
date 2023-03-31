<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header style="margin: 0px 25px">
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid">
			<img class="navbar-brand" src="assets/img/logo.png"
				style="height: 70px" alt="logo"
				onclick="javascript:location.href='./index.jsp';" />
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarScroll"
				aria-controls="navbarScroll" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarScroll">
				<ul class="navbar-nav" style="-bs-scroll-height: 100px">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${root}/tripinfo?action=sidoList">지역별관광지</a>
					</li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">나의여행계획</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">핫플자랑하기</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">여행정보공유</a></li>

					<c:if test="${loginUser != null}">
						<li class="nav-item"><a id="a-logout"
							class="nav-link active" aria-current="page" href="#"
							onclick="doLogout();" >로그아웃</a></li>
					</c:if>
					<c:if test="${loginUser == null}">
						<li class="nav-item"><a id="a-signup"
							class="nav-link active" aria-current="page"
							data-bs-toggle="modal" href="#signUpModal">
								회원가입 </a></li>

						<li class="nav-item"><a id="a-login" class="nav-link active"
							aria-current="page" data-bs-toggle="modal" href="#loginModal"
							>로그인</a></li>
					</c:if>
					<c:if test="${loginUser.id eq 'admin'}">
						<li class="nav-item"><a id="a-logout"
							class="nav-link active" aria-current="page" href="user?action=list"
							 >회원관리</a></li>
					</c:if>

				</ul>
			</div>
		</div>
	</nav>
</header>