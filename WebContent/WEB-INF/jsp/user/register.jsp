<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp" />
<!--include header-->
	<div class="container">
		<!--register.jsp-->
		<h2>Register</h2>
		
		${message}
		
		<form action="register" method="post" action="">	

			<label>Username</label>
			<input type="text" value="${username}" name="username"/>
			<br/>
		
			<label>Email</label>
			<input type="text" value="${emailaddress}" name="emailaddress"/>
			<br/>
			
			<label>Confirm Email</label>
			<input type="text" value="${confirmemailaddress}" name="confirmemailaddress"/>
			<br/>
					
			<label>Password</label>
			<input type="password" value="${password}" name="password"/>
			<br/>
				
			<label>Confirm Password</label>
			<input type="password" value="${confirmpassword}" name="confirmpassword"/>
			<br/>	
		
			<label>Gender</label>
			<select name="gender">
				<!-- common gender -->
				<c:forEach var="sex" items="${genderList}">
					<c:choose>
						<c:when test="${gender == sex.key}">
							<option value="${sex.key}" selected="selected">${sex.value}</option>
						</c:when>					
						<c:when test="${genderCommonKey == sex.key && gender == null}">
							<option value="${sex.key}" selected="selected">${sex.value}</option>
						</c:when>
						<c:otherwise>
							<option value="${sex.key}">${sex.value}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>					
			</select>					
			<br/>			
				
			<label>Birth Date</label>
			<select name="birthyear">
				<c:forEach var="i" begin="1900" end="2012" step="1" varStatus ="status">
						<c:choose>
							<c:when test="${birthyear == i}">
								<option value="${i}" selected="selected">${i}</option>
							</c:when>
							<c:otherwise>
								<option value="${i}">${i}</option>
							</c:otherwise>
						</c:choose>					
				</c:forEach>						
			</select>
			<select name="birthmonth">
				<c:forEach var="i" begin="1" end="12" step="1" varStatus ="status">
						<c:choose>
							<c:when test="${birthmonth == i}">
								<option value="<c:if test="${i<10}">0</c:if>${i}" selected="selected"><c:if test="${i<10}">0</c:if>${i}</option>
							</c:when>
							<c:otherwise>
								<option value="<c:if test="${i<10}">0</c:if>${i}"><c:if test="${i<10}">0</c:if>${i}</option>
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</select>
			<select name="birthday">
				<c:forEach var="i" begin="1" end="31" step="1" varStatus ="status">
						<c:choose>
							<c:when test="${birthday == i}">
								<option value="<c:if test="${i<10}">0</c:if>${i}" selected="selected"><c:if test="${i<10}">0</c:if>${i}</option>
							</c:when>
							<c:otherwise>
								<option value="<c:if test="${i<10}">0</c:if>${i}"><c:if test="${i<10}">0</c:if>${i}</option>
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</select>
			<br/>		
		
			<label>Ethnicity</label>
			<!--<input type="text" value="${person.ethnicity}" name="ethnicity"/>-->
			<select name="ethnicity">
				<!-- common ethnicity -->
					<c:forEach var="ethnic" items="${ethnicityList}">
						<c:choose>
							<c:when test="${ethnicity == ethnic.key}">
								<option value="${ethnic.key}" selected="selected">${ethnic.value}</option>
							</c:when>							
							<c:when test="${ethnicityCommonKey == ethnic.key && ethnicity == null}">
								<option value="${ethnic.key}" selected="selected">${ethnic.value}</option>
							</c:when>
							<c:otherwise>
								<option value="${ethnic.key}">${ethnic.value}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>				
			</select>					
			<br/>
			
			<label>Country</label>
			${person.country}
			<select name="country">
				<!-- common country -->
				<c:forEach var="countryList" items="${countryList}">
					<c:choose>
						<c:when test="${person.country == countryList.key}">
							<option value="${countryList.key}" selected="selected">${countryList.value}</option>
						</c:when>					
						<c:when test="${countryCommonKey == countryList.key && person.country == null}">
							<option value="${countryList.key}" selected="selected">${countryList.value}</option>
						</c:when>
						<c:otherwise>
							<option value="${countryList.key}">${countryList.value}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>	
			<br/>

			<input type="submit" value="submit" name="submit"/>
		</form>
		<!--register.jsp-->
		
		<h2>View Users</h2>
		<ul>
			<li><a href="index">Index</a></li>
			<li><a href="members">List Users</a></li>
		</ul>
	</div>
<!--include footer-->
<jsp:include page="../includes/footer.jsp" />