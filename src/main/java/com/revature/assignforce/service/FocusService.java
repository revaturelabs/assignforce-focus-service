package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import com.revature.assignforce.beans.Focus;

public interface FocusService {
	
	List<Focus> getAll();
	Optional<Focus> findById(int id);
	Focus update(Focus b);
	Focus create(Focus b);
	void delete(int id);
}
