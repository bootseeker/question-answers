package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "questions")
@Data
public class Questions implements Serializable
{

	private static final long serialVersionUID = -1053851110403261383L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "QUESTION_ID")
	@JsonIgnore
	private long questionId;
	@Column(name = "question")
	private String question;
	@Column(name = "CHOICE1")
	private String choice1;
	@Column(name = "CHOICE2")
	private String choice2;
	@Column(name = "CHOICE3")
	private String choice3;
	@Column(name = "CHOICE4")
	private String choice4;
	@Column(name = "CORRECT_ANSWER")
	private String correctAnswer;
	@Column(name = "TEST_SERIES")
	private String testSeries;
	@Column(name = "MARKS")
	private long marks;
	@Column(name = "HINT")
	private String hint;
	
	
}
