package com.revature.assignforce.service;

import java.util.ArrayList;
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
		Set<SkillIdHolder> ids = new HashSet<SkillIdHolder>();
		ids.addAll(b.getSkills());
		b.setSkills(null);
		focusRepository.save(b);
		b.setSkills(ids);
		return focusRepository.save(b);
	}

	@Override
	public void delete(int id) {
		Optional<Focus> focus = focusRepository.findById(id);
		if(focus.isPresent()) {
			Focus f = focus.get();
			f.setSkills(null);
			focusRepository.save(f);
			focusRepository.deleteById(id);
		}
	}

}
