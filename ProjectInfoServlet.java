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

import com.google.gson.Gson;
import com.teamup.beans.Project;
import com.teamup.utils.DBUtils;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class ProjectInfoServlet
 */
@WebServlet("/ProjectInfo")
public class ProjectInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project p = new Project();
		Project projectDetails = new Project();
		
        p.setTitle(request.getParameter("project_name"));
        p.setLeader(request.getParameter("leader"));
        
        Connection conn = MyUtils.getStoredConnection(request);
        
        try {
            // Get project details
            projectDetails = Project.getProjectDetails(conn, p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //response.sendRedirect(request.getContextPath() + "/ProjectDetailsView.jsp");
        
        //request.setAttribute("project", projectDetails);
        //RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/projectView");
        //dispatcher.forward(request, response);
        
        String jsonProjectDetails = new Gson().toJson(projectDetails);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonProjectDetails);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
