<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="colorboxWrapper" class="regular">
		<h1>Visual Dating</h1>

		<div class="login">
						
				${message}
				
				<form id="loginForm" action="login" method="post" action="">
					<label>Email</label>
					<input type="text" value="" name="emailaddress"/>
					<br/>
					
					<label>Password</label>
					<input type="password" value="" name="password"/>
					<br/>
					
					<input type="hidden" value="submitted" name="submitted"/>
					<input type="submit" value="submit" name="submit"/>
				</form>
				<!--login.jsp-->
				
				<a class="shazam" href="forgotpassword">Forgot password</a>  
		
		</div>
</div>