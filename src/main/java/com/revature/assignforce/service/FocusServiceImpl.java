package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Focus;
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
		return focusRepository.save(b);
	}

	@Override
	public void delete(int id) {
		focusRepository.deleteById(id);
	}

}
