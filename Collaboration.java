/**
 * 
 */
package com.teamup.beans;

import java.sql.Connection;
import java.sql.SQLException;

import com.teamup.utils.DBUtils;

/**
 * @author s.inno, a.musciacchio, s.puglisi, m.valente
 *
 */
public class Collaboration {
	
	private String projectId;
	private String username;
	private String type;
	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param string the projectId to set
	 */
	public void setProjectId(String string) {
		this.projectId = string;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public static void insertCollaboration(Connection conn, Collaboration collaboration) {
		try {
			DBUtils.insertCollaboration(conn, collaboration);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void acceptCollaboration(Connection conn, String projectId, String usernameSender) {
		// Accept a collaboration
		DBUtils.acceptCollaboration(conn, projectId, usernameSender);
	}
	
	public static void declineCollaboration(Connection conn, String projectId, String usernameSender) {
		// Decline a collaboration request
		DBUtils.declineCollaboration(conn, projectId, usernameSender);
	}
}
