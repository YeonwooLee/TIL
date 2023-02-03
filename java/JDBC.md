# JDBC

> Java DataBase Connectivity

## JDBC 기능

### 기본

- 데이터베이스에 연결 설정
- DBMS에 SQL 전송
- SQL 문장 전송 후 결과 처리
  - C, U, D 
    - C-> 0 (fail)or 1(Success)
    - U, D -> 몇 개
  - R -> ResultSet

### JDBC API

개발자 - JDBC API - JDBC Driver of DBMS_01 - DBMS_01

​                                - JDBC Driver of DBMS_02 - DBMS_02



### RDBMS

> RDBMS는 일종의 서버
>
> 접근하기위해 URL 필요 : {protocol} : {ip} : {port}  / {system}
>
> 방화벽이 외부와의 통신을 막기 때문에 통신을 위한 port를 열어줘야한다.



## JDBC 연결

1. Driver

2. Connection
   - URL, PORT, DBNAME

​			jdbc:mysql://localhost:3306/ssafydb?serverTimezone=UTC

​			protocol     /       url            /   dbname

3. Statement
4. ResultSet
5. CLose



## JDBC 문법

- execute: Create할 때?(확실치x)

- int[] executeBatch: insert문 여러개 같은 것
- excuteUpdate: Insert, Update, Delete
- executeQuery : Single ResultSet 

### 개발순서

1. ㅇ
   - https://mvnrepository.com/



---

# JDBC ppt

> DBMS의 내부적으로 crud는 방식이 다를 수 밖에 없다
>
> 하지만 개발자 입장에서는 crud가되기만 하면 됨

## JDBC 관련 클래스

> 별표시 붙은 게 중요(CPR로 외우기)

### #DriverManager

> JVM에서 JDBC 전체를 관리하는 클래스
>
> DRIVER 등록, CONNECTION 연결 작업 등

### #Driver

> DB를 만드는 Vendor(Oracle, MS-SQL, MYSQL 등)를 
>
> **implements** 하여 자신들의 DB를 연결할 수 있는 class를 만드는 **인터페이스**

### #Connection *

> DB와 연결성을 갖는 인터페이스

### #Statement/PreparedStatement *

> SQL문을 실행하는 인터페이스

### #ResultSet *

> 조회된 결과 데이터를 갖는 인터페이스



## JDBC 프로그래밍

> 프로그래밍 순서를 서술하라는 문제가 출제되었었음

### 1. 제품군 선택 (Driver Loading) -> 선택한 제품의 driver loading

>  Oracle, MySQL, MS-SQL 등



### 2. 연결객체생성 (Connection)

- 필요요소
  1. DB서버의 주소(Host PC)
  2. 포트번호(0~65535 -> 하나의 pc가 6만5천개 이상의 서비스를 제공할 수 있다)
  3. 연결계정( Id, Password)

### 3. 실행객체생성 (Statement / preparedStatement)

- 관련요소

  1. Sql문 작성

  2. sql문 실행요청



### 4. 결과객체생성 (ResultSet)

- 관련요소
  1. 행단위 데이터 얻기
  2. 열 데이터 얻기
- grid하게 행열데이터로 저장
- 참조변수 rs
  - rs.next()로 다음행 포인팅
  - rs.getInt(column), rs.getString(column), res.getDate(column) 으로 값 가져옴
    - 여기서 column은 n(1로 시작)번째 열 or "열 이름"이 들어갈 수있다



---

# Model2

> model? == 개발방식

- model1: 구분없이 필요한 내용들(절차적인 요소들)을 프로그래밍
- model2: 내용과 형식에 따라 파일을 분류해서 프로그래밍



## MVC Model2 구조

### 1. Model(모델)

- 비즈니스로직과 관련된 부분 처리
- 데이터베이스 관련 로직 구현



### 2. View

- 사용자에게 알맞은 화면을 보여주는 역할 수행
- JSP, CustomTag
- 데이터를 시각적으로 표현하는 것
- 역할
  1. 결과값 출력
  2. 사용자 요구
  3. 사용자 데이터 입력



### 3. Controller

> 전체적인 프로그램의 흐름 제어

- 애플리케이션의 흐름제어나 사용자의처리요청을 구현
- 사용자 인증, 보안설정이라든지 전체 애플리케이션에 영향을 미치는요소 구현
- 요청에 알맞은 모델 사용 및 사용자에게 보여줄 뷰를 선택



---

# MVC

## Model1

- JSP(java server page) 파일 하나에서 db연결 html표현 등 다 함

## Model2

- Model
  - Service
  - DAO
- Controller
- View
- DTO