/**
 * 
 */
package com.teamup.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.teamup.utils.DBUtils;

/**
 * @author s.inno, a.musciacchio, s.puglisi, m.valente
 *
 */
public class Rating {
	private String projectId;
	private String username;
	private String rating;
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
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}
	/**
	 * @param string the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public static boolean insertRating(Connection conn, Rating rating, String projectId) throws SQLException {
		boolean ratingAdded = false;
		
		try {
            // Insert a rating.
            DBUtils.insertRating(conn, rating, projectId);
            ratingAdded = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return ratingAdded;
    }
	
}
