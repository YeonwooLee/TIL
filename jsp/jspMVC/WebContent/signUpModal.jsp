<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="signUpModal" tabindex="-1"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="signUpLabel">회원가입</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form id="signUpForm" method="post" action="user">
				<input type="hidden" name="action" value="join" />
				<div class="modal-body">
					<div class="form-group">
						<label for="signUpUserName">이름:</label> <input type="text"
							class="form-control" id="signUpUserName" placeholder="이름을 입력하세요"
							name="user_name" />
					</div>
					<br />
					<div class="form-group">
						<label for="signUpUserId">아이디:</label> <input type="text"
							class="form-control" id="signUpUserId" placeholder="아이디를 입력하세요"
							name="user_id" />
					</div>
					<br />
					<div class="form-group">
						<label for="signUpUserPassword">비밀번호:</label> <input
							type="password" class="form-control" id="signUpUserPassword"
							placeholder="비밀번호를 입력하세요." name="user_pw" />
					</div>
					<br />
					<div class="form-group">
						<label for="signUpUserPassword2">비밀번호 확인:</label> <input
							type="password" class="form-control" id="signUpUserPassword2"
							placeholder="비밀번호를 한번 더 입력하세요." />
					</div>
					<br />
					<div class="form-group">
						<label for="signUpUserEmail">이메일:</label> <input type="email"
							class="form-control" id="signUpUserEmail"
							placeholder="이메일을 입력하세요." name="user_email" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-outline-primary">회원가입</button>
					<button type="button" class="btn btn-outline-success">초기화</button>
					<button type="button" class="btn btn-outline-secondary">닫기</button>
				</div>
			</form>
		</div>
	</div>
</div>
