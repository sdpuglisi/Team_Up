<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" integrity="sha256-9mbkOfVho3ZPXfM7W8sV2SndrGDuh7wuyLjtsWeTI1Q=" crossorigin="anonymous" />
        <link rel="stylesheet" href="css/global.css" />
        <link rel="stylesheet" href="css/login.css" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <title>TeamUp | Login</title>

        <style type="text/css">
            body {
              overflow: hidden;
              background: -webkit-linear-gradient(left, #3931af, #00c6ff);
		    font-size: 1rem;
            }
            body > .grid {
              height: 100%;
            }
            .image {
              margin-top: -100px;
            }
            .column {
              max-width: 450px;
            }
          </style>
          
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
          
          <script src="js/global.js"></script>
    </head>
    
    <body>
    	
    	<!-- <div class="logo-container">
    		<img class="logo" src="images/teamup-logo.jpeg" alt="TeamUp logo">
    	</div> -->
    
        <div class="ui middle aligned center aligned grid">
          <div class="column">
            

            <form action="${pageContext.request.contextPath}/login" method="POST" class="ui large form">
              <div class="ui stacked segment">
              	<h2 class="ui teal image header" style="color:#333 !important">Sign In</h2>
					<p>Fill in this form to log in</p>
				<hr>
              	<!-- <h2 class="ui teal image header" style="color:black !important">
	                Sign In
	            </h2> -->
                <div class="field">
                  <div class="ui left icon input">
                    <i class="user icon"></i>
                    <input id="login-username" type="text" style="background:#f2f2f2" name="username" value="${user.username}" placeholder="E-mail address" required>
                  </div>
                </div>
                <div class="field">
                  <div class="ui left icon input">
                    <i class="lock icon"></i>
                    <input id="login-password" type="password" style="background:#f2f2f2" name="password" value="${user.password}" placeholder="Password" required>
                  </div>
                </div>
                <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>
              </div>

			  <c:if test="${not empty errorString}">
			  	<div class="ui error message" style="display:block !important">${errorString}</div>
			  </c:if>	
            </form>

            <div class="ui message">
              New on this platform? <a href="SignUpForm.jsp">Sign Up</a>
            </div>
          </div>
        </div> 
    </body>
</html>