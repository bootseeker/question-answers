package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long> {

}
