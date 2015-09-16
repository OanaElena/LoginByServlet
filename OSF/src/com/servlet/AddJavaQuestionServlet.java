package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.CurrentUser;
import com.domain.Question;
import com.domain.User;
import com.service.ServiceDao;
import com.service.ServiceDaoImpl;

/**
 * Servlet implementation class AddJavaQuestionServlet
 */
//@WebServlet("/AddJavaQuestionServlet")
public class AddJavaQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceDao serviceDao;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddJavaQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		response.setContentType("text/html");
		User currentUser = CurrentUser.getUser();
		PrintWriter out=response.getWriter();
		out.println("<html><head>");
        out.println("<title>Add new Java question</title>");
        out.println("<link rel='stylesheet' type='text/css' href='bootstrap/css/bootstrap.min.css'>'");
        out.println("<link rel='stylesheet' type='text/css' href='css/register.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<form class='form-register' action='#' method='post'>");
        out.println("<h2 class='form-register-heading'>Add new question</h2>");
        out.println("<label for='inputTitle' class='sr-only'>Title question</label>");
        out.println("<input type='text' id='inputTitle' name='titleName' class='form-control' placeholder='Question title' required autofocus>");
     	out.println("<label for='inputQuestion' class='sr-only'>Question</label>");
       	out.println("<textarea id='inputQuestion' name='questionName' class='form-control'  rows='4' cols='30' placeholder='Ask here...' required></textarea>");
    	Question question = new Question();
     	question.setFlag(false);
     	question.setQuestion(request.getParameter("questionName"));
     	question.setQuestionTitle(request.getParameter("titleName"));
       	out.println("<button class='btn btn-lg btn-primary btn-block' type='submit' name='addNewQuestionName'>Add question</button>");
        if(request.getParameter("addNewQuestionName") != null){
        	try {
    			serviceDao=new ServiceDaoImpl();
    			serviceDao.addNewQuestion(currentUser, question);
    		} catch (ClassNotFoundException e) {
    			System.err.println(e);
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
        }
       	out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
        out.close();
		
		
		
	}

}
