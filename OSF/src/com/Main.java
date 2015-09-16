package com;

import java.sql.SQLException;
import java.util.List;

import com.DAO.SqlDAO;
import com.DAO.SqlDAOImpl;
import com.domain.AllQuestions;
import com.domain.JavaExperience;
import com.domain.QuestionsPerExperience;
import com.domain.User;

public class Main {

	public static void main(String[] args)throws SQLException, ClassNotFoundException {
		
		SqlDAO daoSql= new SqlDAOImpl();
		User user=new User();
		JavaExperience javaexp=new JavaExperience();
	
		List<QuestionsPerExperience> questionExperienceList = daoSql.questionPerExperience();
		for(QuestionsPerExperience elem: questionExperienceList){
			System.out.println( elem.getExperience().getExperience()+ " "+elem.getTotalQuestions());
		}
		
		System.out.println("-------------------------------------------------------------------------------");		
		
		List<AllQuestions> allQuestionsList=daoSql.allQuestions();
		for(AllQuestions elem: allQuestionsList){
			System.out.println(elem.getUser().getEmail() + " "+elem.getQuestion().getQuestionTitle()+ " "+elem.getSubStringQuestion() + " "+elem.getQuestion().isFlag());
		}
		
		System.out.println("-------------------------------------------------------------------------------");		
		
		List<User>userList=daoSql.loadUsers();
		for(User elem:userList){
			System.out.println(elem.getUserId()+" "+elem.getFirstName()+" "+elem.getLastName()+" "+elem.getEmail()+" "+elem.getPass()+" "+elem.getCurrentJob()+" "+elem.getJavaExperience().getJavaExperienceId()+" "+elem.getJavaExperience().getExperience());
		}
		
		System.out.println("-------------------------------------------------------------------------------");		
		
		user.setFirstName("Gogu");
		user.setLastName("Marean");
		user.setEmail("gogu@hotmail.com");
		user.setCurrentJob("sarahol java");
		user.setPass("teMarie");
		javaexp.setExperience(" > 1 year");
		user.setJavaExperience(javaexp);
		daoSql.addUser(user);
		
	}
}
