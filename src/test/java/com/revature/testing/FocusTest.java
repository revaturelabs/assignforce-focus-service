package com.revature.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Focus;
import com.revature.assignforce.beans.SkillIdHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FocusTest {
	
	private static ValidatorFactory factory;
	private static Validator validator;

	@Configuration
	static class FocusTestContextConfiguration {
	@Bean
	public Focus Focus() {
		return new Focus();
		}
	}
	
	@BeforeClass
	public static void createFactory() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	@AfterClass
	public static void closeFactory() {
		factory.close();
	}
	
	@Test
	public void focusTest1() {
		Focus f1 = new Focus();
		assertNotNull(f1);
	}
	
	@Test
	public void focusTest2() {
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
		assertTrue(f1.getId() == 2);
	}
	
	@Test
	public void getSetId() {
		Focus f1 = new Focus();
		f1.setId(22);
		assertTrue(f1.getId() == 22);
	}
	
	@Test
	public void getSetNameTest() {
		Focus f1 = new Focus();
		f1.setName("Java");
		assertTrue(f1.getName().equals("Java"));
	}
	
	@Test
	public void getSetIsActiveTest() {
		Focus f1 = new Focus();
		f1.setIsActive(true);
		assertTrue(f1.getIsActive());
	}
	
	@Test
	public void getSetSkillsTest() {
		Focus f1 = new Focus();
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
		f1.setSkills(skillSet);
		assertTrue(f1.getSkills().size() == 5);
	}

    @Test
    public void testFocusWithNoValues() {
        Focus f1 = new Focus();

        // validate the input
        Set<ConstraintViolation<Focus>> violations = validator.validate(f1);
        assertEquals(5, violations.size());
    }
    
    @Test
    public void testValidFocus() {
		Focus f1 = new Focus();
		f1.setIsActive(true);
		f1.setName("Java");
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
		f1.setSkills(skillSet);
		
		Set<ConstraintViolation<Focus>> violations = validator.validate(f1);
		assertEquals(0, violations.size());
    }
}
