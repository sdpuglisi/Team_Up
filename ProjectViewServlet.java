package com.teamup.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamup.beans.Project;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class ProjectViewServlet
 */
@WebServlet("/projectView")
public class ProjectViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectViewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project project = new Project();
		Project projectDetails = new Project();
		
		Connection conn = MyUtils.getStoredConnection(request);
		
		project.setTitle(request.getParameter("projectName"));
		project.setLeader(request.getParameter("leader"));
        
        try {
            // Get project details
        	projectDetails = Project.getProjectDetails(conn, project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		request.setAttribute("project", projectDetails);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ProjectDetailsView.jsp");

        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
