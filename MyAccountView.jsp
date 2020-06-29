<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta charset="UTF-8">
	
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" integrity="sha256-9mbkOfVho3ZPXfM7W8sV2SndrGDuh7wuyLjtsWeTI1Q=" crossorigin="anonymous" />
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<link rel="stylesheet" href="css/global.css">
		<link rel="stylesheet" href="css/my-account.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		
		<script src="js/global.js"></script>
	
		<title>TeamUp | ${user.firstName} ${user.lastName}</title>
	</head>
	<body>
		<input id="user-username" type="hidden" value="${user.username}" />
    	<c:set var="username" value="${user.username}" scope="application" />
    	<c:set var="firstname" value="${user.firstName}" scope="application" />
    	<c:set var="lastname" value="${user.lastName}" scope="application" />
	
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <strong><span style="color:orange; font-size:20px">Team</span><span style="color:#3079D1; font-size:20px">Up!</span></strong>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <li class="nav-item active" style="display:none">
		        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item" style="display:none">
		        <a class="nav-link" href="#">Link</a>
		      </li>
		      <li class="nav-item" style="display:none">
		        <a class="nav-link disabled" href="#">Disabled</a>
		      </li>
		    </ul>
		    <div class="dropdown">
			  <button class="btn btn-secondary dropdown-toggle" style="border-radius:20px; background-color:white; color:grey" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    Search projects
			  </button>
			  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			  	<h6 class="dropdown-header">Categories:</h6>
			  	<a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Art&sortBy=1">Art</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Design&sortBy=1">Design</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Electronic&sortBy=1">Electronic</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Entertainment&sortBy=1">Entertainment</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Medicine&sortBy=1">Medicine</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Motors&sortBy=1">Motors</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Music&sortBy=1">Music</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Software Engineering&sortBy=1">Software Engineering</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Sport and Events&sortBy=1">Sport and Events</a>
			    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=Sustainability&sortBy=1">Sustainability</a>
			  </div>
			</div>
		  	<ul class="navbar-nav">
          	  <li class="nav-item">
	              <a class="nav-link" href="${pageContext.request.contextPath}/userInfo"><i class="fa fa-user"></i> ${firstname} ${lastname}</a>
              </li>
	          <li class="nav-item">
	              <a class="nav-link" onclick="logoutFunction()" href="#" style="color:#dc3545"><i class="fa fa-sign-out"></i> Logout</a>
              </li>
             </ul>
		  </div>
		</nav>
	
		<div class="container emp-profile">
            <!-- <form method="post"> -->
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                            <img src="images/profile_logo.png" alt="Avatar"/>
                            <!-- <div class="file btn btn-lg btn-primary">
                                Change Photo
                                <input type="file" name="file"/>
                            </div> -->
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                            <h5>
                                ${user.firstName} ${user.lastName}
                            </h5>
                            <h6 style="padding-bottom:20px">
                                ${user.profession}
                            </h6>
                            <!-- <p class="proile-rating">RANKINGS : <span>8/10</span></p> -->
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true"><i class="fa fa-user"></i> About Me</a>
                                </li>
                                <!-- <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Timeline</a>
                                </li> -->
                                <li class="nav-item">
                                    <a class="nav-link" id="my-projects-tab" data-toggle="tab" onClick="findMyProjects('${pageContext.request.contextPath}/MyProjects?leader=${user.username}')" href="#projects" role="tab" aria-controls="projects" aria-selected="false"><i class="fa fa-tasks"></i> My Projects</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="collaborations-tab" data-toggle="tab" onclick="viewCollaborations('${pageContext.request.contextPath}/viewCollaborations?username=${user.username}')" href="#collaborations" role="tab" aria-controls="collaborations" aria-selected="false"><i class="fa fa-users"></i> Collaborations</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="my-new-project-tab" data-toggle="tab" href="#new-project" role="tab" aria-controls="new-project" aria-selected="false"><i class="fa fa-plus"></i> New project</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="teams-tab" onClick="findCollaborationRequests('${pageContext.request.contextPath}/collaborationRequests?leader=${user.username}')" data-toggle="tab" href="#teams" role="tab" aria-controls="teams" aria-selected="false"><i class="fa fa-user-plus"></i> Teams</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <input type="button" class="profile-edit-btn" name="btnAddMore" data-toggle="modal" data-target="#deleteProfileModal" value="Delete Profile"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <p style="font-weight:500 !important">SKILLS</p>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab" style="width:75%">
	                            <form action="${pageContext.request.contextPath}/editPassword?username=${user.username}" method="POST">
		                            <div class="row">
		                                <div class="col-md-5">
		                                    <label>First name</label>
		                                </div>
		                                <div class="col-md-6">
		                                    <p>${user.firstName}</p>
		                                </div>
		                            </div>
		                            <div class="row">
		                                <div class="col-md-5">
		                                    <label>Last name</label>
		                                </div>
		                                <div class="col-md-6">
		                                    <p>${user.lastName}</p>
		                                </div>
		                            </div>
		                            <div class="row">
		                                <div class="col-md-5">
		                                    <label>Email</label>
		                                </div>
		                                <div class="col-md-6">
		                                    <p>${user.username}</p>
		                                </div>
		                            </div>
		                            <div class="row">
		                                <div class="col-md-5">
		                                    <label>Password</label>
		                                </div>
		                                <div class="col-md-6">
		                                    <input id="my-account-password" type="password" class="form-control" style="margin-bottom:10px" name="newPassword" value="12345678" placeholder="New password" required>
		                                </div>
		                            </div>
		                            <div class="row">
		                                <div class="col-md-5">
		                                    <label>Profession</label>
		                                </div>
		                                <div class="col-md-6">
		                                    <p>${user.profession}</p>
		                                </div>
		                            </div>
		                            <div class="" style="float:right">
										<input id="confirm-password-container" class="btn btn-primary" style="display:none; margin-right:5px" type="submit" value="Confirm new password">
								  	</div>
	                            </form>
                            </div>
                            
                            <%-- My Projects section --%>
                            <div class="tab-pane fade" id="projects" style="margin-top:-15px;" role="tabpanel" aria-labelledby="my-projects-tab">
                                        
                            </div>
                            
                            <%-- Collaborations section --%>
                            <div class="tab-pane fade" id="collaborations" style="margin-top:-15px;" role="tabpanel" aria-labelledby="collaborations-tab">
                            	<!-- <h4>Collaborations</h4> -->
                            </div>
                            
                            <%-- My project details --%>
                            <div id="my-project-detail-container">
					        	<i class="fa fa-times close-modal"></i>
					        	<h3 id="my-project-title" style="font-weight:bold"></h3>
					        	<div class="meta" style="margin-top:-10px; margin-bottom:10px; color:grey">
					        		<span id="my-project-category"></span>
					        	</div>
					        	<h4 style="font-weight:bold">Description</h4>
								<p id="my-project-description"></p>
								<h4 style="font-weight:bold">Team</h4>
								<ul id="collaborators-list" class="list-group list-group-flush" style="padding-bottom:15px">
								  
								</ul>
								<h4 style="font-weight:bold">Status</h4>
								<p id="my-project-status"></p>
								<h4 style="font-weight:bold">Budget</h4>
								<p id="my-project-budget"></p>
								<div class="controls-my-projects" style="float:right">
									<button type="button" data-toggle="modal" data-target="#editProjectModal" class="btn btn-primary" style="margin-right:5px" onclick="editProject()">Edit this project</button>
									<button type="button" data-toggle="modal" data-target="#deleteProjectModal" class="btn btn-danger">Delete this project</button>
								</div>
					        </div>
					        
					        <%-- New project section --%>
					        <div class="tab-pane fade" id="new-project" style="width:75%; margin-top:-10px;">
					            <!-- <h4 class="mt-2"><strong>Create a new project</strong></h4> -->
					            <form id="create-project" style="padding-top:10px" action="${pageContext.request.contextPath}/CreateProject?leader=${user.username}" method="POST">
								  <div class="form-group row">
								    <label for="projectName" class="col-sm-2 col-form-label" style="color:#495057">Name</label>
								    <div class="col-sm-10">
								      <input type="text" class="form-control" id="projectName" placeholder="Project Name" name="project_name" value="" autocomplete="off" required>
								    </div>
								  </div>
								  <div class="form-group row">
								    <label for="category" class="col-sm-2 col-form-label" style="color:#495057">Category</label>
								    <div class="col-sm-10">
								      <select class="browser-default custom-select" name="category" required>
							              <option disabled selected>Choose the category of your project</option>
							              <option value="Art">Art</option>
							              <option value="Design">Design</option>
							              <option value="Electronic">Electronic</option>
							              <option value="Entertainment">Entertainment</option>
										  <option value="Medicine">Medicine</option>
										  <option value="Motors">Motors</option>
										  <option value="Music">Music</option>
										  <option value="Software Engineering">Software Engineering</option>
										  <option value="Sport and Events">Sport and Events</option>
										  <option value="Sustainability">Sustainability</option>
						              </select>
								    </div>
								  </div>
								  <div class="form-group row">
								    <label for="description" class="col-sm-2 col-form-label" style="color:#495057">Description</label>
								    <div class="col-sm-10">
								      <textarea class="form-control" rows="4" id="comment" name="description" required></textarea>
								    </div>
								  </div>
								  <div class="form-group row">
								    <label for="budget" class="col-sm-2 col-form-label" style="color:#495057">Budget</label>
								    <div class="col-sm-10">
								      <input type="text" class="form-control" name="budget" id="budget" placeholder="$ 1,000,000.00" data-type="currency"  value="" autocomplete="off">
								    </div>
								  </div>
								 
								  <div class="form-buttons" style="float:right">
								  	<input class="btn btn-primary" type="submit" value="Post the project">
								  	<input class="btn btn-secondary reset-form" type="button" value="Cancel" onClick="resetForm('#create-project')">
								  </div>
								</form>
					        </div>
					        
					        <%-- Teams section --%>
					        <div class="tab-pane fade" id="teams" style="width:75%; margin-top:-10px">
					            <h4 class="mt-2"><strong>Increase your team</strong></h4>
					            
					        </div>
                            
                        </div>
                    </div>
                </div>
            <!-- </form>      -->      
        </div>
        
        <!-- Modal - add skill -->
		<div class="modal fade" id="addSkillModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">Add a new skill</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		      	<form action="${pageContext.request.contextPath}/addSkill?username=${user.username}" method="post">
		        	<input type="text" class="form-control" name="skillName" placeholder="Write here your skill">
		        	<div class="buttons-form-skills">
			        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        	<button type="submit" class="btn btn-primary" style="margin-left:5px">Add skill</button>
		        	</div>
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Modal - delete profile -->
		<div class="modal fade" id="deleteProfileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">Please, confirm you choice</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <!-- <input type="text" class="form-control" name="skill" placeholder="Write here your skill"> -->
		        <p>Are you sure to delete your account? This action is irreversible.</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <a href="${pageContext.request.contextPath}/deleteAccount?username=${user.username}"><button id="delete-project-button" type="button" class="btn btn-danger">Delete</button></a>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Modal - edit project -->
		<div class="modal fade" id="editProjectModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">Edit this project</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form id="create-project" style="padding-top:10px" >
				  <input type="hidden" name="leader" value="${user.username}" />
				  <input type="hidden" name="oldProjectTitle" value="" />
				  <div class="form-group row">
				    <label for="projectName" class="col-sm-2 col-form-label"><strong>Name</strong></label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="edit-project-name" placeholder="Project Name" name="editProjectName" value="" required>
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="category" class="col-sm-2 col-form-label"><strong>Category</strong></label>
				    <div class="col-sm-10">
				      <select class="browser-default custom-select" id="edit-category" name="editCategory" required>
			              <option disabled selected>Choose the category of your project</option>
			              <option value="Art">Art</option>
			              <option value="Design">Design</option>
			              <option value="Electronic">Electronic</option>
			              <option value="Entertainment">Entertainment</option>
						  <option value="Medicine">Medicine</option>
						  <option value="Motors">Motors</option>
						  <option value="Music">Music</option>
						  <option value="Software Engineering">Software Engineering</option>
						  <option value="Sport and Events">Sport and Events</option>
						  <option value="Sustainability">Sustainability</option>
		              </select>
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="description" class="col-sm-2 col-form-label"><strong>Description</strong></label>
				    <div class="col-sm-10">
				      <textarea class="form-control" rows="5" id="edit-description" name="editDescription" required></textarea>
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="category" class="col-sm-2 col-form-label"><strong>Status</strong></label>
				    <div class="col-sm-10">
				      <select class="browser-default custom-select" id="edit-status" name="editStatus" required>
			              <option disabled selected>Change the status for this project</option>
			              <option value="stopped">Stopped</option>
			              <option value="in progress">In progress</option>
			              <option value="ended">Ended</option>
		              </select>
				    </div>
				  </div>
				  <!-- <div class="form-group row">
				    <label for="budget" class="col-sm-2 col-form-label"><strong>Budget</strong></label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="editBudget" id="edit-budget" placeholder="Budget" value="">
				    </div>
				  </div> -->
				 
				  <div class="form-buttons" style="float:right">
				  	<input id="edit-project-button" class="btn btn-primary" type="submit" value="Apply changes">
				  	<input class="btn btn-secondary reset-form" type="button" value="Cancel" data-dismiss="modal">
				  </div>
				</form>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Modal - delete project -->
		<div class="modal fade" id="deleteProjectModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">Please, confirm you choice</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <!-- <input type="text" class="form-control" name="skill" placeholder="Write here your skill"> -->
		        <p>Are you sure you to delete this project? This action is irreversible.</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button id="delete-project-button" type="submit" class="btn btn-danger">Delete</button>
		      </div>
		    </div>
		  </div>
		</div>
	</body>
</html>
</html>