/**
 * 
 */
package com.teamup.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.teamup.utils.DBUtils;
import com.teamup.utils.MyUtils;

/**
 * @author s.inno, a.musciacchio, s.puglisi, m.valente
 *
 */
public class Project {

	private String projectId;
	private String creationDate;
	private String category;
	private String title;
	private String description;
	private String leader;
	private String budgetRequested;
	private String status;
	private String members;
	
	public Project() {}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the budgetRequested
	 */
	public String getBudgetRequested() {
		return budgetRequested;
	}
	
	/**
	 * @return the members
	 */
	public String getMembers() {
		return members;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param budget the budgetRequested to set
	 */
	public void setBudgetRequested(String budget) {
		this.budgetRequested = budget;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(String members) {
		this.members = members;
	}

	/**
	 * @return the leader
	 */
	public String getLeader() {
		return leader;
	}

	/**
	 * @param leader the leader to set
	 */
	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	public static void insertProject(Connection conn, Project newProject) throws SQLException {
		DBUtils.insertProject(conn, newProject);
    }
	
	public static void editProject(Connection conn, String leader, String oldTitle, Project editedProject) throws SQLException {
		DBUtils.editProject(conn, leader, oldTitle, editedProject);
    }
	
	public static void deleteProject(Connection conn, String project, String leader) throws SQLException {
		try {
            // Delete the project.
            DBUtils.deleteProject(conn, project, leader);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static List<Project> findProjects(Connection conn, String leader) throws SQLException {
   	 
    	List<Project> projectsList = new ArrayList<>();
    	
        try {
            // Find projects of the current user
        	projectsList = DBUtils.findProjects(conn, leader);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
        return projectsList;
    }
	
	public static Project getProjectDetails(Connection conn, Project project) throws SQLException {
    	Project projectInfo = new Project();
    	
    	try {
            // Get project details
    		projectInfo = DBUtils.getProjectDetails(conn, project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return projectInfo;
    }
	
	public static Project getProjectIdentifier(Connection conn, Project project) throws SQLException {
    	Project projectInfo = new Project();
    	
    	try {
            // Get project ID
    		projectInfo = DBUtils.getProjectIdentifier(conn, project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return projectInfo;
    }
	
	public static List<Project> searchProjectsByCategory(Connection conn, String category, String sorting) throws SQLException {
	   	 
    	List<Project> projectsByCategory = new ArrayList<>();
    	
    	try {
            // Search projects by search term (category)
    		switch(sorting) {
	    		case "1":
	    			projectsByCategory = DBUtils.searchProjectsByCategoryOrderByOlder(conn, category);
	    			break;
	    		case "2":
	    			projectsByCategory = DBUtils.searchProjectsByCategoryOrderByNewest(conn, category);
	    			break;
	    		case "3":
	    			projectsByCategory = DBUtils.searchProjectsByCategoryOrderByRatings(conn, category);
	    			break;
	    		default:
	    			projectsByCategory = DBUtils.searchProjectsByCategoryOrderByOlder(conn, category);
    		}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
        return projectsByCategory;
    }

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
