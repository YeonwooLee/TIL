

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

