package com.service;

import java.sql.SQLException;
import java.util.List;

import com.DAO.SqlDAO;
import com.DAO.SqlDAOImpl;
import com.domain.CurrentUser;
import com.domain.JavaExperience;
import com.domain.Question;
import com.domain.User;

public class ServiceDaoImpl implements ServiceDao{
	private SqlDAO sqlDao = new SqlDAOImpl();
	
	public ServiceDaoImpl()throws SQLException, ClassNotFoundException{	
	}

    public boolean checkUserRegister(User user)throws SQLException{
    	List<User> userList = sqlDao.loadUsers();
    	for(User elem: userList){
    		if(elem.getEmail().equals(user.getEmail())){
    			return false;
    		}
    	}
    	return true;
    }
    
    public String checkUserLogin(User user) throws SQLException{
    	List<User> userList = sqlDao.loadUsers();
    	for(User elem: userList){
    		if(elem.getEmail().equals(user.getEmail())){
    			if(elem.getPass().equals(user.getPass())){
    				user.setUserId(elem.getUserId());
    				user.setFirstName(elem.getFirstName());
    				user.setLastName(elem.getLastName());
    				JavaExperience javaExp = new JavaExperience();
    				javaExp.setExperience(elem.getJavaExperience().getExperience());
    				javaExp.setJavaExperienceId(elem.getJavaExperience().getJavaExperienceId());
    				user.setJavaExperience(javaExp);
    				user.setCurrentJob(elem.getCurrentJob());
    				CurrentUser.setUser(user);
    				return elem.getFirstName();
    			}
    		}
    	}
    	return null;
    }

	@Override
	public void addNewQuestion(User user, Question question) throws SQLException {
		sqlDao.addQuestion(user, question);
		sqlDao.addQuestionUser(user, question);
		
	}
    
    
}
