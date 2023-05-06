// Arrow Function (화살표 함수)
// function name(param) { }
// 위의 형식을 축약.
// 함수의 이름이 없는 익명함수 이므로 반드시 변수에 담아서 사용.
// const name = (param) => { };

// 매개변수 따른 설정
// () => {}; // 매개변수가 없을 경우.
// (param) => {}; // 매개변수가 한개 있을 경우. (param)의 소괄호 생략 가능.
// (param1, param2) => {}; // 매개변수가 여러개 있을 경우. (param1, param2)의 소괄호 생략 불가능.

// function body 설정.
// body의 내용이 한줄일 경우.
// (x) => {
//   return x + 10;
// };
// body의 내용이 한줄일 경우 {} 생략 가능하고 자동으로 결과값이 return된다. 위와 동일.
// (x) => x + 10;

// object return시 () 사용.
// () => {
//   return { id: "ssafy" };
// };
// () => ({ id: "ssafy" });

// // body가 여러줄일 경우 {}, return 생략 불가.
// (x) => {
//   const y = x + 100;
//   return y;
// };

// ES6 이전
function func1() {
  return 100;
}
let result = func1();
console.log(result);

// ES6 이후
// const func2 = () => {
//   return 100;
// };
const func2 = () => 100;
result = func2();
console.log(result);

// ES6 이전
function func3(x) {
  return x + 100;
}
result = func3(50);
console.log(result);

// ES6 이후
const func4 = (x) => x + 100;
result = func4(50);
console.log(result);

// ES6 이전
function func5(x, y) {
  return x + y;
}
result = func5(50, 80);
console.log(result);

// ES6 이후
const func6 = (x, y) => x + y;
result = func6(50, 80);
console.log(result);

// ES6 이전
function func7() {
  return {
    id: "ssafy",
  };
}
result = func7();
console.log(result.id);

// ES6 이후
const func8 = () => ({ id: "ssafy" });
result = func8();
console.log(result.id);

// ES6 이전
function func9(x) {
  const y = x * 3;
  return y;
}
result = func9(10);
console.log(result);

// ES6 이후
const func10 = (x) => {
  const y = x * 3;
  return y;
};
result = func10(10);
console.log(result);

// Arrow Function에서는 this가 바인딩 되지 않음.
const user = {
  id: "ssafy8",
  age: 20,
  //   info() {
  //     console.log(this.id, this);
  //   },
  info: () => console.log(this.id, this), // this 사용 불가.
};
user.info();
