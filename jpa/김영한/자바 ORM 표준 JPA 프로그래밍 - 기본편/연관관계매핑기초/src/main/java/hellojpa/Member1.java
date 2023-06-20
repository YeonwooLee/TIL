package hellojpa;

import javax.persistence.*;

@Entity
// @Table(name="USER") //table명은 USER인 경우
// @Table(uniqueConstraints = ) unique 제약조건
public class Member1 {
    //기본 생성자는 필수 public 또는 protected
    public Member1(){}
    @Id //jpa한테 최소한 pk가 뭔지는 알려줘야함
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /*teamId에서 수정
    무슨 연관관계인지 알려줘야함
     */
    @ManyToOne(fetch = FetchType.LAZY)//Member는 여러개, TeAM은 하나인 다대일 매핑이므로, fetchtype은 지연로딩전략
    @JoinColumn(name="TEAM_Thunder_ID")//name은 Member1테이블에 team.id를 나타내는 외래키 이름
    private Team1 teamProp;//teamProp은 연관관계 상대편측에서 사용

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team1 getTeamProp() {
        return teamProp;
    }

    public void changeTeamProp(Team1 teamProp) {
        this.teamProp = teamProp;
        //양방향 매핑시 객체 관계를 고려하여 양쪽 객체에 값을 세팅해줘야한다
        //그러나 사람이 까먹을 수도 있으니까 연관관계의 주인에 값을 셍팅할 때 노예에도 값이 세팅되도록 하자
        teamProp.getMembers().add(this);
    }
}
