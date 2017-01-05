package com.cloud.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subjects")
public class Subject extends Task {
	private Date start;
	private String profesor;
	private Date exam;
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	public Date getExam() {
		return exam;
	}
	public void setExam(Date exam) {
		this.exam = exam;
	}
	
}
