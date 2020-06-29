package com.teamup.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamup.beans.User;
import com.teamup.utils.DBUtils;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User u = new User();
		
		String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();
        String profession = request.getParameter("profession").trim();
        String dateOfBirth = request.getParameter("dateofbirth");
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        
        u.setUsername(username);
        u.setFirstName(firstname);
        u.setProfession(profession);
        u.setLastName(lastname);
        u.setDateOfBirth(dateOfBirth);
        u.setPassword(password);
        
        Connection conn = MyUtils.getStoredConnection(request);
        
        try {
            // Insert the user in the DB.
            User.insertUser(conn, u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        request.setAttribute("username", username);
        request.setAttribute("password", password);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login");

        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
