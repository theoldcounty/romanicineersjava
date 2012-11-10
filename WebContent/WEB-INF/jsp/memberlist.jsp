<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />
<!--include header-->
	<div class="container">
		<!--memberlist.jsp-->
			<h2>list of members</h2>
		
			<table border="1">
				<c:forEach var="person" items="${response}">
					<tr>
						<td><a href="user?id=${person._id}">${person.username}</a></td>
						<td><a href="delete?id=${person._id}">x</a></td>
						<td>${person.emailaddress}</td>
						<td>${person.password}</td>
						<td>${person.gender}</td>
						<td>${person.birthdate}</td>
						<td>${person.ethnicity}</td>								
						<td>${person.country}</td>
						<td>${person.registeredon}</td>
						<td>${person.lastloggedon}</td>								
						<td>${person.isloggedon}</td>
						<td>-</td>
						<td>${person.seeking}</td>
					</tr>
				</c:forEach>   
			</table>
		<!--memberlist.jsp-->
	</div>	
<!--include footer-->    
<jsp:include page="includes/footer.jsp" />