package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.domain.AllQuestions;
import com.domain.JavaExperience;
import com.domain.Question;
import com.domain.QuestionsPerExperience;
import com.domain.User;
import com.util.Util;

public class SqlDAOImpl implements SqlDAO {

	private Connection con = null;
	private Util util = new Util();
	private Statement stmt = null;
	private PreparedStatement pstmt=null;
	private ResultSet rs = null;
	private StringBuilder sb = null;
	
	public SqlDAOImpl() throws SQLException, ClassNotFoundException {
		con = util.conexiune();
	}

	@Override
	public List<AllQuestions> allQuestions() throws SQLException {
		List<AllQuestions> list = new ArrayList<>();

		sb = new StringBuilder();
		sb.append(" SELECT users.email email, ");
		sb.append("question.question_id id,");
		sb.append(" question.question_title questionTitle, ");
		sb.append(" SUBSTR(question.question,1,30) question, ");
		sb.append(" userQuestion.flag flag ");
		sb.append(" FROM tusers users ");
		sb.append(" INNER JOIN tuser_question userQuestion ");
		sb.append(" ON users.user_id=userQuestion.user_id ");
		sb.append(" INNER JOIN tquestions question ");
		sb.append(" ON userQuestion.question_id=question.question_id ");

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				AllQuestions allQuestions = new AllQuestions();
				User user = new User();
				Question question = new Question();
				user.setEmail(rs.getString("email"));
				question.setQuestionTitle(rs.getString("questionTitle"));
				question.setFlag(rs.getBoolean("flag"));
				question.setQuestionId(rs.getLong("id"));
				allQuestions.setUser(user);
				allQuestions.setQuestion(question);
				allQuestions.setSubStringQuestion(rs.getString("question"));
				list.add(allQuestions);
			}
		} finally {
			util.close(stmt, rs);
		}
		return list;
	}
	
	@Override
	public List<QuestionsPerExperience> questionPerExperience() throws SQLException {
		List<QuestionsPerExperience> list = new ArrayList<>();

		sb = new StringBuilder();
		sb.append(" SELECT javaExp.experience experience, ");
		sb.append(" COUNT(question.java_experience_id) total_questions ");
		sb.append(" FROM tjava_experience javaExp ");
		sb.append(" INNER JOIN tquestions question ");
		sb.append(" ON javaExp.java_experience_id=question.java_experience_id ");
		sb.append(" GROUP BY javaExp.experience ");

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				QuestionsPerExperience questionExperience = new QuestionsPerExperience();
				JavaExperience javaExp = new JavaExperience();
				javaExp.setExperience(rs.getString("experience"));
				questionExperience.setExperience(javaExp);
				questionExperience.setTotalQuestions(rs.getInt("total_questions"));
				list.add(questionExperience);
			} 
		} finally {
			util.close(stmt, rs);
		}
		return list;
	}
	
	@Override
	public List<User> loadUsers() throws SQLException{
		List<User> list=new ArrayList<>();
		
		sb = new StringBuilder();
		sb.append(" SELECT usr.user_id idUser, ");
		sb.append(" usr.first_name firstName, ");
		sb.append(" usr.last_name lastName, ");
		sb.append(" usr.email email, ");
		sb.append(" usr.pass pass, ");
		sb.append(" usr.current_job currentJob, ");
		sb.append(" usr.java_experience_id expId, ");
		sb.append(" (select experience from tjava_experience ");
		sb.append(" where java_experience_id=usr.java_experience_id) ");
		sb.append(" experience ");
		sb.append(" FROM tusers usr ");
		
		try{
			stmt=con.createStatement();
			rs=stmt.executeQuery(sb.toString());
			while(rs.next()){
				User user=new User();
				JavaExperience javaExp=new JavaExperience();
				user.setUserId(rs.getInt("idUser"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				user.setCurrentJob(rs.getString("currentJob"));
				javaExp.setJavaExperienceId(rs.getInt("expId"));
				javaExp.setExperience(rs.getString("experience"));
				user.setJavaExperience(javaExp);
				list.add(user);
			}
		} finally {
			util.close(stmt, rs);
		}			
		return list;
	}

	@Override
	public void addUser(User user) throws SQLException {
		
		sb = new StringBuilder();
		sb.append(" INSERT INTO tUsers( ");
		sb.append("  user_id,first_name,last_name,email,pass,current_job,java_experience_id ) ");
		sb.append(" VALUES ( user_id_Seq.NEXTVAL, ");
		sb.append(" ?,?,?,?,?, ");
		sb.append(" (select java_experience_id ");
		sb.append(" from tjava_experience ");
		sb.append(" where experience =? ) ) ");
		
		try{
		pstmt=con.prepareStatement(sb.toString());
		pstmt.setString(1,user.getFirstName());
		pstmt.setString(2,user.getLastName());
		pstmt.setString(3,user.getEmail());
		pstmt.setString(4,user.getPass());
		pstmt.setString(5,user.getCurrentJob());
		pstmt.setString(6,user.getJavaExperience().getExperience());
		pstmt.executeQuery();
		}finally{
			util.close(pstmt);
		}
	}

	@Override
	public List<QuestionsPerExperience> loadQuestionsPerExperience() throws SQLException {
		List<QuestionsPerExperience> list=new ArrayList<>();
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT javaExp.experience experience, ");
		sb.append(" COUNT(question.java_experience_id) total_questions ");
		sb.append(" FROM tjava_experience javaExp ");
		sb.append(" INNER JOIN tquestions question ");
		sb.append(" ON javaExp.java_experience_id=question.java_experience_id ");
		sb.append(" GROUP BY experience ");
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				QuestionsPerExperience questionsPerExperience = new QuestionsPerExperience();
				JavaExperience javaExperience = new JavaExperience();
				javaExperience.setExperience(rs.getString("experience"));
				questionsPerExperience.setExperience(javaExperience);
				questionsPerExperience.setTotalQuestions(rs.getInt("total_questions"));
				list.add(questionsPerExperience);
			}
		} finally {
			util.close(stmt, rs);
		}
		return list;
	}

	@Override
	public void addQuestion(User user, Question question) throws SQLException {
		StringBuilder sb=new StringBuilder();
		sb.append(" INSERT INTO tQuestions( ");
		sb.append(" question_id, question_title, question, java_experience_id ) ");
		sb.append(" VALUES ( question_id_Seq.NEXTVAL, ");
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ? ) ");
		
		try{
		pstmt=con.prepareStatement(sb.toString());
		pstmt.setString(1, question.getQuestionTitle());
		pstmt.setString(2,question.getQuestion());
		pstmt.setInt(3, user.getJavaExperience().getJavaExperienceId());
		pstmt.executeQuery();
		}finally{
			util.close(pstmt);
		}
		
	}

	@Override
	public void addQuestionUser(User user, Question question) throws SQLException {
		StringBuilder sb=new StringBuilder();
		sb.append(" INSERT INTO tUser_Question ");
		sb.append(" (user_id, question_id, flag ) VALUES  (?,question_id_Seq.CURRVAL,? ) ");
		
		try{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, user.getUserId());
			pstmt.setBoolean(2, question.isFlag());
			pstmt.executeQuery();
		}finally{
			util.close(pstmt);
		}
	}

	@Override
	public void setQuestionDeleted(Long question, Boolean flag) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append(" update tuser_question ");
		sb.append(" set flag=? where question_id=? ");
		
		try{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setBoolean(1, flag);
			pstmt.setLong(2, question);
			pstmt.execute();
		}finally{
			util.close(pstmt);
		}
		
		
	}
}
