package com.revature.assignforce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Focus;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.commands.SkillsCommand;
import com.revature.assignforce.repos.FocusRepository;

@Transactional
@Service
public class FocusServiceImpl implements FocusService {

	@Autowired
	private FocusRepository focusRepository;
	
	@Autowired
	private SkillsCommand skillsCommand;
	
	@Override
	public List<Focus> getAll() {
		return focusRepository.findAll();
	}

	@Override
	public Optional<Focus> findById(int id) {
		return focusRepository.findById(id);
	}

	@Override
	public Focus update(Focus focus) {
		
		Set<SkillIdHolder> skills = focus.getSkills();
		
		if (skills == null) {
			skills = new HashSet<>();
			focus.setSkills(skills);
		}
		
		focus = validateSkillReferences(focus);
		
		return focusRepository.save(focus);
	}

	@Override
	public Focus create(Focus focus) {
		
		Set<SkillIdHolder> skills = focus.getSkills();
		
		if (skills == null) {
			skills = new HashSet<>();
			focus.setSkills(skills);
		}
		
		focus = validateSkillReferences(focus);
		
		return focusRepository.save(focus);
	}

	@Override
	public void delete(int id) {
		focusRepository.deleteById(id);
	}
	
	private Focus validateSkillReferences(Focus focus) {
		
		focus.setSkills(focus.getSkills().stream().filter(
				(skillIdHolder) -> skillsCommand.findSkill(skillIdHolder)).collect(Collectors.toSet()));
		
		return focus;
	}

}
