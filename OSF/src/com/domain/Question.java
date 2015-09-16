package com.domain;

public class Question {
	private Long questionId;
	private String questionTitle;
	private String question;
	private JavaExperience javaExperience;
	private boolean flag;
	
	public Question(){
		
	}
	
	public Question(Long questionId, String questionTitle, String question, JavaExperience javaExperience, boolean flag){
		this.questionId=questionId;
		this.questionTitle=questionTitle;
		this.question=question;
		this.javaExperience=javaExperience;
		this.flag=flag;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public JavaExperience getJavaExperience() {
		return javaExperience;
	}

	public void setJavaExperience(JavaExperience javaExperience) {
		this.javaExperience = javaExperience;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
