package com.DAO;

import java.sql.SQLException;
import java.util.List;

import com.domain.AllQuestions;
import com.domain.Question;
import com.domain.QuestionsPerExperience;
import com.domain.User;

public interface SqlDAO {
	public List<AllQuestions> allQuestions() throws SQLException;
	public List<QuestionsPerExperience> questionPerExperience() throws SQLException;
	public List<User> loadUsers() throws SQLException;
	public void addUser(User user) throws SQLException;
	public List<QuestionsPerExperience> loadQuestionsPerExperience() throws SQLException;
	public void addQuestion(User user, Question question) throws SQLException;
	public void addQuestionUser(User user, Question question) throws SQLException;
	public void setQuestionDeleted(Long  question,Boolean flag) throws SQLException;
}
