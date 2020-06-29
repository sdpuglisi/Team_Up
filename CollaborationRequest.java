/**
 * 
 */
package com.teamup.beans;

/**
 * @author s.inno, a.musciacchio, s.puglisi, m.valente
 *
 */
public class CollaborationRequest {

	private String projectId;
	private String senderFirstname;
	private String senderLastname;
	private String senderUsername;
	private String senderProfession;
	private String projectTitle;
	/**
	 * @return the senderFirstname
	 */
	public String getSenderFirstname() {
		return senderFirstname;
	}
	/**
	 * @return the senderLastname
	 */
	public String getSenderLastname() {
		return senderLastname;
	}
	/**
	 * @return the projectTitle
	 */
	public String getProjectTitle() {
		return projectTitle;
	}
	/**
	 * @param senderFirstname the senderFirstname to set
	 */
	public void setSenderFirstname(String senderFirstname) {
		this.senderFirstname = senderFirstname;
	}
	/**
	 * @param senderLastname the senderLastname to set
	 */
	public void setSenderLastname(String senderLastname) {
		this.senderLastname = senderLastname;
	}
	/**
	 * @param projectTitle the projectTitle to set
	 */
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	/**
	 * @return the senderUsername
	 */
	public String getSenderUsername() {
		return senderUsername;
	}
	/**
	 * @param senderUsername the senderUsername to set
	 */
	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}
	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the senderProfession
	 */
	public String getSenderProfession() {
		return senderProfession;
	}
	/**
	 * @param senderProfession the senderProfession to set
	 */
	public void setSenderProfession(String senderProfession) {
		this.senderProfession = senderProfession;
	}
}
