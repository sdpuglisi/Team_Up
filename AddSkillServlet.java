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
import com.teamup.beans.Skill;
import com.teamup.utils.MyUtils;

/**
 * Servlet implementation class AddSkillServlet
 */
@WebServlet("/addSkill")
public class AddSkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSkillServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Skill skill = new Skill();
		
        skill.setSkillName(request.getParameter("skillName"));
        skill.setUsername(request.getParameter("username"));
        
        Connection conn = MyUtils.getStoredConnection(request);
        
        // Insert a new skill
		Skill.insertSkill(conn, skill);
        
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
