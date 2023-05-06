// Default Paramteter

// ES6 이전

// ES6 이후

// default parameter는 함수에 전달된 파라미터가 undefined이거나 전달되지 않았을 경우, 설정한 값으로 초기화.
function getUserId(userId = "ssafy9") {
  return userId;
}

console.log(getUserId());
console.log(getUserId(undefined));
console.log(getUserId(null));
console.log(getUserId("troment"));
