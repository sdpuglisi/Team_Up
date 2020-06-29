package com.teamup.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.teamup.beans.Project;
import com.teamup.beans.User;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class ViewCollaborationsServlet
 */
@WebServlet("/viewCollaborations")
public class ViewCollaborationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCollaborationsServlet() {
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
            projects = User.viewCollaborations(conn, request.getParameter("username"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String jsonProjects = new Gson().toJson(projects);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonProjects);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
