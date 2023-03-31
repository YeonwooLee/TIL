# 2022-03-21 servlet 영역객체

# JSP

자바코드는 <% %> 사이에 삽입

# jsp 선언문 처리문

## directive

- page Directive - 컨테이너에 현재 jsp 페이지를 어떻게 처리할 것인가에 대한 정보 제공
  - <%@ page attr1=”val1” attr2=”val2” … %>
- include Directive
  - <% @ include
- tag Directive
  - <%@ taglib prefix(”c”

## page directive

<%@ page lanuage=”java” contentType=”text/html;charset=UTF-8” pageEncoding=”UTF-8” info=”이 jsp는 기본 예제입니다”%>

> 속성(기본값)

- language(java): 스크립트에서 사용할 언어
- info: 현재 jsp 페이지에 대한 설명
- contentType(text/html;charset=ISO-8859-1): 브라우저로 내보내는 내용의 mime형식지정 및 문자 집합 지정
- pageEncoding(ISO-8859-1): 현재 페이지 문자집합 ㅣ정
- import: 현재 jsp 페이지에서 사용할 자바 패키지나 클래스를 지정
- session(true): 세션 사용 유무지정
- errorPage: 에러가 발생할 때 대신 처리될jsp 페이지 지정
- isErrorPage(false): 현재 JSP 페이지가 에러 핸들링 페이지임을 지정
- buffer(8KB): 버퍼의 크기
- autoflush(true): 버퍼의 내용을 자동으로 브라우저로 보낼 지에 대한 설정
- isThreadsafe(true): 현재 JSP 페이지가 멀티 쓰레드로 동작해도 안전한지 여부를 설정하는 것으로 false인 경우  jsp 페이지는 singleThreade로 서비스 된다
- extends(javax.servlet.jsp.HttpJspPage): 현재 JSP 페이지를 기본적인클래스가 아닌다ㅡㄹ 클래스로부터 상속하도록 벼경

# 다시 듣기 - pageContext, Session, application의 차이

## JSP 기본객체(1)

| 기본객체    | Type                                   | 설명                                         |
| ----------- | -------------------------------------- | -------------------------------------------- |
| request     | javax.servlet.http.HttpServletRequest  | Html폼 요소의 선택 값 등을 읽어올 때 사용    |
| response    | javax.servlet.http.HttpServletResponse | 사용자 요청에 대한 응답을 처리하기 위해 사용 |
| pageContext |                                        | 내가 지금보고있는 페이지                     |
| session     |                                        | 세션 안쓰는곳 빼고 전부                      |
| application |                                        | 프로젝트 전체 영역                           |

# Scope

### pageContext

pageContext.setAttribute(key,val) - 지정한 페이지에서만 사용 가능

### request

request.setAttribute(key,val) - 페이지간 데이터 전송시 request이용

요청을 받는 다음 페이지까지 request가 살아있다

### session

session.setAttribute(key,val) - page directive에서 session=false로 지정 안 한 모든 페이지에서 사용 가능

### application

application.setAttribute(key,val) - 어플리케이션이 프로젝트임. 아무데서나 다 사용 가능.

- application에 다담으면 메모리낭비

## Scope에 속성 저장, 꺼내기, 삭제, 단체꺼내기

- void setAttribute(String name, Object value)
- Object getAttrubute(String name)
- Enumeration getAttributeName()
- void removeAttribute(String name)

## Web page 이동

### forward(request, response)

1. 사용방법

   RequestDispatcher dispatcher = request.getRequestDispatcher(path);

   dispatcher.forward(request, response);

2. 이동범위: 동일서버(project) 내 경로

3. location bar: 기존 url유지 - 실제 이동되는 주소 확인 불가

4. 객체 - 기존의 request와 response가 그대ㅗㄹ전달

5. 속도 - 비교적 빠름

6. 데이터유지 - request의 setAttribute(name,value)를 통해 전달

### sendRedirect(location)

사용법: response.sendRedirect(locatio);

이동범위: 동일 서버 포함 타 urL 가능

location bar: 이동하는 page로 변경

객체: 기존의 request와 response는 소멸되고 새로운 resquest와 response가 생성

속도: forward()에 비해 느림

데이터 유지: request로는 data저장 불가, session이나 cookie를 이용