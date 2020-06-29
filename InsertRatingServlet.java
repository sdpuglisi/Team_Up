package com.teamup.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.teamup.beans.Project;
import com.teamup.beans.Rating;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class InsertRatingServlet
 */
@WebServlet("/insertRating")
public class InsertRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRatingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project p = new Project();
		Rating rating = new Rating();
		Project targetProject = new Project();
		
		String ratingResponse = "";
		
        p.setTitle(request.getParameter("project_name"));
        p.setLeader(request.getParameter("leader"));
        
        Connection conn = MyUtils.getStoredConnection(request);
        
        /* Retrieve the projectID to add a rating */
        try {
        	targetProject = Project.getProjectIdentifier(conn, p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        rating.setProjectId(targetProject.getProjectId());
        rating.setUsername(request.getParameter("username"));
        rating.setRating(request.getParameter("rating"));
        
        Connection conn1 = MyUtils.getStoredConnection(request);
        
        try {
            boolean success = Rating.insertRating(conn1, rating, targetProject.getProjectId());
            if(success) {
            	ratingResponse = "{ \"response\": \"OK\"}";
            } else {
            	ratingResponse = "{ \"response\": \"KO\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String convertedObject = new Gson().toJson(ratingResponse);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(convertedObject);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
