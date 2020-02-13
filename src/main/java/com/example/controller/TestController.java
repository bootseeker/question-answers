package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Test;
import com.example.repository.TestRepository;

@RestController
@RequestMapping("/api/questions/v1")
public class TestController {
	
	
	@Autowired
	private TestRepository testRepository;
	
	@GetMapping("/test123")
	public ResponseEntity<List<Test>> getAllUsers() {
		List<Test> users = new ArrayList<>();
		users = testRepository.findAll();

		return ResponseEntity.ok(users);
	}


}
