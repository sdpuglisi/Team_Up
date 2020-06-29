<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" integrity="sha256-9mbkOfVho3ZPXfM7W8sV2SndrGDuh7wuyLjtsWeTI1Q=" crossorigin="anonymous" />
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/css/bootstrap.css" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/js/bootstrap.bundle.min.js"></script>
	
		<link rel="stylesheet" href="css/global.css">
		<link rel="stylesheet" href="css/project-details.css">
		<link rel="stylesheet" href="css/my-account.css">
		
		<script src="js/global.js"></script>
	
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta charset="UTF-8">
		<title>TeamUp | ${project.title}</title>
	</head>
	<body>
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
      		<div class="row">
      			<div class="col-md-10">
		      		<c:set var="members" value="${fn:split(project.members, ',')}" />
		      		
		      		<h1 style="margin-bottom:0 !important">${project.title}</h1>
		      		<div class="project-category">
		      			<a href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=${project.category}&sortBy=1">${project.category}</a>
		      		</div>
		      		
		      		<h3 style="margin-top:20px">Description</h3>
		      		<p>${project.description}</p>
		      		
		      		<h3 style="margin-top:20px">Team</h3>
		      		<div class="ui four column grid">
		      			<c:set var="isPossibleToJoinProject" value="true" />
		      			<c:choose>
		      				<c:when test="${not empty project.members}">
		      					<c:forTokens items="${project.members}" delims="," var="member">
				      				<div class="column" style="margin-right:30px">
									    <div class="ui fluid card" style="width:100% !important">
									      <div class="image">
									        <img src="images/profile_logo.png">
									      </div>
									      <c:choose>
									      	<c:when test="${member ne username}">
									      		<div class="content">
										        	<a href="mailto:${member}" class="header" style="font-size:14px">${member}</a>
										      	</div>
									      	</c:when>
									      	<c:otherwise>
									      		<c:set var="isPossibleToJoinProject" value="false" />
									      		<div class="content">
										        	<a href="#" class="header" style="font-size:14px">${member}</a>
										      	</div>
									      	</c:otherwise>
									      </c:choose>
									    </div>
								  	</div>
				      			</c:forTokens>
		      				</c:when>
		      				<c:otherwise>
		      					<p style="font-style:italic; margin-top:10px">There are no teammates yet for this project.</p>
		      				</c:otherwise>
		      			</c:choose>
					</div>
					
					<h3 style="margin-top:20px">Status</h3>
					<p>${fn:toUpperCase(fn:substring(project.status, 0, 1))}${fn:toLowerCase(fn:substring(project.status, 1,fn:length(project.status)))}</p>
					
					<h3 style="margin-top:20px">Budget</h3>
					<p>For this project, the leader has requested <span style="font-size:16px; font-weight:500">${project.budgetRequested}</span></p>
   		 		</div>
   		 	
	   		 	<div class="col-md-2">
	               	<div class="dropdown">
					  <button class="btn btn-secondary dropdown-toggle" style="border-radius:20px; background-color:white; color:grey" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    More Actions
					  </button>
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					  	<c:choose>
					  		<c:when test="${project.leader ne username}">
					  			<c:choose>
					  				<c:when test="${empty isPossibleToJoinProject or isPossibleToJoinProject ne 'true'}">
					  					<a class="dropdown-item" href="mailto:${project.leader}">Email to the leader</a>
						  				<a class="dropdown-item" href="#" style="color:#D0D0D0; cursor:auto">Join project</a>
					  				</c:when>
					  				<c:otherwise>
					  					<a class="dropdown-item" href="mailto:${project.leader}">Email to the leader</a>
						  				<a class="dropdown-item" href="${pageContext.request.contextPath}/sendCollaborationRequest?projectTitle=${project.title}&leader=${project.leader}&sender=${username}">Join project</a>
					  				</c:otherwise>
					  			</c:choose>
						  	</c:when>
						  	<%--<c:when test="${empty isPossibleToJoinProject or isPossibleToJoinProject ne 'true'}">
						  		<a class="dropdown-item" href="mailto:${project.leader}">Email to the leader</a>
						  		<a class="dropdown-item" href="#" style="color:#D0D0D0; cursor:auto">Join project</a>
						  	</c:when> --%>
						  	<c:otherwise>
						  		<a class="dropdown-item" href="#" style="color:#D0D0D0; cursor:auto">Email to the leader</a>
						  		<a class="dropdown-item" href="#" style="color:#D0D0D0; cursor:auto">Join project</a>
						  	</c:otherwise>
					  	</c:choose>
					  </div>
					</div>
	            </div>
            </div>
      	</div>
	</body>
</html>