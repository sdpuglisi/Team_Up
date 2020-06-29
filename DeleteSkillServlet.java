package com.teamup.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamup.beans.Skill;
import com.teamup.beans.User;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class DeleteSkillServlet
 */
@WebServlet("/deleteSkill")
public class DeleteSkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSkillServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
        
        try {
            // Delete a skill
            Skill.deleteSkill(conn, request.getParameter("username"), request.getParameter("skillName"));
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
