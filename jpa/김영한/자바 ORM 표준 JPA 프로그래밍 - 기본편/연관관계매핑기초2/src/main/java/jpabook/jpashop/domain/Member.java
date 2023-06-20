package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//
// @Table(UniqueConstraint = )
// @Table(indexes = )
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    //표기법(대소문자,스네이크 등)은 회사마다 다르다
    //db에서는 member_id
    @Column(name="MEMBER_ID")
    private Long id;
    @Column(length = 10)
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member")//다대다를 일대다 다대일로
    private List<MemberProduct> memberProducts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="LOCKER_ID")//일대일관계 연관관계의 주인에 외래키 설정
    private Locker locker;

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
