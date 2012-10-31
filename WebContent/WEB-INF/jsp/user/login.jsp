<jsp:include page="../includes/header.jsp" />
<!--include header-->
	<div class="container">
		<!--login.jsp-->
		<h2>Login</h2>
		
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
<!--include footer-->
<jsp:include page="../includes/footer.jsp" />
