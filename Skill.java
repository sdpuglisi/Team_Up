/**
 * 
 */
package com.teamup.beans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teamup.utils.DBUtils;

/**
 * @author s.inno, a.musciacchio, s.puglisi, m.valente
 *
 */
public class Skill {

	private String username;
	private String skillName;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the skillName
	 */
	public String getSkillName() {
		return skillName;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param skillName the skillName to set
	 */
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
	public static void insertSkill(Connection conn, Skill newSkill) {
		try {
			DBUtils.insertSkill(conn, newSkill);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteSkill(Connection conn, String username, String skill) throws SQLException {
		try {
            // Delete a skill
            DBUtils.deleteSkill(conn, username, skill);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static List<Skill> getSkills(Connection conn, String user) {
		List<Skill> skillList = new ArrayList<>();
		
		try {
            // Find skills for the current user
			skillList = DBUtils.getSkills(conn, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return skillList;
	}
	
	
}
