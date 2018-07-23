package com.revature.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Focus;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.controllers.FocusController;
import com.revature.assignforce.repos.FocusRepository;
import com.revature.assignforce.service.FocusService;
import com.revature.assignforce.service.FocusServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FocusControllerTest {

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
	@Bean
	public FocusController FocusController() {
		return new FocusController();
	}
	}
	
	@Autowired
	private FocusService focusService;
	@Autowired
	private FocusRepository focusRepository;
	@Autowired
	private FocusController focusController;
	
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
		Focus f2 = new Focus(4, "Software Engineering", false, skillSet);
		List<Focus> focusList = new ArrayList<Focus>();
		focusList.add(f1);
		focusList.add(f2);
		Mockito.when(focusRepository.findAll()).thenReturn(focusList);
		List<Focus> testList = focusController.getAll();
		assertTrue(testList.size() == 2);
	}
	
	@Test
	public void getByIdTestOk() {
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
		Optional<Focus> op1 = Optional.ofNullable(f1);
		Mockito.when(focusRepository.findById(2)).thenReturn(op1);
		ResponseEntity<Focus> reTest = focusController.getById(2);
		assertTrue(reTest.getBody().getId() == 2 && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getByIdTestNotFound() {
		ResponseEntity<Focus> reTest = focusController.getById(18);
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void addTestCreated() {
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
		Focus f1 = new Focus(17, "Software Engineering", false, skillSet);
		Mockito.when(focusRepository.save(f1)).thenReturn(f1);
		ResponseEntity<Focus> reTest = focusController.add(f1);
		assertTrue(reTest.getBody().getId() == 17 && reTest.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	public void addTestBadRequest() {
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
		Focus f1 = new Focus(18, "Software Testing", false, skillSet);
		ResponseEntity<Focus> reTest = focusController.add(f1);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void updateTestCreated() {
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
		Focus f1 = new Focus(17, "Software Engineering", false, skillSet);
		f1.setName("SE");
		Mockito.when(focusRepository.save(f1)).thenReturn(f1);
		ResponseEntity<Focus> reTest = focusController.update(f1);
		assertTrue(reTest.getBody().getName().equals("SE") && reTest.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	public void updateTestBadRequest() {
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
		Focus f1 = new Focus(17, "Software Engineering", false, skillSet);
		f1.setName("Some Field");
		ResponseEntity<Focus> reTest = focusController.update(f1);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(focusRepository).deleteById(31);
		ResponseEntity<Focus> reTest = focusController.delete(31);
		assertTrue(reTest.getStatusCode() == HttpStatus.OK);
	}

}
