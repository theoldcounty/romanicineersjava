<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../includes/header.jsp" />
<!--include header-->
	<div class="container">
		<!--edit.jsp-->
		<h2>Edit</h2>
		
		${message}
		
		<h2>Account::</h2>
		<form action="edit" method="post" action="">	
				<c:forEach var="person" items="${response}">
					
					<label>Username</label>
					<input type="text" value="${person.username}" name="username"/>
					<br/>
				
					<label>Email</label>
					<input type="text" value="${person.emailaddress}" name="emailaddress"/>
					<br/>
					
					<label>Confirm Email</label>
					<input type="text" value="${person.emailaddress}" name="confirmemailaddress"/>
					<br/>
							
					<label>Password</label>
					<input type="password" value="${person.password}" name="password"/>
					<br/>
						
					<label>Confirm Password</label>
					<input type="password" value="${person.password}" name="confirmpassword"/>
					<br/>	
				
					<label>Gender</label>
					<select name="gender">							
						<c:forEach var="sex" items="${genderList}">
							<c:choose>
								<c:when test="${person.gender == sex.key}">
									<option value="${sex.key}" selected="selected">${sex.value}</option>
								</c:when>					
								<c:when test="${genderCommonKey == sex.key && person.gender == null}">
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
									<c:when test="${person.birthyear == i}">
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
								<c:when test="${person.birthmonth == i}">
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
								<c:when test="${person.birthmonth == i}">
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
						<c:forEach var="ethnic" items="${ethnicityList}">
							<c:choose>
								<c:when test="${person.ethnicity == ethnic.key}">
									<option value="${ethnic.key}" selected="selected">${ethnic.value}</option>
								</c:when>							
								<c:when test="${ethnicityCommonKey == ethnic.key && person.ethnicity == null}">
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
					<select name="country">
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
				</c:forEach>   
			
			<input type="submit" value="submit" name="submitaccount"/>
		</form>
		
		
		
		<h2>Basics::</h2>
		<form action="edit" method="post" action="">	
				<c:forEach var="basics" items="${response}">
						<label>Seeking</label>
						<select name="seeking">
							<c:forEach var="sex" items="${genderList}">
								<c:choose>
									<c:when test="${basics.seeking == sex.key}">
										<option value="${sex.key}" selected="selected">${sex.value}</option>
									</c:when>					
									<c:when test="${seekingGenderCommonKey == sex.key && basics.seeking == null}">
										<option value="${sex.key}" selected="selected">${sex.value}</option>
									</c:when>
									<c:otherwise>
										<option value="${sex.key}">${sex.value}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>	
						</select>
						
						<br>
						<label>I am looking for</label>
						<select name="lookingFor">
							<option value="test">test</option>
						</select>
						<br>
						
						<label>Intent</label>
						<select name="intent">
							<option value="test">test</option>
						</select>
						<br>

						<label>Longest Relationship</label>
						<select name="longestRelationship">
							<option value="test">test</option>
						</select>
						<br>
							
										
				</c:forEach>
			<input type="submit" value="submit" name="submitbasics"/>
		</form>

<!-- other fields -->



<!-- 
<table width="90%">
<tbody><tr>
<td colspan="5"><br><span class="headline txtBlue size14">THE BASICS</span><br>
<div style="width:100%; border-bottom:1px solid #c1d5d9;"></div><br></td>
</tr>
<tr>
<td width="25%"><span class="headline txtGrey size12">Your Fish Personality</span></td>
<td width="25%"><select class="title" id="fishtype" style="width:180px" name="fishtype">
<option value="0" selected="">No Personality</option>
<option value="14">Angelfish</option>
<option value="16">Barnacle</option>
<option value="5">Barracuda </option>
<option value="9">Big Mouth Bass</option>
<option value="8">BlowFish</option>
<option value="4">Bottom Dweller</option>
<option value="6">Catfish</option>
<option value="27">Clam</option>
<option value="11">Clownfish</option>
<option value="17">Crab </option>
<option value="26">Damselfish</option>
<option value="7">Dolphin</option>
<option value="20">Eel</option>
<option value="24">Hammerhead</option>
<option value="3">Jellyfish </option>
<option value="19">Lobster</option>

