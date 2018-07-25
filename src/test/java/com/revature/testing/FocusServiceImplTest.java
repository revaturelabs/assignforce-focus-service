package com.revature.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockitoSession;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Focus;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.repos.FocusRepository;
import com.revature.assignforce.repos.SkillRepository;
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

		@Bean
		public SkillRepository SkillRepository() {
			return Mockito.mock(SkillRepository.class);
		}
	}

	@Autowired
	private FocusService focusService;
	@Autowired
	private FocusRepository focusRepository;
	@Autowired
	private SkillRepository skillRepository;

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
		Focus f3 = new Focus(6, "Software Testing", true, skillSet);
		List<Focus> focusList = new ArrayList<Focus>();
		focusList.add(f1);
		focusList.add(f2);
		focusList.add(f3);
		Mockito.when(focusRepository.findAll()).thenReturn(focusList);

		List<Focus> testList = focusService.getAll();
		assertTrue(testList.size() == 3);
	}

	@Test
	public void findByIdTest() {
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

		Optional<Focus> opTest = focusService.findById(2);
		assertTrue(opTest.get().getId() == 2);
	}

	@Test
	public void updateTest() {
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
		f1.setIsActive(false);
		Mockito.when(focusRepository.save(f1)).thenReturn(f1);

		Focus testFocus = focusService.update(f1);
		assertTrue(testFocus.getIsActive() == false);
	}
	
	@Test
	public void updateTest2() {
		Focus f1 = new Focus();
		f1.setIsActive(true);
		Mockito.when(focusRepository.save(f1)).thenReturn(f1);
		Focus fTest = focusService.update(f1);
		assertTrue(fTest.getIsActive());
	}

	@Test
	public void createTest() {
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
		Mockito.when(focusRepository.save(f1)).thenReturn(f1);

		Focus testFocus = focusService.update(f1);
		assertTrue(testFocus.getId() == 2);
	}
	
	@Test
	public void createTest2() {
		Focus f1 = new Focus();
		Mockito.when(focusRepository.save(f1)).thenReturn(f1);
		Focus fTest = focusService.create(f1);
		assertNotNull(fTest);
	}

	@Test
	public void deleteTest() {
		Mockito.doNothing().when(focusRepository).deleteById(5);
		focusService.delete(5);
		Optional<Focus> focusTest = focusService.findById(5);
		assertFalse(focusTest.isPresent());
	}
	
	@Test
	public void deleteTest2() {
		Focus f1 = new Focus();
		f1.setId(17);
		Optional<Focus> op1 = Optional.ofNullable(f1);
		Mockito.when(focusRepository.findById(17)).thenReturn(op1);
		Mockito.doNothing().when(focusRepository).deleteById(17);
		focusService.delete(17);
		Optional<Focus> op2 = Optional.ofNullable(null);
		Mockito.when(focusRepository.findById(17)).thenReturn(op2);
		Optional<Focus> opTest = focusRepository.findById(17);
		assertFalse(opTest.isPresent());
	}

}
