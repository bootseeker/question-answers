package com.example.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class QuestionsDto implements Serializable 
{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Questions> questions;
	private String pageNo;
	private String pageSize;
	private String totalPages;
	private String totalRecords;
	
}
