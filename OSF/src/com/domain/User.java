package com.domain;

import java.util.List;

public class User {
	
	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private String pass;
	private String currentJob;
	private JavaExperience javaExperience;
	
	public User(){
		
	}
	
	public User(Integer userId, String firstName, String lastName, String email,String pass, String currentJob, JavaExperience javaExperience){
		this.userId=userId;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.pass=pass;
		this.currentJob=currentJob;
		this.javaExperience=javaExperience;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(String currentJob) {
		this.currentJob = currentJob;
	}

	public JavaExperience getJavaExperience() {
		return javaExperience;
	}

	public void setJavaExperience(JavaExperience javaExperience) {
		this.javaExperience = javaExperience;
	}
	
}
