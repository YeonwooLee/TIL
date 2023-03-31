# 

# JSP

# pageContext, Session, application의 차이

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

