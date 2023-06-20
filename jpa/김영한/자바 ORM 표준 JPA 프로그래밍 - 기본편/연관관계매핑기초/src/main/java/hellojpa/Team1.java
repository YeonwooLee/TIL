package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team1 {
    @Id
    @GeneratedValue
    // @Column(name="TEAM_ID")//주석 해제시 객체에서는 id, db에서는 TEAM_ID로 사용
    private Long id;
    private String name;

    //나는 하나 상대는 여럿
    //
    /*mappedBy는 상대편측 에서 날 나타내는 property명
        양방향 매핑 규칙
        • 객체의 두 관계중 하나를 연관관계의 주인으로 지정
        • 연관관계의 주인만이 외래 키를 관리(등록, 수정)
        • 주인이 아닌쪽은 읽기만 가능!!!
        • 주인은 mappedBy 속성 사용X
        • 주인이 아니면 mappedBy 속성으로 주인 지정

        외래 키가 있는 있는 곳을 주인으로 정해라

     */
    //보통 똑같이 맞추는듯 하나 학습용으로 prop 명시함
    @OneToMany(mappedBy = "teamProp")
    private List<Member1> members = new ArrayList<>();//초기화 해두는 것이 관례(add할 때 null pointer 방지)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member1> getMembers() {
        return members;
    }

    public void setMembers(List<Member1> members) {
        this.members = members;
    }
}

