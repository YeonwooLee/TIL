/*
식별자 규칙
1. 반드시 문자, $, _ 로 시작한다. (숫자로 시작x)
2. 대소문자를 구분
3. 예약어 사용불가

식별자 작성 스타일

camelCase : 변수, 상수, 함수명
PascalCase : 클래스, 생성자
UPPER_SNAKE_CASE : 상수(절대로 변하면 안되는 값)

*/

// var : 재할당, 재선언 모두가능, 호이스팅, 함수스코프

var z = 1;
z = 10;
var z = 11; //가능

// 호이스팅
//실제 코드
console.log(username);
var username = "Bob";
// ----------------------
//자바스크립트가 인식하는 코드
var username;
console.log(username); // undefined
username = "Bob";
console.log(username); // Bob

// let => 재할당 가능, 재선언 불가능, 블록스코프
let x = 1;
x = 2;

let x = 3; //SyntaxError: Identifier 'x' has already been declared

// const => 재할당 불가능, 재선언 불가능, 블록스코프
const y = 1;
y = 2; //TypeError: Assignment to constant variable.

const y = 2; //SyntaxError: Identifier 'y' has already been declared

// 블록 스코프 중괄호
const greeting = "hi";

if (true) {
  console.log(greeting);
  let name = "Bob";
}
console.log(name); //에러

// let 과 const 둘다 마찬가지 에러
console.log(age);
let age = 99; //ReferenceError: age is not defined
