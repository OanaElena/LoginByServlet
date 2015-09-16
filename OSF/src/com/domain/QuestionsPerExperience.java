package com.domain;

public class QuestionsPerExperience {
	private JavaExperience experience;
	private Integer totalQuestions;
	
	public QuestionsPerExperience(){
		
	}
	
	public QuestionsPerExperience(JavaExperience experience, Integer totalQuestions){
		this.experience=experience;
		this.totalQuestions=totalQuestions;
	}

	public JavaExperience getExperience() {
		return experience;
	}

	public void setExperience(JavaExperience experience) {
		this.experience = experience;
	}

	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	
}
