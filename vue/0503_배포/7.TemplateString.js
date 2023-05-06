// Template String(`, 백틱 사용)

const id = "ssafy",
  name = "안효인",
  age = 30;
// ES6 이전
console.log(name + "(" + id + ")님의 나이는 " + age + "입니다.");
// ES6 이후
console.log(`${name}(${id})님의 


나이는 ${age}입니다.`);
