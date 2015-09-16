package com.domain;

public class AllQuestions {
	private User user;
	private Question question;
	private String subStringQuestion;
	
	public AllQuestions(){
	
	}
	
	public AllQuestions(User user, Question question, String subStringQuestion){
		this.user=user;
		this.question=question;
		this.subStringQuestion=subStringQuestion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getSubStringQuestion() {
		return subStringQuestion;
	}

	public void setSubStringQuestion(String subStringQuestion) {
		this.subStringQuestion = subStringQuestion;
	}

	
}
