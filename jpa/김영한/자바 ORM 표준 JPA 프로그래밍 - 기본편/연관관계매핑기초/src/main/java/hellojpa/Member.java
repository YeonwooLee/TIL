package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
// @Table(name="USER") //table명은 USER인 경우
// @Table(uniqueConstraints = ) unique 제약조건
public class Member {
    //기본 생성자는 필수 public 또는 protected
    public Member(){}
    @Id //jpa한테 최소한 pk가 뭔지는 알려줘야함
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TEAM_ID")
    private Long teamId;

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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
