// Default Paramteter

// ES6 이전
function print1(msg) {
  console.log(msg);
}
print1("hello ssafy");
print1();

// ES6 이후
function print2(msg = "안녕하세요 싸피") {
  console.log(msg);
}
print2("hello ssafy");
print2();

// default parameter는 함수에 전달된 파라미터가 undefined이거나 전달되지 않았을 경우, 설정한 값으로 초기화.
function getUserId(userId = "ssafy9") {
  return userId;
}

console.log(getUserId());
console.log(getUserId(undefined));
console.log(getUserId(null));
console.log(getUserId("troment"));

// default parameter각 reference type일 경우 호출될 때마다 새로운 객체를 생성함.
function appendArr(val, array = []) {
  array.push(val);
  return array;
}

console.log(appendArr(10)); // [10]
console.log(appendArr(20)); // [20] or [10, 20] ???
