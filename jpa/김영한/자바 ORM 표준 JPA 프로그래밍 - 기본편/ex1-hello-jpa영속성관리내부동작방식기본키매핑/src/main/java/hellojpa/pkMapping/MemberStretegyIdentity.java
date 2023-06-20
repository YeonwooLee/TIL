package hellojpa.pkMapping;

import javax.persistence.*;

//기본키 매핑
/*
@GeneratedValue// 값 자동 생성
    strategy//자동 생성 규칙
        =GenerationType.Identity
        =GenerationType.SEQUENCE
        =GenerationType.TABLE
        =GenerationType.AUTO

 */

/*
1. identity
- 기본 키 생성을 database에 위임
    h2사용중: default as identity
    mysql방언사용중: auto_increment
- 난 모르겠고 db야 네가 알아서 해줘

심화
- 내가 id 값을 주면 안 된다
- db에 id가 null로 들어감으로써 id가 auto로 생기므로
- db에 들어가 봐야 pk 값을 알 수 있음

- identity 한정 commit 말고 em.persist를 호출한 시점에 바로 db에 insert 쿼리 날림
-
 */
@Entity
public class MemberStretegyIdentity {
    //기본 생성자는 필수 public 또는 protected
    public MemberStretegyIdentity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
