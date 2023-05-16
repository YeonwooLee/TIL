// if, else if, else
// 조건은 소괄호 안에 작성(필수) 중괄호는 실행할 코드가 한줄 일때는 생략가능
// 하지만 사용을 하는게 조금 더 가독성이 있음
// 파이썬과는 다르게 else if 이다.


// switch (표현식) : 표현식의 결과값을 이용한 조건문
// case문의 값과 비교를 한다. 
// break , default문은 선택적으로 사용이 가능하나 이로 인해 조금씩 기능의 차이가 있음
// 블록스코프


let month = 3

switch(month) {
  case 3: {
    console.log('봄~~~')
    break
  }
  case 4: {
    console.log('봄~~~')
    break
  }
  case 5: {
    console.log('봄~~~')
    break
  }
  case 6: {
    console.log('핫썸머~~')
    break
  }
  case 7: {
    console.log('핫썸머~~')
    break
  }
  case 8: {
    console.log('핫썸머~~')
    break
  }
  default: {
    console.log('봄에서 여름까지만 하자고~~ ')
  }
}


// 중복된 값을 써야한다면 축약해서 작성가능
let month = 3

switch(month) {
  case 3:
  case 4: 
  case 5: {
    console.log('봄~~~')
    break
  }
  case 6: 
  case 7: 
  case 8: {
    console.log('핫썸머~~')
    break
  }
  case 9: 
  case 10: 
  case 11: {
    console.log('가을이라네~~')
    break
  }
  case 12: 
  case 1: 
  case 2: {
    console.log('아이추워 겨울~~')
    break
  }
  default: {
    console.log('그거슨 우리나라의 월이 아니라네 ')
  }
}

const nation = 'Korea'

switch(nation){
  case 'Korea':{
    console.log('안녕하세요')
  }
  case 'Chinese':{
    console.log('你好')
  }
  default: {
    console.log('Hi')
  }
}
//출력결과는 3개의 인사말 모두 출력이된다~~ (break가 없어서... ㅎ)



// 기능1 : 상 확인
// 기능2 : 상하 확인
// 기능3 : 상하좌우 확인

// switch (기능?) {
//   case 3:
//     좌우 확인 기능 구현
//   case 2:
//     하 확인 기능 구현
//   case 1:
//     상 확인 기능 구현
//     break
// }
// 물론 각 기능별로 구현할 수 도 있지만 컴퓨터쟁이들은 중복을 싫어하는 경향이 있음
// 이때는 case 작성 순서도 매우 중요~