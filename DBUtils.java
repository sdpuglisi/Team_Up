/**
 * 
 */
package com.teamup.utils;

import java.util.ArrayList;
import java.util.List;

import com.teamup.beans.Collaboration;
import com.teamup.beans.CollaborationRequest;
import com.teamup.beans.Project;
import com.teamup.beans.Rating;
import com.teamup.beans.Skill;
import com.teamup.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author s.inno, a.musciacchio, s.puglisi, m.valente
 *
 */
public class DBUtils {
	
	public static User findUser(Connection conn, String userName, String password) throws SQLException {
 
        String sql = "SELECT u.username, u.password, u.firstname, u.lastname, u.profession, u.dateofbirth FROM USERS u " //
                + " WHERE u.username = ? and u.password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            User user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setProfession(rs.getString("profession"));
            user.setDateOfBirth(rs.getString("dateofbirth"));
            return user;
        }
        return null;
    }
 
    public static User findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "SELECT u.username, u.password FROM USERS u "//
                + " WHERE u.username = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("password");
            User user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            return user;
        }
        return null;
    }
    
    public static void insertUser(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO USERS(username, firstname, lastname, profession, dateofbirth, registeredtimestamp, password) values (?,?,?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, user.getUsername());
        pstm.setString(2, user.getFirstName());
        pstm.setString(3, user.getLastName());
        pstm.setString(4, user.getProfession());
        pstm.setString(5, user.getDateOfBirth());
        if(user.getRegisteredTimestamp() == null) {
        	Date date = new Date();
        	String timestamp = new Timestamp(date.getTime()).toString();
        	pstm.setString(6, timestamp);
        } else {
        	pstm.setString(6, user.getRegisteredTimestamp());
        }
        pstm.setString(7, user.getPassword());
        
        pstm.executeUpdate();
    }
    
    public static void editPassword(Connection conn, String user, String pwd) throws SQLException {
        String sql = "UPDATE USERS SET password = ? WHERE username = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, pwd);
        pstm.setString(2, user);
        
        pstm.executeUpdate();
    }
    
    public static void deleteAccount(Connection conn, String user) throws SQLException {
        String sql = "DELETE FROM USERS WHERE username = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, user);
        
        pstm.executeUpdate();
    }
    
    public static List<Project> viewCollaborations(Connection conn, String user) throws SQLException {
   	 
    	List<Project> projectsList = new ArrayList<>();
    	
        String sql = "SELECT p.title, p.category, p.description, p.creation_date, p.leader "//
        		+ " FROM PROJECTS p "//
        		+ " JOIN COLLABORATIONS c ON c.project_id = p.project_id"//
                + " WHERE c.username = ? "//
                + " AND c.type = 'P' ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	Project project = new Project();
        	project.setLeader(rs.getString("leader"));
            project.setTitle(rs.getString("title"));
            project.setCategory(rs.getString("category"));
            project.setDescription(rs.getString("description"));
            project.setCreationDate(rs.getString("creation_date"));
            projectsList.add(project);
        }
        return projectsList;
    }
    
    public static List<Project> findProjects(Connection conn, String leader) throws SQLException {
    	 
    	//Project project = new Project();
    	List<Project> projectsList = new ArrayList<>();
    	
        String sql = "SELECT p.title, p.category, p.description, p.creation_date FROM PROJECTS p "//
                + " WHERE p.leader = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, leader);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	Project project = new Project();
            project.setTitle(rs.getString("title"));
            project.setCategory(rs.getString("category"));
            project.setDescription(rs.getString("description"));
            project.setCreationDate(rs.getString("creation_date"));
            projectsList.add(project);
        }
        return projectsList;
    }
    
    public static void insertProject(Connection conn, Project newProject) throws SQLException {
        String sqlForProjectId = "SELECT MAX(project_id)+1 AS counter FROM PROJECTS";
    	String sql = "INSERT INTO PROJECTS(project_id, title, category, description, leader, creation_date, status, budget) values (?,?,?,?,?,?,?,?)";
 
    	Integer projectId = null;
    	
        PreparedStatement pstm = conn.prepareStatement(sql);
        PreparedStatement pstm1 = conn.prepareStatement(sqlForProjectId);
 
        ResultSet rs = pstm1.executeQuery();
        
        if (rs.next()) {
        	projectId = rs.getInt("counter");
        }
        
        pstm.setInt(1, projectId);
        pstm.setString(2, newProject.getTitle());
        pstm.setString(3, newProject.getCategory());
        pstm.setString(4, newProject.getDescription());
        pstm.setString(5, newProject.getLeader());
        if(newProject.getCreationDate() == null) {
        	Date date = new Date();
        	String timestamp = new Timestamp(date.getTime()).toString();
        	pstm.setString(6, timestamp);
        } else {
        	pstm.setString(6, newProject.getCreationDate());
        }
        pstm.setString(7, "created");
        pstm.setString(8, newProject.getBudgetRequested());
        
        pstm.executeUpdate();
    }
    
    public static void editProject(Connection conn, String leader, String oldTitle, Project newProject) throws SQLException {
    	String sql = "UPDATE PROJECTS SET title = ?, category = ?, description = ?, status = ? WHERE leader = ? AND title = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, newProject.getTitle());
        pstm.setString(2, newProject.getCategory());
        pstm.setString(3, newProject.getDescription());
        pstm.setString(4, newProject.getStatus());
        pstm.setString(5, leader);
        pstm.setString(6, oldTitle);
        
        pstm.executeUpdate();
    }
    
    public static void deleteProject(Connection conn, String projectName, String leader) throws SQLException {
        String sql = "DELETE FROM PROJECTS WHERE title = ? AND leader = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, projectName);
        pstm.setString(2, leader);
        
        pstm.executeUpdate();
    }
    
    public static Project getProjectDetails(Connection conn, Project project) throws SQLException {
    	String sql = "SELECT p.project_id, p.leader, p.title, p.category, p.description, p.creation_date, p.status, p.budget, GROUP_CONCAT(c.username) AS collaborators "//
    			+ " FROM PROJECTS p "//
    			+ " LEFT JOIN COLLABORATIONS c on c.project_id = p.project_id AND c.type = 'P' "//
    			+ " WHERE p.title = ? "//
    			+ " AND p.leader = ? "//
    			+ " GROUP BY p.project_id, p.title, p.category, p.description, p.creation_date, p.budget";
 
    	Project projectDetails = new Project();
    	
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, project.getTitle());
        pstm.setString(2, project.getLeader());
        
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
        	projectDetails.setProjectId(rs.getString("project_id"));
        	projectDetails.setLeader(rs.getString("leader"));
        	projectDetails.setTitle(rs.getString("title"));
        	projectDetails.setCategory(rs.getString("category"));
        	projectDetails.setDescription(rs.getString("description"));
        	projectDetails.setCreationDate(rs.getString("creation_date"));
        	projectDetails.setStatus(rs.getString("status"));
        	projectDetails.setBudgetRequested(rs.getString("budget"));
        	projectDetails.setMembers(rs.getString("collaborators"));
        }
        
        return projectDetails;
    }
    
    public static Project getProjectIdentifier(Connection conn, Project project) throws SQLException {
    	String sql = "SELECT p.project_id "//
    			+ " FROM PROJECTS p "//
    			+ " WHERE p.title = ? "//
    			+ " AND p.leader = ? ";
 
    	Project projectDetails = new Project();
    	
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, project.getTitle());
        pstm.setString(2, project.getLeader());
        
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
        	projectDetails.setProjectId(rs.getString("project_id"));
        }
        
        return projectDetails;
    }
    
    public static List<Project> searchProjectsByCategoryOrderByOlder(Connection conn, String searchTermParam) throws SQLException {
   	 
    	List<Project> projectsList = new ArrayList<>();
    	
        String sql = "SELECT p.title, p.leader, p.category, p.description, p.creation_date "//
        		+ " FROM PROJECTS p "//
                + " WHERE p.category = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, searchTermParam);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	Project project = new Project();
            project.setTitle(rs.getString("title"));
            project.setCategory(rs.getString("category"));
            project.setDescription(rs.getString("description"));
            project.setCreationDate(rs.getString("creation_date"));
            project.setLeader(rs.getString("leader"));
            projectsList.add(project);
        }
        return projectsList;
    }
    
    public static List<Project> searchProjectsByCategoryOrderByNewest(Connection conn, String searchTermParam) throws SQLException {
      	 
    	List<Project> projectsList = new ArrayList<>();
    	
        String sql = "SELECT p.title, p.leader, p.category, p.description, p.creation_date "//
        		+ " FROM PROJECTS p "//
                + " WHERE p.category = ? "//
                + " ORDER BY p.creation_date DESC";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, searchTermParam);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	Project project = new Project();
            project.setTitle(rs.getString("title"));
            project.setCategory(rs.getString("category"));
            project.setDescription(rs.getString("description"));
            project.setCreationDate(rs.getString("creation_date"));
            project.setLeader(rs.getString("leader"));
            projectsList.add(project);
        }
        return projectsList;
    }
    
    public static List<Project> searchProjectsByCategoryOrderByRatings(Connection conn, String searchTermParam) throws SQLException {
     	 
    	List<Project> projectsList = new ArrayList<>();
    	
        String sql = "SELECT p.title, p.leader, p.category, p.description, p.creation_date "//
        		+ " FROM PROJECTS p "//
        		+ " LEFT JOIN RATINGS r on r.project_id = p.project_id"
                + " WHERE p.category = ? "//
                + " ORDER BY r.rating DESC";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, searchTermParam);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	Project project = new Project();
            project.setTitle(rs.getString("title"));
            project.setCategory(rs.getString("category"));
            project.setDescription(rs.getString("description"));
            project.setCreationDate(rs.getString("creation_date"));
            project.setLeader(rs.getString("leader"));
            projectsList.add(project);
        }
        return projectsList;
    }
    
    public static void insertCollaboration(Connection conn, Collaboration coll) throws SQLException {
        String sql = "INSERT INTO COLLABORATIONS(project_id, username, type) values (?,?, ?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, coll.getProjectId());
        pstm.setString(2, coll.getUsername());
        pstm.setString(3, "T");
        
        pstm.executeUpdate();
    }
    
    public static List<CollaborationRequest> findCollaborationRequests(Connection conn, String leader) throws SQLException {
      	
    	List<CollaborationRequest> requestsList = new ArrayList<>();
    	
        String sql = "SELECT p.project_id, u.firstname, u.lastname, u.username, u.profession, p.title"//
        		+ " FROM COLLABORATIONS c  "//
        		+ " LEFT JOIN PROJECTS p on p.project_id = c.project_id  "//
        		+ " LEFT JOIN USERS u on u.username = c.username  "//
                + " WHERE c.project_id IN (SELECT pr.project_id FROM PROJECTS pr WHERE pr.leader = ?) "//
                + " AND c.type = 'T' ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, leader);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	CollaborationRequest collaborationRequest = new CollaborationRequest();
        	collaborationRequest.setProjectId(rs.getString("project_id"));
        	collaborationRequest.setSenderFirstname(rs.getString("firstname"));
        	collaborationRequest.setSenderLastname(rs.getString("lastname"));
        	collaborationRequest.setSenderProfession(rs.getString("profession"));
        	collaborationRequest.setSenderUsername(rs.getString("username"));
        	collaborationRequest.setProjectTitle(rs.getString("title"));
        	requestsList.add(collaborationRequest);
        }
        return requestsList;
    }
    
    public static void insertRating(Connection conn, Rating r, String projectId) throws SQLException {
        String sql = "INSERT INTO RATINGS(project_id, username, rating) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, projectId);
        pstm.setString(2, r.getUsername());
        pstm.setString(3, r.getRating());
        
        pstm.executeUpdate();
    }
    
    public static void insertSkill(Connection conn, Skill s) throws SQLException {
        String sql = "INSERT INTO SKILLS(username, name) values (?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, s.getUsername());
        pstm.setString(2, s.getSkillName());
        
        pstm.executeUpdate();
    }
    
    public static void deleteSkill(Connection conn, String user, String skill) throws SQLException {
        String sql = "DELETE FROM SKILLS WHERE username = ? AND name = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, user);
        pstm.setString(2, skill);
        
        pstm.executeUpdate();
    }
    
    public static List<Skill> getSkills(Connection conn, String username) throws SQLException {
   	 
    	//Project project = new Project();
    	List<Skill> userSkillList = new ArrayList<>();
    	
        String sql = "SELECT s.name FROM SKILLS s "//
                + " WHERE s.username = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	Skill skill = new Skill();
        	skill.setSkillName(rs.getString("name"));
        	userSkillList.add(skill);
        }
        return userSkillList;
    }
    
    public static void acceptCollaboration(Connection conn, String projectId, String usernameSender) {
    	String sql = "UPDATE COLLABORATIONS SET type = 'P' WHERE project_id = ? AND username = ?";
    	 
        try {
        	PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, projectId);
			pstm.setString(2, usernameSender);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void declineCollaboration(Connection conn, String projectId, String usernameSender) {
    	String sql = "DELETE FROM COLLABORATIONS WHERE project_id = ? AND username = ? AND type = 'T'";
    	 
        try {
        	PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, projectId);
			pstm.setString(2, usernameSender);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
