package com.revature.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Focus;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.repos.FocusRepository;
import com.revature.assignforce.service.FocusService;
import com.revature.assignforce.service.FocusServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FocusServiceImplTest {

	@Configuration
	static class BatchServiceTestContextConfiguration {
	@Bean
	public FocusService focusService() {
		return new FocusServiceImpl();
		}
	@Bean
	public FocusRepository FocusRepository() {
		return Mockito.mock(FocusRepository.class);
		}
	}
	
	@Autowired
	private FocusService focusService;
	@Autowired
	private FocusRepository focusRepository;
	
	@Test
	public void getAllTest() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		Focus f1 = new Focus(2, "Software Development", true, skillSet);
		//List<Focus> focusList = new ArrayList<Focus>();
		List<Focus> focusList = focusService.getAll();
		System.out.println(focusList.size());
		assertTrue(focusList.size() > 0);
	}

}
