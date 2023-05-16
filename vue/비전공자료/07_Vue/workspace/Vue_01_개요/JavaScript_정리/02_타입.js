// Primitive Types
// 1. Number
// 2. String
// 3. undefined, null
// 4. Boolean

// Number
typeof 1
typeof Infinity
typeof -Infinity
typeof -1
typeof 3.14
typeof NaN
typeof -0
typeof 2.998e10

10 / 0 == Infinity


// String
const name = 'Bob'
const name2 = "Bob"
const line1 = "aaa'aaa"
const line2 = 'aaa"aaa'
const line3 = 'aaa\'aaa'

let age = 50 

console.log(`${name}은 ${age}살이며 내년에는 ${age+1}살이 된다.`)
// Bob은 50살이며 내년에는 51살이 된다.
console.log(name+"은 "+age+"살이며")
// Bob은 50살이며


// undefined, null
// 둘다 값이 없음을 나타내는데 의도의 차이가 있다.!!!!!!


// Boolean
true, false 

// 자바스크립트 false
null
undefined
0
''
NaN
