package com.example.solved_group.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class SolvedGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "group_name", unique = true)
    private String name;

    @Column(name = "group_pw")
    private String pw;
}
