https://steady-coding.tistory.com/594
- 스프링 빈은 스프링 컨테이너에 의해 관리되는 자바 객체(POJO)를 의미한다.
- 해당 객체를 싱글톤 패턴을 유지하며 사용하도록 스프링이 관리

#### #클래스를 빈으로 등록하는 방법
```java

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

}
```