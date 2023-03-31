WAS의 메모리에 Object 형식으로 저장

- 저장 데이터에 제한이 없다
- session.setAttribute(String name, Object value)//속성등록
- session.getAttribute(String name):Object//속성조회
- session.removeAttribute(String name)//단일속성제거
- session.invalidate();//binding되어있는 모든 Attr 제거