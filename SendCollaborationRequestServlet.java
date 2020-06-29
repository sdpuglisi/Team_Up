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

import com.teamup.beans.Collaboration;
import com.teamup.beans.Project;
import com.teamup.utils.DBUtils;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class SendCollaborationRequestServlet
 */
@WebServlet("/sendCollaborationRequest")
public class SendCollaborationRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendCollaborationRequestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project p = new Project();
		Collaboration c = new Collaboration();
		Project projectDetails = new Project();
		
        p.setTitle(request.getParameter("projectTitle"));
        p.setLeader(request.getParameter("leader"));
        
        Connection conn = MyUtils.getStoredConnection(request);
        
        /* Retrieve the projectID to send the request */
        try {
            projectDetails = DBUtils.getProjectDetails(conn, p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        /* Send the request - Write on COLLABORATIONS table */
        c.setProjectId(projectDetails.getProjectId());
        c.setUsername(request.getParameter("sender"));
        
        Connection conn1 = MyUtils.getStoredConnection(request);
        
        Collaboration.insertCollaboration(conn1, c);
        
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
