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
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class EditProjectServlet
 */
@WebServlet("/editProject")
public class EditProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProjectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project p = new Project();
		
		String projectName = request.getParameter("editProjectName").trim();
		String category = request.getParameter("editCategory");
        String description = request.getParameter("editDescription").trim();
        String status = request.getParameter("editStatus");
        
        p.setTitle(projectName);
        p.setCategory(category);
        p.setDescription(description);
        p.setStatus(status);
        
        Connection conn = MyUtils.getStoredConnection(request);
        
        try {
            // Edit the selected project
            Project.editProject(conn, request.getParameter("leader"), request.getParameter("oldProjectTitle"), p);
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
