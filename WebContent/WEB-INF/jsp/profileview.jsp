<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />
<!--include header-->
	<div class="container">	
		<!--profileview.jsp-->
			<h2>Specific Member</h2>
			
			<table border="1">
				<c:forEach var="person" items="${people}">
					<tr>
						<td>${person.username}</td>
						<td>${person.emailaddress}</td>
						<td>${person.password}</td>
						<td>${person.gender}</td>
						<td>${person.birthdate}</td>
						<td>${person.ethnicity}</td>								
						<td>${person.country}</td>
						<td>${person.registeredon}</td>
						<td>${person.lastloggedon}</td>								
						<td>${person.isloggedon}</td>
					</tr>
				</c:forEach>   
			</table>

			<table border="1">
				<c:forEach var="person" items="${people}">
					<tr>
						<td>${person.seeking}</td>
					</tr>
				</c:forEach>   
			</table>			
			
			
			<!-- 
			 
		    User Name: zeezack<br/>
		    FirstName: Rob<br/>
		    LastName: Lone<br/>
		
			<br/>
			//Basic Info<br/>
			
			Birthday:	15 February 1983<br/>
			Gender:	Male<br/>
			InterestedIn:	Women<br/>
			RelationshipStatus:	Married<br/>
			Languages:	English<br/>
			PoliticalViews:	Liberal<br/>
			
			<br/>
			//Favorite quotations<br/>
			"What are we making here dickhead, a fucking cake?"<br/>
			    
			<br/>    
			//About you<br/>
			Web Technologies/Motion Graphics are my things. http://www.roblone.com/<br/>
			http://cargocollective.com/candy-rocket<br/>
			<br/>	
			Film fanatic, interested in making films and developing complex web applications. I have a Chinese wife and plan to move and work in Hong Kong in a few years time.    
			    
			 -->
			
						<h2>My Profile</h2>
						
						
						<table>
							<tr>
								<td>Relationship Status</td>
								<td>never married</td>
							</tr>
							<tr>
								<td>Nationality</td>
								<td>-</td>
							</tr>
							<tr>
								<td>Children</td>
								<td>none</td>
							</tr>
							<tr>
								<td>Personality</td>
								<td>-</td>
							</tr>
							<tr>
								<td>Entertainment</td>
								<td>nightclubs/dancing</td>
							</tr>
							<tr>
								<td>Interests</td>
								<td>travel/sightseeing</td>
							</tr>
							<tr>
								<td>Sports</td>
								<td>dancing, swimming, bowling</td>
							</tr>
							<tr>
								<td>Smoking</td>
								<td>occasionally</td>
							</tr>							
						</table>
						
						
						
						<h2>My Lifestyle</h2>
						
						<table>
							<tr>
								<td>Occupation</td>
								<td>-</td>
							</tr>							
						</table>					
			
			
		<!--profileview.jsp-->
	</div>
<!--include footer-->    
<jsp:include page="includes/footer.jsp" />