<option value="22">Octopus</option>
<option value="10">Piranha</option>
<option value="21">Sea horse</option>
<option value="29">Sea urchin</option>
<option value="1">Shark</option>
<option value="18">Shrimp</option>
<option value="2">Starfish</option>
<option value="23">Sucker fish</option>
<option value="25">Sunfish</option>
<option value="13">Swordfish</option>
<option value="15">Tuna</option>
<option value="12">Turtle</option>
<option value="28">Whale</option>
		</select></td>
<td align="middle" width="20%"> </td>
<td width="25%"><span class="headline txtGrey size12">City</span></td>
<td width="25%"><input type="text" value="london" class="title" id="city" style="width:169px" name="city" size="17" maxlength="30"></td>					
</tr>
<tr>
<td width="25%"><span class="headline txtGrey size12">Seeking</span></td>
<td width="25%"><select class="title" id="Seekinga" style="width:180px" name="Seekinga">
<option value="1" selected="">Female</option>
<option value="0">Male</option>
</select></td>
            
<td align="middle" width="20%"> </td>
<td width="20%"><span class="headline txtGrey size12"><b>constituent state</b></span></td><td><select name="state_id" style="width:180px"><option value="1360" selected="">England</option>
<option value="1361">Northern Ireland</option>
<option value="1362">Scotland</option>
<option value="1363">Wales</option>
</select></td>


</tr>
<tr>
<td width="25%"><span class="headline txtGrey size12">I am looking for</span></td>
<td width="25%"><select class="title" id="searchtype" style="width:180px" name="searchtype">
<option value="1">Hang Out</option>		 
<option value="4">Long-term</option>
<option value="7">Dating</option>
<option value="8">Friends</option>
<option value="9" selected="">Intimate Encounter</option>
																						 
</select></td>
<td align="middle" width="20%"> </td>
<td width="25%"><span class="headline txtGrey size12">Postal Code</span></td>
<td width="25%"><input type="text" class="title" id="postalcode" value="" style="width:169px" name="postalcode" size="17" maxlength="30"></td>					
</tr>
<tr>    
<td width="20%"><span class="headline txtGrey size12">Intent</span></td>
<td colspan="2">						   
<select class="input-long" id="intent" name="intent">
								 
<option value="0">Select</option>

<option value="1">I'm looking for Casual dating/No Commitment.</option>
<option value="2"> I want to date but nothing serious.</option>
<option value="3">I want a relationship.</option>
<option value="4">I am putting in serious effort to find someone.</option>
<option value="5">I am serious and I want to find someone to marry.</option>
</select></td>
<td><span class="headline txtGrey size12">Longest Relationship: </span></td>
<td>
<select class="title" id="relationshipage_id" name="relationshipage_id" style="width:180px">																		
								 
<option value="12">Select</option>

<option value="0">Under 1 year</option>
<option value="1">Over 1 year</option>
<option value="2">Over 2 years</option>
<option value="3">Over 3 years</option>
<option value="4">Over 4 years</option>
<option value="5">Over 5 years</option>
<option value="6">Over 6 years</option>
<option value="7">Over 7 years</option>
<option value="8">Over 8 years</option>
<option value="9">Over 9 years</option> 
<option value="10">Over 10 years</option> 
</select></td>
</tr>
</tbody></table>







<table width="90%">
<tbody><tr>
<td><span class="headline txtBlue size14">HEADLINE</span>  
                
                
<input class="title" type="text" size="75" name="headline" maxlength="50" value="looking for a friend" onchange="javascript:while(''+this.value.charAt(this.value.length-1)==' ')this.value=this.value.substring(0,this.value.length-1);">
</td>
</tr>                                           
<tr>
<td><br>
<table width="100%">
<tbody><tr>
<td width="55%"><span class="headline txtBlue size14">DESCRIPTION</span>
<span class="grey-text">(Mandatory)</span>
<br>
<span class="grey-text">For your own safety, do not include your name, phone number or address.</span><br><br>											
<span class="text">People will read both your profile AND message when <a title="Powered by Text-Enhance" id="_GPLITA_2" style="text-decoration:underline" href="#" in_rurl="http://www.textsrv.com/click?v=R0I6MTk3NzQ6MTI3NzpkZWNpZGluZzphNDdmYjllNTI2ZjUwY2M5YzEwYzBmZmZhZmRkN2NhNTp6LTEwNjMtMTUyMjQ6d3d3LnBvZi5jb20%3D">deciding</a> if they should write back to you.  If your profile is really lame it won't matter how good your message is.</span><br>													
</td>
<td width="10%"></td>
<td width="45%"><span class="headline txtGreen size14"><br><br>If you want to be successful on POF, try this:</span><br>
1. Talk about your hobbies.  <br> 
2. Talk about your goals and aspirations<br> 
3. Talk about yourself and what makes you unique. <br> 
4. Describe your taste in music.  </td>
</tr>
</tbody></table><br>

