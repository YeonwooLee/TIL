// SpreadSyntax (전개구문)
// spread operator는 반복 가능한(iterable) 객체에 적용할 수 있는 문법.
// 배열이나 문자열 등을 풀어서 요소 하나 하나로 전개시킬 수 있다.

const user1 = { id: "ssafy1" };
const user2 = { id: "ssafy2" };

const arr = [user1, user2];

// array copy (reference)
const copyArr = [...arr];
console.log(arr, copyArr);
// 객체와 배열은 === 비교시 주소를 비교. false
console.log(arr === copyArr); // false
// 객체 또는 배열의 내용 비교시 JSON.stringify()사용
console.log(JSON.stringify(arr) === JSON.stringify(copyArr)); //true

// 주의 : spread operator의 경우 값 복사가 아닌 주소를 가져오기 때문에 값을 바꿀경우 모두 변경.
user1.id = "ssafy8";
console.log(arr, copyArr);

// 배열 복사 + 새로운 객체 추가.
const addArr = [...arr, { id: "ssafy3" }];
console.log(arr, addArr);

const team1 = ["서울", "광주"];
const team2 = ["대전", "부울경", "구미"];
// 배열 복사
const team3 = [...team1];
console.log(team3);
// 배열의 추가 요소로 넣기
const team4 = ["서울", ...team2, "광주"];
console.log(team4);
// 두 배열 이어 붙이기.
const team = [...team1, ...team2];
console.log(team);

//////////////////////////////////////////////////////

// 객체 복사
const copyUser = { ...user1 };
// const refUser = user1;
// console.log(user1 === refUser); // true
console.log(copyUser);
copyUser.id = "ssafy100";
console.log(user1, copyUser);
console.log(user1 === copyUser);

// 객체 복사 + 새로운 property 추가.
const copyUser2 = { ...user1, name: "안효인" };
console.log(copyUser2);

// object merge (병합)
const u1 = { id1: "ssafy1" };
const u2 = { id2: "ssafy2" };
const u = { ...u1, ...u2 };
console.log(u);

// 주의점 : key가 같은 object를 병합할 경우 마지막 obj의 값이 설정.
const user = { ...user1, ...user2 };
console.log(user);

// 함수 호출시 인자로 전달.
const num = [1, 3, 5, 7];

function plus(x, y, z) {
  return x + y + z;
}

const result = plus(...num);
console.log(result);
