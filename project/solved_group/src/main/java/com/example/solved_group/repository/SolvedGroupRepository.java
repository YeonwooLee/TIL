package com.example.solved_group.repository;

import com.example.solved_group.domain.SolvedGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolvedGroupRepository extends JpaRepository<SolvedGroup,Integer> {
    SolvedGroup findByName(String name);
}
