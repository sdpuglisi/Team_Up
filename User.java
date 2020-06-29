/**
 * 
 */
package com.teamup.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.teamup.utils.DBUtils;

import java.sql.PreparedStatement;

/**
 * @author s.inno, a.musciacchio, s.puglisi, m.valente
 *
 */
public class User {
    
	private String username;
	private String firstName;
	private String lastName;
	private String profession;
	private String dateOfBirth;
	private String registeredTimestamp;
	private String password;
	
	public User() {}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the registeredTimestamp
	 */
	public String getRegisteredTimestamp() {
		return registeredTimestamp;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param registeredTimestamp the registeredTimestamp to set
	 */
	public void setRegisteredTimestamp(String registeredTimestamp) {
		this.registeredTimestamp = registeredTimestamp;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void insertUser(Connection conn, User user) throws SQLException {
		try {
            // Insert the user in the DB.
            DBUtils.insertUser(conn, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static User findUser(Connection conn, String userName, String password) throws SQLException {
		User user = null;
		
		try {
            // Find the user in the DB.
            user = DBUtils.findUser(conn, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return user;
    }
	
	public static void editPassword(Connection conn, String username, String password) throws SQLException {
		try {
            // Edit the password for the current account
            DBUtils.editPassword(conn, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static void deleteAccount(Connection conn, String username) throws SQLException {
		try {
            // Delete the user from the DB.
            DBUtils.deleteAccount(conn, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static List<Project> viewCollaborations(Connection conn, String user) throws SQLException {
	   	 
    	List<Project> projectsList = new ArrayList<>();
    	
        try {
        	projectsList = DBUtils.viewCollaborations(conn, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
        return projectsList;
    }
}
