//while 생략

const numbers = [1, 2, 3, 4, 5]

// for (초기식; 조건식; 증감식){
//      코드
// }
// 돌아가는 순서 확인해보기~~~

for (let i = 0; i < numbers.length; i++){
  console.log(numbers[i])
}


// for  of
for (let number of numbers){
  console.log(number)
}

const person = {
  name: 'Bob',
  age: '77'
}
for (const key in person){
  console.log(key, person[key])
}