<span class="grey-text">Formatting Options:</span> <span class="text"> [b]<strong>BOLD</strong>[/b]  [i] <i>Italics</i> [/i]  [u] <u>Underline</u> [/u]</span>
<br>

</td>
</tr>
<tr>
<td><textarea class="title" id="description" style="WIDTH: 910px;" name="description" rows="13" onchange="javascript:while(''+this.value.charAt(this.value.length-1)==' ')this.value=this.value.substring(0,this.value.length-1);" wrap="soft">I make websites and I find it really fun. I like movies and music and anything else in between. I am just here for a good time and to take good ideas. I have a friend and I am here just to take ideas to make a website like this.</textarea><br></td>
</tr>
<tr>
<td><br><span class="headline txtBlue size14">FIRST DATE </span> <span class="grey-text"> (optional)</span> <br>
<textarea class="title" style="WIDTH: 910px" name="firstdate" rows="7" wrap="soft">Buy dinner</textarea><br><br>
</td>
</tr>

<tr>
<td width="25%"><span class="headline txtBlue size14">INTERESTS</span><span class="grey-text"> - separate interests with commas</span><br>
<input class="title" type="text" name="interests" size="45" maxlength="980" style="WIDTH: 910px" value="websites"></td>
</tr>

<tr>						 
<td><br><center><input type="hidden" id="profile_id" name="profile_id" value="11788517"><input type="submit" class="button big-blue" style="*padding:5px;*font-size:18px;" value="Update Profile" name="CreateProfile"><br></center>
</td>
</tr>
</tbody></table>









<table width="90%"> 
<tbody><tr>
<td colspan="5"><br><span class="headline txtBlue size14">ABOUT YOU</span><br>
<div style="width:100%; border-bottom:1px solid #c1d5d9;"></div><br></td>
</tr>
<tr>
<td width="25%"><span class="headline txtGrey size12">Marital Status</span></td>
<td width="25%"><select class="title" id="maritalstatus" style="width:180px" name="maritalstatus">

<option value="1" selected="">Single</option>
<option value="2">Married</option>
<option value="3">Living Together</option>
<option value="4">Divorced</option>
<option value="5">Widowed</option>
<option value="6">Separated</option>
<option value="7">Not Single/Not Looking</option>
</select></td>

<td align="middle" width="20%"> </td>
<td width="25%"><span class="headline txtGrey size12">Do you smoke?</span></td>
<td width="25%"><select class="title" id="smoke" style="width:180px" name="smoke">

