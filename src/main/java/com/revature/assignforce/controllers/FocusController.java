package com.revature.assignforce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Focus;
import com.revature.assignforce.service.FocusService;

@CrossOrigin
@RestController
public class FocusController {

	@Autowired
	FocusService focusService;

	// findAll
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Focus> getAll() {
		return focusService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Focus> getById(@PathVariable("id") int id) {
		Optional<Focus> f = focusService.findById(id);
		if (!f.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(f.get(), HttpStatus.OK);
	}

	// create
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Focus> add(@RequestBody Focus f) {
		f = focusService.create(f);
		if (f == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(f, HttpStatus.CREATED);
	}

	// update
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Focus> update(@RequestBody Focus f) {
		f = focusService.update(f);
		if (f == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(f, HttpStatus.OK);
	}

	// delete
	@DeleteMapping(value = "{id}")
	public ResponseEntity delete(@PathVariable("id") int id) {
		focusService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
