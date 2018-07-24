package com.revature.assignforce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Focus;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.repos.FocusRepository;

@Transactional
@Service
public class FocusServiceImpl implements FocusService {

	@Autowired
	private FocusRepository focusRepository;

	@Override
	public List<Focus> getAll() {
		return focusRepository.findAll();
	}

	@Override
	public Optional<Focus> findById(int id) {
		return focusRepository.findById(id);
	}

	@Override
	public Focus update(Focus b) {
		return focusRepository.save(b);
	}

	@Override
	public Focus create(Focus b) {
		Set<SkillIdHolder> skills = b.getSkills();
		if (skills == null) {
			skills = new HashSet<SkillIdHolder>();
		}
		b.setSkills(new HashSet<SkillIdHolder>());
		focusRepository.save(b);
		b.setSkills(skills);
		focusRepository.flush();
		return focusRepository.save(b);
	}

	@Override
	public void delete(int id) {
		Optional<Focus> focus = focusRepository.findById(id);
		if (!focus.isPresent()) {
			return;
		}
		focus.get().setSkills(new HashSet<SkillIdHolder>());
		focusRepository.save(focus.get());
		focusRepository.deleteById(id);
	}

}