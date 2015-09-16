package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.SqlDAO;
import com.DAO.SqlDAOImpl;
import com.domain.AllQuestions;
import com.domain.CurrentUser;
import com.domain.QuestionsPerExperience;

/**
 * Servlet implementation class ViewQuestionsServlet
 */
//@WebServlet("/ViewQuestionsServlet")
public class ViewQuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SqlDAO sqlDao; 
    private int nrCrt = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewQuestionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter("flag") != null&&request.getParameter("id")!=null)
		{try {
			sqlDao = new SqlDAOImpl();
	
		sqlDao.setQuestionDeleted(Long.valueOf(request.getParameter("id").replaceAll("row", "").trim()), true);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		PrintWriter out=response.getWriter();
		out.println("<html><head>");
        out.println("<title>View questions and statistics</title>");
        out.println("<script src='js/questions.js'></script>");
        out.println("<link rel='stylesheet' type='text/css' href='bootstrap/css/bootstrap.min.css'>'");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='row'>");
        out.println("<div class='col-xs-4'>");
        out.println("<h2 class='form-register-heading'>View statistics</h2>");
        out.println("<table id='questionPerExperienceTable' class='table table-bordered'>");
        out.println("<tr>");
        out.println("<th>Experience</th>");
        out.println("<th>Total questions</th>");
        out.println("</tr>");
        try {
        	sqlDao = new SqlDAOImpl();
			List<QuestionsPerExperience> questionPerExperienceList = sqlDao.loadQuestionsPerExperience();
			for(QuestionsPerExperience elem: questionPerExperienceList){
				 out.println("<tr>");
				 out.println("<td>"+elem.getExperience().getExperience()+"</td>");
				 out.println("<td>"+elem.getTotalQuestions()+"</td>");
				 out.println("</tr>");
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e);
		}
        
        out.println("</table>");
        out.println("</div>");
        out.println("<div class='col-xs-6'>");
        out.println("<h2 class='form-register-heading'>View all questions</h2>");
        out.println("<table class='table table-bordered' id='allQuestionsTable'>");
        out.println("<tr>");
        out.println("<th>Order No.</th>");
        out.println("<th>Email</th>");
        out.println("<th>Question title</th>");
        out.println("<th>Question</th>");
        out.println("<th></th>");
        out.println("</tr>");
        
        try {
			List<AllQuestions> list = sqlDao.allQuestions();
			for(AllQuestions elem: list){
				String style="";
				if(elem.getQuestion().isFlag()){
					style=" style=\"background-color:red\";";

				}
				out.println("<tr "+style+"id = 'row"+elem.getQuestion().getQuestionId()+"'>");
				
				
				int rand = nrCrt;
				
				out.println("<td>"+ ++nrCrt +"</td>");
				out.println("<td>"+elem.getUser().getEmail()+"</td>");
				out.println("<td>"+elem.getQuestion().getQuestionTitle()+"</td>");
				out.println("<td>"+elem.getSubStringQuestion()+"..."+"</td>");
				
				
				if(!elem.getQuestion().isFlag()){
					out.println("<td> <button id='butrow"+elem.getQuestion().getQuestionId()+"' type='button' onclick = modifyDatabaseFlag(row"+elem.getQuestion().getQuestionId()+",butrow"+elem.getQuestion().getQuestionId()+") class='btn btn-default btn-xs'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span> Delete</button></td>");
				}else{
					out.println("<td></td>");
					
				}
				out.println("</tr>");
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
        out.println("</table>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body></html>");
        out.close();
	}

}
