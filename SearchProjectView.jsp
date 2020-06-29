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
		<link rel="stylesheet" href="css/search-project-view.css">
		<link rel="stylesheet" href="css/my-account.css">
		
		<script src="js/global.js"></script>
	
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta charset="UTF-8">
		<title>TeamUp | ${searchTerm}</title>
	</head>
	<body>
		<input id="userAccount" type="hidden" value="${username}" />
      	
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
      				<h1><strong>${fn:toUpperCase(fn:substring(searchTerm, 0, 1))}${fn:toLowerCase(fn:substring(searchTerm, 1,fn:length(searchTerm)))}</strong></h1>
      		 
		      		 <c:choose>
			        	<c:when test="${not empty projects}">
				        	<c:forEach items="${projects}" var="project">
					        	<div class="ui card fluid search-project-card" data-leader="${project.leader}">
						          <div class="content">
						            <div class="header">
						            	<a href="${pageContext.request.contextPath}/projectView?projectName=${project.title}&leader=${project.leader}">${project.title}</a>
					          		</div>
						            <div class="meta">
						              <span class="right floated time">Posted on ${fn:substring(project.creationDate, 0, 16)}</span>
						              <span class="category">${project.category}</span>
						            </div>
						            <div class="description">
						              <p>
						                ${project.description}
						              </p>
						            </div>
						          </div>
						          <div class="extra content">
						          	<c:if test="${project.leader ne username}">
						          		<div class="left floated author">
							          		<div class="rating">
								          		<span id="1" class="fa fa-star"></span>
												<span id="2" class="fa fa-star"></span>
												<span id="3" class="fa fa-star"></span>
												<span id="4" class="fa fa-star"></span>
												<span id="5" class="fa fa-star"></span>
											</div>
							            	<a href="${pageContext.request.contextPath}/sendCollaborationRequest?projectTitle=${project.title}&leader=${project.leader}&sender=${username}" style="display:none"><i class="fa fa-user-plus"></i> Send a request</a>
						            	</div>
						          	</c:if>
						            <div class="right floated author">
						            	${project.leader}
						            </div>
						          </div>
						        </div>
				      		</c:forEach>
			        	</c:when>
			        	<c:otherwise>
			        		<h4 class="no-results-found">No projects found with this category. Please, try with another one</h4>
			        	</c:otherwise>
			        </c:choose>
      			</div>
      		
                <div class="col-md-2">
                	<div class="dropdown">
					  <button class="btn btn-secondary dropdown-toggle" style="border-radius:20px; background-color:white; color:grey" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    Sort By
					  </button>
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					  	<h6 class="dropdown-header">Sort results by:</h6>
					    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=${searchTerm}&sortBy=2">Most recents</a>
					    <a class="dropdown-item" href="${pageContext.request.contextPath}/searchProjectByCategory?searchTerm=${searchTerm}&sortBy=3">Most Rated</a>
					  </div>
					</div>
                </div>
            </div>
      	
      		 
      	</div>
	</body>
</html>