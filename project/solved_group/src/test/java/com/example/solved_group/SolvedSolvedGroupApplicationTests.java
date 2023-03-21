package com.example.solved_group;

import com.example.solved_group.domain.SolvedGroup;
import com.example.solved_group.repository.SolvedGroupRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SolvedSolvedGroupApplicationTests {

	@Autowired
	SolvedGroupRepository solvedGroupRepository;

	@Test
	public void Group리포지토리_저장_테스트(){
	    //given
		SolvedGroup g1 = new SolvedGroup();

		g1.setName("login1");
		g1.setPw("pw1");

		SolvedGroup g2 = new SolvedGroup();

		g2.setName("loginId2");
		g2.setPw("pw2");
	    //when
		this.solvedGroupRepository.save(g1);
		this.solvedGroupRepository.save(g2);
	    
	    // // then
		SolvedGroup login1 = this.solvedGroupRepository.findByName("login1");
		SolvedGroup login2 = this.solvedGroupRepository.findByName("loginId2");

		System.out.println("login2 = " + login2.toString());
		System.out.println("login1 = " + login1.toString());

		Assertions.assertEquals(login1.getPw(),"pw1");
		Assertions.assertEquals(login2.getPw(),"pw2");
	}

}
