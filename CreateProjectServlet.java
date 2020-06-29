package com.teamup.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamup.beans.Project;
import com.teamup.beans.User;
import com.teamup.utils.DBUtils;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class CreateProjectServlet
 */
@WebServlet("/CreateProject")
public class CreateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Project p = new Project();
		
		String projectName = request.getParameter("project_name");
		String category = request.getParameter("category");
        String description = request.getParameter("description");
        String budget = request.getParameter("budget");
        
        p.setTitle(projectName);
        p.setCategory(category);
        p.setDescription(description);
        p.setLeader(request.getParameter("leader"));
        p.setBudgetRequested(budget);
        
        Connection conn = MyUtils.getStoredConnection(request);
        
        try {
            // Insert the user in the DB.
            Project.insertProject(conn, p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Redirect to userInfo page.
        response.sendRedirect(request.getContextPath() + "/userInfo");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
