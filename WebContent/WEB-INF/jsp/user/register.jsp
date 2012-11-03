<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="colorboxWrapper" class="regular">
		<h1>Visual Dating</h1>

		<div class="registration">

			<ul>
				<li>Step 1</li>
				<li>Step 2</li>
				<li>Step 3</li>
			</ul>

			<fieldset class="step1">
				<h2>Create your account in 5 minutes!</h2>

				<label>Name In full* [your name can be selected as a screen name]</label>
				<input type="text" name="realname">

				<label>Username* [Username can be selected as a screen name]</label>
				<input type="text" name="username">

				<label>Email*</label>
				<input type="text" name="email">

				<label>Password*</label>
				<input type="text" name="password">

				<label>Confirmed Password*</label>
				<input type="text" name="confirmedpassword">
								
				<br>
				<input type="radio" name="screenname" value="realname">Please, use my name in full as a screen name<br>
				<input type="radio" name="screenname" value="username">Please, use my username as a screen name			
			</fieldset>
			
			
			<fieldset class="step2">
				<label>Date of Birth*</label>
				<input type="text" name="d" placeholder="dd">
				<input type="text" name="m" placeholder="mm">
				<input type="text" name="y" placeholder="yyyy">			
			
				<label>Gender*</label>
				<input type="radio" name="gender" value="male">Male<br>
				<input type="radio" name="gender" value="female">Female

				<label>Looking For*</label>
				<select>
					<option value="A Woman">A Woman</option>
					<option value="A Man">A Man</option>					
				</select>

				<label>Kind of Relationship*</label>
				<select>
					<option value="Serious">Serious</option>
					<option value="A Fling">A Fling</option>					
				</select>

				<label>Language*</label>
				<select>
					<option value="English">English</option>
					<option value="Spanish">Spanish</option>
					<option value="French">French</option>					
				</select>
			</fieldset>
			
			<fieldset class="step3">
				
				<p>To have the best experiences, we recommend you to fill your profile. It's going to be more attractive and bring your more new friends. Once your account is activated you can edit those information whenever you want.</p>
				
				<label>Please indicate what mostly drives your life</label>
				<select>
					<option value="Driven 01">Driven 01</option>				
				</select>
				<select>
					<option value="Driven 02">Driven 02</option>				
				</select>
				<select>
					<option value="Driven 03">Driven 03</option>				
				</select>	
				
				<label>Please indicate what describes you more</label>
				<div id="controls" class="sliderToggles" data-role="slider-controls-nav">
					<span>88</span>
					<span>77</span>
					<span>55</span>
					<span>33</span>
					<span>40</span>
					<span>45</span>
					<span>70</span>
				</div>
				
				<label>Please indicate 5 of your interest and their importance from 0 to 100</label>
				<select>
					<option value="Interest 01">Interest 01</option>				
				</select>
				<select>
					<option value="Interest 02">Interest 02</option>				
				</select>		
				<select>
					<option value="Interest 03">Interest 03</option>				
				</select>
				<select>
					<option value="Interest 04">Interest 04</option>				
				</select>				
				<select>
					<option value="Interest 05">Interest 05</option>				
				</select>			
											
			</fieldset>

		</div>
</div>


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

