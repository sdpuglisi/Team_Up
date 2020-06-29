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
import com.teamup.utils.DBUtils;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class SearchProjectByCategoryServlet
 */
@WebServlet("/searchProjectByCategory")
public class SearchProjectByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProjectByCategoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Project> projects = new ArrayList<>();
		
		Connection conn = MyUtils.getStoredConnection(request);
        
        try {
            // Find projects of the current user
            projects = Project.searchProjectsByCategory(conn, request.getParameter("searchTerm"), request.getParameter("sortBy"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		request.setAttribute("projects", projects);
		request.setAttribute("searchTerm", request.getParameter("searchTerm"));
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/SearchProjectView.jsp");

        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
