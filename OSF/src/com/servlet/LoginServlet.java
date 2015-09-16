package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.service.ServiceDao;
import com.service.ServiceDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceDao serviceDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String email = request.getParameter("emailName");
		String password = request.getParameter("passwordName");
		
		User user = new User();
		user.setEmail(email);
		user.setPass(password);
		
		 PrintWriter out=response.getWriter();
			out.println("<html><head>");
	        out.println("<title>HomePage</title>");
	        out.println("<link rel='stylesheet' type='text/css' href='bootstrap/css/bootstrap.min.css'>'");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<div class='container'>");
	        		
	        try {
				serviceDao = new ServiceDaoImpl();
				String firstName = serviceDao.checkUserLogin(user);
				if(firstName == null){
					out.println(" <script type='text/javascript'>");
					out.println("alert('Email sau parola incorecta.')");
			        out.println(" window.location.href = 'login.html'");
			        out.println(" </script>");
				}else{
					out.println("   <p>    Welcome {"+firstName +"}    </p>");
					out.println(" Link:");
					out.println(" <form class='form-register' action='AddJavaQuestionServlet' method='post'>");
					out.println(" <button type='submit'>");
					out.println(" Add new Java question");
					out.println("</button>");
					out.println("</form>");
					out.println(" <form class='form-register' action='ViewQuestionsServlet' method='post'>");
					out.println(" <button type='submit'>");
					out.println(" View all Java questions and statistics");
					out.println("</button>");
					out.println("</form>");
					out.println("<button onclick='location.href = \"externalwebservice.html\"' >Invoke external web service</button>");
				}
	        } catch (ClassNotFoundException e) {
				System.err.print(e);
			} catch (SQLException e) {
				System.err.print(e);
			}
	        out.println("</div>");
	        out.println("</body></html>");
	        out.close();
	}

}
