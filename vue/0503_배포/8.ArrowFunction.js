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

// Arrow Function에서는 this가 바인딩 되지 않음.
