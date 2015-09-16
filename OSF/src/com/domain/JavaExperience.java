package com.domain;

public class JavaExperience {
	private Integer javaExperienceId;
	private String experience;
	
	public JavaExperience(){
		  
	}
	public JavaExperience(Integer javaExperienceId, String experience){
		this.javaExperienceId=javaExperienceId;
		this.experience=experience;
	}
	
	public Integer getJavaExperienceId() {
		return javaExperienceId;
	}
	public void setJavaExperienceId(Integer javaExperienceId) {
		this.javaExperienceId = javaExperienceId;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
}
