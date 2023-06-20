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
    private Long id;


    /*
    insertable = true or false
    updatable = true or false
    nullable = true or false
    unique: 잘 안 씀, 이름이 알아볼 수 없는 이름이라
    columnDefinition: 컬럼 정보 직접 준다("varchar(100) default 'EMPTY'") 이런식으로
    length: 문자열 제약조건에만 사용
    precision, scale: BigInteger나 소수점 다룰 때
     */
    @Column(name="name")//db column은 name, 객체는 username
    private String username;

    private Integer age;

    /*
    enumtype
    ordinal - enum 순서를 db에 저장
    string - enum 이름을 db에 저장
    
     */
    @Enumerated(EnumType.STRING)//enum 타입
    private RoleType roleType;

    //날짜타입: DATE(날짜), TIME(시간), TIMESTAMP(날짜시간)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient//db랑 매핑하지 마세요
    private int temp;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
