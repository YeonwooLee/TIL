// DestructuringAssignment (구조 분해 할당)
// 배열이나 객체에 입력된 값을 개별적인 변수에 할당하는 간편한 방식 제공.

// 배열
const areas = ["광주", "구미", "서울", "대전", "부울경"];

// ES6 이전
{
  const a1 = areas[0];
  const a2 = areas[1];
  const a3 = areas[2];
  const a4 = areas[3];
  const a5 = areas[4];

  console.log(a1, a2, a3, a4, a5);
}

// ES6 이후
{
  const [a1, a2, a3, a4, a5] = areas;
  console.log(a1, a2, a3, a4, a5);
}

// 객체
const user = {
  id: "ssafy",
  name: "안효인",
  age: 30,
};

// ES6 이전
{
  let id = user.id;
  let name = user.name;
  let age = user.age;
  console.log(id, name, age);
}

// ES6 이후
// 객체의 property와 변수명이 같을 경우.
{
  let { id, name, age } = user;
  console.log(id, name, age);
}

// 변수명을 객체의 property명과 다르게 만들 경우.
{
  let { id: user_id, name: user_name, age: user_age } = user;
  console.log(user_id, user_name, user_age);
}

// 객체를 return 하는 함수.
function getUser() {
  return {
    id: "ssafy",
    name: "안효인",
    age: 30,
  };
}

// ES6 이전
{
  let user = getUser();
  let id = user.id;
  let name = user.name;
  let age = user.age;
  console.log(id, name, age);
}

// ES6 이후
{
  let { id, name, age } = getUser();
  console.log(id, name, age);
}

// ES6 이전
function showUserInfo1(user) {
  console.log("showUserInfo1 call");
  let id = user.id;
  let naem = user.name;
  let age = user.age;
  console.log("아이디 : " + id);
  console.log("이름 : " + name);
  console.log("나이 : " + age);
}
showUserInfo1(user);

// ES6 이후
function showUserInfo2({ id, name, age }) {
  console.log("showUserInfo2 call");
  console.log("아이디 : " + id);
  console.log("이름 : " + name);
  console.log("나이 : " + age);
}
showUserInfo2(user);
