let userData;

const loginCheck = async (e) => {
	const form = document.getElementById('loginForm');
  const id = form.userId.value;
  const pw = form.userPassword.value;
  if (!id || !pw) {
    alert('아이디와 비밀번호가 모두 입력되어야 합니다.');
    return;
  }
  form.submit();
};

const doLogout = async (e) => {
  location.href="/board6_final/user?action=logout";
};

const signUpCheck = async (e) => {
  const name = this.signUpUserName.value;
  const id = this.signUpUserId.value;
  const pw = this.signUpUserPassword.value;
  const pwCheck = this.signUpUserPassword2.value;
  const email = this.signUpUserEmail.value;

  if (!name || !id || !pw || !pwCheck || !email) {
    alert('모든 정보가 입력되어야 합니다.');
    return;
  } else if (pw !== pwCheck) {
    alert('비밀번호가 일치하지 않습니다.');
    return;
  }
  if (id in userData) {
    // 중복 id
    alert('중복되는 아이디입니다.');
    return;
  }

  userData[id] = {name, pw, email};
  window.localStorage.setItem('userData', JSON.stringify(userData));
};
