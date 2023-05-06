# servlet context

이름에서 알 수 있듯 요청과 관련된 객체를 정의한다

- view resolver
- handlerMapping
- Controller
- Interceptor
- annotation



# root context

비즈니스 로직과 관련된 부분(view와 관련 없는 부분)의 객체를 정의한다

- service
- repository
- db



# web.xml

설정을 위한 설정 파일

- WAS가 최초로 구동될 때, 각종 설정을 정의해줌
- 여러 xml 파일을 인식하도록 파일을 가리켜줌