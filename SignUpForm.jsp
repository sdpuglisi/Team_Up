<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" integrity="sha256-9mbkOfVho3ZPXfM7W8sV2SndrGDuh7wuyLjtsWeTI1Q=" crossorigin="anonymous" />
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<link rel="stylesheet" href="css/global.css">
		<link rel="stylesheet" href="css/signup.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		
		<script src="js/global.js"></script>
		
		<title>TeamUp | Sign Up</title>
		
		<style type="text/css">
            body {
              	background: -webkit-linear-gradient(left, #3931af, #00c6ff);
			    font-size: 1rem;
            }
          </style>
	</head>
	
	<body>
	
		<!-- <div class="logo-container">
    		<img class="logo" src="images/teamup-logo.jpeg" alt="TeamUp logo">
    	</div> -->
	
		<div class="signup-form">
		    <form action="${pageContext.request.contextPath}/signup" method="post">
				<h2>Sign Up</h2>
				<p>Fill in this form to create an account</p>
				<hr>
		        <div class="form-group">
		            <input type="text" class="form-control" name="firstname" placeholder="First Name" maxlength="16" required pattern="[A-Za-z]{1,16}">
		        </div>
		        <div class="form-group">
		            <input type="text" class="form-control" name="lastname" placeholder="Last Name" maxlength="16" required pattern="[A-Za-z]{1,16}">
		        </div>
		        <div class="form-group">
		            <input type="text" class="form-control" name="profession" placeholder="Profession" required>
		        </div>
		        <div class="form-group">
		            <input type="date" min="1950-01-01" max="2020-06-30" class="form-control" name="dateofbirth" placeholder="Date of Birth (e.g. aaaa-mm-dd)" required>
		        </div>
		        <div class="form-group">
		        	<input type="email" class="form-control" name="username" placeholder="Email" required>
		        </div>
				<div class="form-group">
		            <input type="password" class="form-control" name="password" placeholder="Password" required>
		        </div>
				<!-- <div class="form-group">
		            <input type="password" class="form-control" name="confirm_password" placeholder="Confirm Password" required="required">
		        </div> -->        
		        <!-- <div class="form-group">
					<label class="checkbox-inline"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
				</div> -->
				<div class="form-group">
		            <button type="submit" class="btn btn-primary btn-lg">Sign Up</button>
		        </div>
		        <p class="hint-text">Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
		    </form>
			<!-- <div class="hint-text">Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></div> -->
		</div>
	</body>
	
</html>