<option value="1" selected="">No</option>
<option value="2">Occasionally</option>
<option value="3">Often</option>
</select></td>
</tr>
<tr>
<td width="25%"><span class="headline txtGrey size12">Height</span></td>
<td width="25%"><select class="title" id="height" style="width:180px" name="height">
<option value="0">&lt; 5' (&lt; 152 cm)</option>
<option value="152">5'0" (152 cm)</option>
<option value="155">5'1" (155 cm)</option>
<option value="157">5'2" (157 cm)</option>
<option value="160">5'3" (160 cm)</option>
<option value="163">5'4" (163 cm)</option>
<option value="165">5'5" (165 cm)</option>
<option value="168">5'6" (168 cm)</option>
<option value="170" selected="">5'7" (170 cm)</option>
<option value="173">5'8" (173 cm)</option>
<option value="175">5'9" (175 cm)</option>
<option value="178">5'10" (178 cm)</option>
<option value="180">5'11" (180 cm)</option>
<option value="183">6'0" (183 cm)</option>
<option value="185">6'1" (185 cm)</option>
<option value="188">6'2"(188 cm)</option>
<option value="191">6'3" (191 cm)</option>
<option value="193">6'4" (193 cm)</option>
<option value="196">6'5" (196 cm)</option>
<option value="198">6'6" (198 cm)</option>
<option value="201">6'7" (201 cm)</option>
<option value="203">6'8" (203 cm)</option>
<option value="206">6'9" (206 cm)</option>
<option value="208">6'10" (208 cm)</option>
<option value="211">6'11" (211 cm)</option>
<option value="213">7' 0" (213 cm)</option>
<option value="999">&gt; 7' (&gt; 213 cm)</option>
</select></td>
<td align="middle" width="20%"> </td>
<td><span class="headline txtGrey size12">Would you date someone<br>who smokes?</span></td>
<td> <select class="title" style="width:180px" id="datesmokers" name="datesmokers"><option value="0">Select</option>
<option value="1">No</option>
<option value="2">Yes</option>
<option value="3">I only date smokers</option>
</select>
</td>
</tr>
<tr>
<td width="25%"><span class="headline txtGrey size12">Body Type</span></td>
<td width="25%"><select class="title" id="body" style="width:180px" name="body">
<option value="0">Prefer Not To Say</option>
<option value="1">Thin</option>
<option value="2" selected="">Athletic</option>
<option value="3">Average</option>
<option value="4">A Few Extra Pounds</option>
<option value="5">Big &amp; Tall/BBW</option>
</select></td>
<td align="middle" width="20%"> </td>
<td width="25%"><span class="headline txtGrey size12">Do you do drugs?</span></td>
<td width="25%"><select class="title" id="drugs" style="width:180px" name="drugs">
<option value="0">Select</option>
<option value="1" selected="">No</option>
<option value="2">Socially</option>
<option value="3">Often (&gt;3 times/week)</option>
</select></td>
</tr>
<tr>
<td width="25%"><span class="headline txtGrey size12">Hair Color</span></td>
<td width="25%"><select class="title" id="haircolor" style="width:180px" name="haircolor">
<option value="1">Black</option>
<option value="2">Blond(e)</option>
<option value="3" selected="">Brown</option>
<option value="4">Red</option>
<option value="5">Grey</option>
<option value="6">Bald</option>
<option value="7">Mixed Color</option>
</select></td>
<td align="middle" width="20%"> </td>
<td width="25%"><span class="headline txtGrey size12">Do you drink?</span></td>
<td width="25%"><select class="title" id="drink" style="width:180px" name="drink">
<option value="0">Prefer Not To Say</option>
<option value="1" selected="">No</option>
<option value="2">Socially</option>
<option value="3">Often (&gt;3 times/week)</option>
</select></td>
</tr>
<tr>
<td width="20%"><span class="headline txtGrey size12">Religion</span></td>
<td width="20%">
<select class="title" id="religion" name="religion" style="width:180px">
<option value="7" selected="">Non-religious</option>																					
<option value="1">New age</option>
<option value="2">Muslim</option>
<option value="3">Jewish</option>
<option value="4">Catholic</option>
<option value="5">Buddhist</option>
<option value="6">Hindu</option>

<option value="8">Anglican</option>
<option value="9">Sikh</option>
<option value="10">Methodist</option>
<option value="11">Christian - other</option>
<option value="12">Baptist</option>
<option value="13">Lutheran</option>
<option value="14">Presbyterian</option>
<option value="15">Other</option>
</select></td>

<td align="middle" width="25%"> </td>
<td width="20%"><span class="headline txtGrey size12">Do You Have a Car?</span></td>
<td width="20%"><select class="title" id="car" style="width:180px" name="car">
<option value="0">Prefer Not To Say</option>
<option value="1">Yes</option>
<option value="2" selected="">No</option>											 
</select></td>
</tr>
																		
																		
																		
																		
<tr>
<td width="20%"><span class="headline txtGrey size12">Ethnicity</span></td>
<td width="20%">
<select class="title" id="ethnicity" style="width:180px" name="ethnicity" size="1">
<option value="2">Black</option>
																					 
																						
<option value="4">Caucasian</option>

