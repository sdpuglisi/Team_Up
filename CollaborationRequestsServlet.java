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
import com.teamup.beans.Collaboration;
import com.teamup.beans.CollaborationRequest;
import com.teamup.utils.DBUtils;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class CollaborationRequestsServlet
 */
@WebServlet("/collaborationRequests")
public class CollaborationRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollaborationRequestsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CollaborationRequest> collRequestsList = new ArrayList<>();
		
		Connection conn = MyUtils.getStoredConnection(request);
        
        /* Retrieve the projectID to send the request */
        try {
        	collRequestsList = DBUtils.findCollaborationRequests(conn, request.getParameter("leader"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String jsonCollaborationRequests = new Gson().toJson(collRequestsList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonCollaborationRequests);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
