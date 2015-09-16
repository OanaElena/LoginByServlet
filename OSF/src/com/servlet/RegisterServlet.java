package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.SqlDAO;
import com.DAO.SqlDAOImpl;
import com.domain.JavaExperience;
import com.domain.User;
import com.service.ServiceDao;
import com.service.ServiceDaoImpl;
/**
 * Servlet implementation class RegisterServlet	
 */
//we mapped the Servet in web.xml so this adnotation is not needed anymore
//@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServiceDao serviceDao;
    private SqlDAO sqlDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
        String firstName=request.getParameter("firstName");
        String email= request.getParameter("emailName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("passwordName");
        String currentJobName = request.getParameter("currentJobName");
        String javaExperienceName = request.getParameter("javaExperienceName");
        
        User user =  new User();
        JavaExperience javaExp = new JavaExperience();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCurrentJob(currentJobName);
        user.setEmail(email);
        user.setPass(password);
        javaExp.setExperience(javaExperienceName);
        user.setJavaExperience(javaExp);
       
        PrintWriter out=response.getWriter();
		out.println("<html><head>");
        out.println("<title>Thank you</title>");
        out.println("</head>");
        out.println("<body>");
        
        try {
			serviceDao = new ServiceDaoImpl();
			sqlDao = new SqlDAOImpl();
			Boolean exist = serviceDao.checkUserRegister(user);
			if(!exist){
			out.println("<p>");
			out.println("Email address is already in use. Please log in <a href = 'login.html'>login</a>");
			out.println("</p>");
			}else{
				sqlDao.addUser(user);
				out.println("<p>");
		        out.println("Welcome "+firstName +", nice to meet you, you can now <a href = 'login.html'>login</a>");
		        out.println("</p>");
			}
		    	} catch (ClassNotFoundException e) {
					System.err.print(e);
				} catch (SQLException e) {
					System.err.print(e);
				}
        out.println("</body></html>");
        out.close();
	}

}
