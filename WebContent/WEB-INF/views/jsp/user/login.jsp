<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="colorboxWrapper" class="regular">
		<h1>Visual Dating</h1>

		<div class="login">
						
				${message}
				
				<form action="login" method="post" action="">
					<label>Email</label>
					<input type="text" value="" name="emailaddress"/>
					<br/>
					
					<label>Password</label>
					<input type="password" value="" name="password"/>
					<br/>
					
					<input type="submit" value="submit" name="submit"/>
				</form>
				<!--login.jsp-->
				
				<h2>View Users</h2>
				<ul>
					<li><a href="index">Index</a></li>
					<li><a href="members">List Users</a></li>
				</ul>  
		
		</div>
</div>