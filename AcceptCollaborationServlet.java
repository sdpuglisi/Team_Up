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
import com.teamup.beans.User;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class AcceptCollaborationServlet
 */
@WebServlet("/acceptCollaboration")
public class AcceptCollaborationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptCollaborationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
        
        // Delete the user from the DB.
		Collaboration.acceptCollaboration(conn, request.getParameter("projectId"), request.getParameter("username"));
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/userInfo");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
