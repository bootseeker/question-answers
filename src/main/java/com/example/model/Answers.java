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
public class Answers implements Serializable
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ANSWER_ID")
	@JsonIgnore
	private long anwserId;
	@Column(name = "QUESTION_ID")
	private long questionId;
	@Column(name = "ANSWER")
	private String answer;
	@Column(name = "EMAIL_ID")
	private long emailId;
	
}
