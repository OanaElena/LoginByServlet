package com.service;

import java.sql.SQLException;

import com.domain.Question;
import com.domain.User;

public interface ServiceDao {
	public boolean checkUserRegister(User user)throws SQLException;
	public String checkUserLogin(User user) throws SQLException;
	public void addNewQuestion(User user, Question question) throws SQLException;
}
