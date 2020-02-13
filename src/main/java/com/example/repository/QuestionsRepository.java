package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long> 
{
	
	Page<Questions> findByTestSeries(String testSeries,Pageable pageable);

}
