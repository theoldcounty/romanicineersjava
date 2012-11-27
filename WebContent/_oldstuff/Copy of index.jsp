<!--<jsp:include page="WEB-INF/jsp/includes/header.jsp" />-->
<!--include header-->
	<div class="container">
		<h2>The proto dating site</h2>
		<div>
		thumbnails list of members 
		</div>
		
		${message}
		
		<h2>View Users</h2>
		<ul>
			<li><a href="members">List Users</a></li>
		</ul>      
		 
		<h2>Search Users</h2>
		<ul>
			<li><a href="search?query=rob">Search through members</a></li>
		</ul>       
		  
		<h2>Inbox functions</h2>
		<ul>
			<li><a href="messages?mode=compose">Compose</a></li>
			<li><a href="messages?mode=sent">Read Sent messages</a></li>
			<li><a href="messages?mode=received">Read received messages</a></li>
		</ul>      
		    
		    
		<h2>Upload function</h2>
		<ul>
			<li><a href="gallery">View gallery</a></li>
			<li><a href="galleryupload">File Upload</a></li>
		</ul>            
		       
	</div>       
<!--include footer-->    
<!--<jsp:include page="WEB-INF/jsp/includes/footer.jsp" />-->