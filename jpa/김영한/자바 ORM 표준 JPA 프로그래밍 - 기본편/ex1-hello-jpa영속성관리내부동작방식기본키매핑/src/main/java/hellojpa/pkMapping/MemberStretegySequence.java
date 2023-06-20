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
2. sequence
allocationSize: 미리 시퀀스 번호 n개 땡겨와서 네트워크 타는 것 최소화
 */
@Entity
@SequenceGenerator(
        name = "member_seq_generator",
        sequenceName="member_seq",
        initialValue = 1, allocationSize = 50
)
public class MemberStretegySequence {
    //기본 생성자는 필수 public 또는 protected
    public MemberStretegySequence(){}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
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
