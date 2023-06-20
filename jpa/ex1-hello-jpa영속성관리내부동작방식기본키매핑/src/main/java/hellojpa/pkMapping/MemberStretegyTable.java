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
2. table
시퀀스 담당 테이블 생성
테이블에서 시퀀스만 관리한다(1번,2번,3번...)
장점: 아무 디비에서 사용 가능
단점: 성능

 */
@Entity
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ",
        allocationSize = 1
)
public class MemberStretegyTable {
    //기본 생성자는 필수 public 또는 protected
    public MemberStretegyTable(){}

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long Id;

    private String name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
