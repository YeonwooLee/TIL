/*
  {key: value}  => Object === 객체 
  1. key 문자열의 따옴표 삭제 가능(띄어쓰기 없을 때, 특수문자가 없을떄)
  2. 접근할 때, ['key'] 와 .key 모두 가능
 */
  const me = {
    name: '둘리',
    // 'phone number': '010123456789',  => 가능하지만, key 도 변수명처럼 지을 것
    greeting: '어서오고',
    phoneNumber: '01012345678',
    friends: [
      '도우너', '마이콜', '또치'
    ],
    home: {
      address: '서울',
      owner: '고길동',
    }
  }
  
  /*  ES6+ 축약 문법 */
  // 1. key - value 가 같은 이름일 경우
  // Old
  var books = ['SSAFY_Java', 'SSAFY_JS']
  var magazines = ['GQ', 'esquire']
  
  var bookshop = {
    books: books,
    magazines: magazines,
  }
  
  // New
  const books = ['SSAFY_Java', 'SSAFY_JS']
  const magazines = ['GQ', 'esquire']
  
  const bookshop = {
    books,
    magazines,
  }
  
  // 2. Object 안의 함수(메서드) 정의
  // Old 
  var dooly = {
    name: 'dooly',
    greeting: function () {
      console.log('어서 오고')
    }
  }
  
  // New
  const dooly = {
    name: 'dooly',
    // Arrow
    greeting1: () => console.log('도우너,'),
    // Function 키워드 대체용
    greeting2 () {
      console.log('어서오고')
    },
  }
  
  
  // 3. Object Destructuring (비구조화)
  // Old
  var userInfo = {
    name: '김싸피',
    email: 'kim@ssafy.com',
    phone: '0101234567',
  }
  
  var name = userInfo.name
  var email = userInfo.email
  var phone = userInfo.phone
  
  // New  변수명과 key값이 같다면, 아래와 같이 작성 가능
  const userInfo = {
    name: '김싸피',
    email: 'kim@ssafy.com',
    phone: '0101234567',
  }
  
  // const { name } = userInfo
  // const { email } = userInfo
  // const { phone } = userInfo
  const { name, email, phone } = userInfo
  
  function printInfo({ name, email, phone }) {
    console.log(`안녕 나는 ${name} ${email} ${phone} `)
  }
  
  printInfo(userInfo)
  