<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="colorboxWrapper" class="regular">
	<h1>Visual Dating</h1>

	<div class="forgotpassword">						
		${message}				
		<form id="forgotPasswordForm" action="forgotpassword" method="post" action="">
			<label>Email</label>
			<input type="text" value="" name="emailaddress"/>
			<br/>
			
			<input type="hidden" value="submitted" name="submitted"/>
			<input type="submit" value="submit" name="submit"/>
		</form>
		<!--forgotpassword.jsp-->
	</div>
</div>