<option value="6">Hispanic</option>
<option value="7">Indian</option>
<option value="8">Middle Eastern</option>
<option value="9">Native American</option>
<option value="10">Asian</option>
<option value="11" selected="">Mixed Race</option>
<option value="12">Other Ethnicity</option>
</select>
</td>
<td align="middle" width="20%"> </td>
<td width="25%"><span class="headline txtGrey size12">Do you have children?</span></td>
<td width="25%"><select class="title" id="haschildren" style="width:180px" name="haschildren">
<option value="0">Prefer Not To Say</option>
<option value="1">Yes</option>
<option value="2" selected="">No</option>
<option value="3">All my kids are over 18</option>
</select></td>


</tr>												
		
<tr>
<td width="20%"><span class="headline txtGrey size12">Profession</span></td>
<td width="20%"><input class="title" type="text" id="profession" name="profession" style="width:169px" maxlength="50" value="web developer"></td>

<td align="middle" width="20%"> </td>

<td width="25%"><span class="headline txtGrey size12">Do you want children?</span></td>
<td width="25%"><select class="title" id="wantchildren" style="width:180px" name="wantchildren">
<option value="0" selected="">Prefer Not To Say</option>
<option value="1">Want children</option>
<option value="2">Does not want children</option>
<option value="3">Undecided/Open</option>
</select></td>
</tr>
<tr>
<td width="25%"><span class="headline txtGrey size12">Your Income Level?</span></td>
<td width="25%"><select class="title" id="income" style="width:180px" name="income">

<option value="1">Less Than 25,000</option>
<option value="2" selected="">25,001 to 35,000</option>
<option value="3">35,001 to 50,000</option>
<option value="4">50,001 to 75,000</option>
<option value="5">75,001 to 100,000</option>
<option value="6">100,001 to 150,000</option>
<option value="7">150,000+</option>								
</select></td>
<td align="middle" width="20%"> </td>
<td>
<span class="headline txtGrey size12">Would you date someone<br>who has kids?</span> </td>
<td> <select class="title" id="datekids" style="width:180px" name="datekids"> <option value="0">Select</option>
<option value="1">No</option>
<option value="2">Yes</option>
<option value="3">I only date single parents</option>
</select> </td>
</tr>								

<tr>
<td width="20%"><span class="headline txtGrey size12">Education</span></td>
<td>
<select name="college_id" style="width:180px" class="title">
<option value="13418">High school</option>
<option value="13422">Some college</option>
<option value="13425">Some University</option>
<option value="13421">Associates degree</option>
<option value="13423">Bachelors degree</option>
<option value="13424" selected="">Masters degree</option>
<option value="13420">PhD / Post Doctoral</option>
<option value="13419">Graduate degree</option>
                
</select></td>
<td align="middle" width="20%"> </td>
<td><span class="headline txtGrey size12">Do you have pets?</span> </td>
<td> <select id="pets" class="title" name="pets" style="width:180px">
<option value="6" selected="">Select</option>
<option value="0">No Pets</option>
<option value="1">Cat</option>
<option value="2">Dog</option>
<option value="3">Cat &amp; Dog</option>
<option value="4">Birds</option>
<option value="5">Other</option>
</select> </td>
</tr>

<tr>
<td><span class="headline txtGrey size12">Eye color</span></td>
<td><select id="eyes_id" class="title" name="eyes_id" style="width:180px">
<option value="0" selected="">Select</option>
<option value="1">Blue</option>
<option value="2">Hazel</option>
<option value="3">Grey</option>
<option value="6">Green</option>
<option value="4">Brown</option>
<option value="5">Other</option>
</select>
</td>
<td align="middle" width="20%"> </td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
</tbody></table>
-->



<!--other fields-->
		
		<!--edit.jsp-->
		
		<h2>View Users</h2>
		<ul>
			<li><a href="index">Index</a></li>
			<li><a href="members">List Users</a></li>
		</ul>  
	</div>
<!--include footer-->
<jsp:include page="../includes/footer.jsp" />