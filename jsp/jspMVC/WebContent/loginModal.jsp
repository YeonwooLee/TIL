<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="loginModal" tabindex="-1"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="loginLabel">로그인</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form id="loginForm" action="user" method="post">
				<input type="hidden" name="action" value="login" />
				<div class="modal-body">
					<div class="form-group">
						<label for="userId">아이디:</label> <input type="text"
							class="form-control" id="userId" name="userId"
							placeholder="아이디를 입력하세요" />
					</div>
					<br />
					<div class="form-group">
						<label for="userPassword">비밀번호:</label> <input type="password"
							class="form-control" id="userPassword" name="userPassword"
							placeholder="비밀번호를 입력하세요." />
					</div>
					<br />
					<div class="form-check">
						<input type="checkbox" class="form-check-input"
							id="rememberIdCheck" /> <label class="form-check-label"
							for="rememberIdCheck">아이디 저장</label>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline-primary"
						onclick="loginCheck()">로그인</button>
					<button type="button" class="btn btn-outline-success">id
						찾기</button>
					<button type="button" class="btn btn-outline-secondary">비밀번호
						찾기</button>
				</div>
			</form>
		</div>
	</div>
</div>