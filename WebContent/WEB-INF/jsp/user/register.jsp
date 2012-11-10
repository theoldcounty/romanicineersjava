<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="colorboxWrapper" class="regular">
		<h1>Visual Dating</h1>

		<div class="registration">

			<ul>
				<li><a href="#step1">Step 1</a></li>
				<li><a href="#step2">Step 2</a></li>
				<li><a href="#step3">Step 3</a></li>
			</ul>

		<form id="registerForm" action="register" method="post" action="">
			<fieldset id="step1">
				<h2>Create your account in 5 minutes!</h2>

				<label>Name In full* [your name can be selected as a screen name]</label>
				<input type="text" name="realname" value="${realname}">

				<label>Username* [Username can be selected as a screen name]</label>
				<input type="text" name="username" value="${username}">

				<label>Email*</label>
				<input type="text" name="emailaddress" value="${emailaddress}">
				
				<label>Confirmed Email*</label>
				<input type="text" name="confirmemailaddress" value="${confirmemailaddress}">				

			
				<label>Password*</label>
				<input type="password" name="password" value="${password}">

				<label>Confirmed Password*</label>
				<input type="password" name="confirmpassword" value="${confirmpassword}">
								
				<br>
				<input type="radio" name="whichscreenname" value="realname">Please, use my name in full as a screen name<br>
				<input type="radio" name="whichscreenname" value="username">Please, use my username as a screen name			
			</fieldset>
			
			
			<fieldset id="step2">
				<fieldset class="wrapper">
					<fieldset class="dob">
						<label>Date of Birth*</label>
						<input type="text" name="birthday" placeholder="dd" class="day">
						<input type="text" name="birthmonth" placeholder="mm" class="month">
						<input type="text" name="birthyear" placeholder="yyyy" class="year">			
					</fieldset>
					
					<fieldset class="gender">
						<label>Gender*</label>
						<input type="radio" name="gender" value="Male" selected="selected">Male<br>
						<input type="radio" name="gender" value="Female">Female
					</fieldset>

					<fieldset class="bio">
						<label>A little bit about you*</label>
						<textarea id="about" name="about" col="2" rows="5"></textarea>
					</fieldset>
				</fieldset>


				<label>Ethnicity</label>
				${person.ethnicity}
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
				
				
				<label>Looking For*</label>
				<select name="lookingfor">
					<option value="A Woman">A Woman</option>
					<option value="A Man">A Man</option>					
				</select>

				<label>Kind of Relationship*</label>
				<select name="kindofrelationship">
					<option value="Serious">Serious</option>
					<option value="A Fling">A Fling</option>					
				</select>

				<label>Body Type*</label>
				<select name="bodytype">
					<option value="Athletic">Athletic</option>
					<option value="Average">Average</option>					
				</select>
				
				<label>Hair Colour*</label>
				<select name="haircolor">
					<option value="Blonde">Blonde</option>
					<option value="Brown">Brown</option>
					<option value="Brunette">Brunette</option>
					<option value="Black">Black</option>
					<option value="Brunette">Red</option>					
				</select>

				<label>Eye Colour*</label>
				<select name="eyecolor">
					<option value="Blue">Blue</option>
					<option value="Brown">Brown</option>
					<option value="Green">Green</option>
					<option value="Hazel">Hazel</option>					
				</select>

				<label>Children*</label>
				<select name="children">
					<option value="None">None</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>					
				</select>

				<label>Education*</label>
				<select name="education">
					<option value="None">None</option>
					<option value="School">School</option>
					<option value="Degree">Degree</option>
					<option value="Masters">Masters</option>					
				</select>


				<label>Occupation*</label>
				<input type="text" name="occupation">

				<label>Language*</label>
				<select name="languages" multiple="multiple">
					<option value="English">English</option>
					<option value="Spanish">Spanish</option>
					<option value="French">French</option>
					<option value="Japanese">Japanese</option>
					<option value="Chinese">Chinese</option>					
				</select>
			</fieldset>
			
			<fieldset id="step3">
				
				<p>To have the best experiences, we recommend you to fill your profile. It's going to be more attractive and bring your more new friends. Once your account is activated you can edit those information whenever you want.</p>
				
				<fieldset class="goaldriven">
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
				</fieldset>
				
				<label>Please indicate what describes you more</label>
				<div id="controls" class="sliderToggles" data-role="slider-controls-nav">
					<span data-field="personality">88</span>
					<span data-field="personality">77</span>
					<span data-field="personality">55</span>
					<span data-field="personality">33</span>
					<span data-field="personality">40</span>
					<span data-field="personality">45</span>
					<span data-field="personality">70</span>
				</div>
				
				<label>Please indicate 5 of your interest and their importance from 0 to 100</label>
				
				<fieldset class="doughnutWrap">
					<select name="interests">
						<option value="Interest 01">Interest 01</option>				
					</select>
					<fieldset data-fieldname="interestknobs" id="knob1" class="knob" data-role="doughnut-knob" data-value="5"></fieldset>
				</fieldset>
				
				<fieldset class="doughnutWrap">
					<select name="interests">
						<option value="Interest 02">Interest 02</option>				
					</select>
					<fieldset data-fieldname="interestknobs" id="knob2" class="knob" data-role="doughnut-knob" data-value="15"></fieldset>
				</fieldset>
						
				<fieldset class="doughnutWrap">	
					<select name="interests">
						<option value="Interest 03">Interest 03</option>				
					</select>
					<fieldset data-fieldname="interestknobs" id="knob3" class="knob" data-role="doughnut-knob" data-value="45"></fieldset>
				</fieldset>
				
				<fieldset class="doughnutWrap">
					<select name="interests">
						<option value="Interest 04">Interest 04</option>				
					</select>
					<fieldset data-fieldname="interestknobs" id="knob4" class="knob" data-role="doughnut-knob" data-value="85"></fieldset>
				</fieldset>		
							
				<fieldset class="doughnutWrap">		
					<select name="interests">
						<option value="Interest 05">Interest 05</option>				
					</select>
					<fieldset data-fieldname="interestknobs" id="knob5" class="knob" data-role="doughnut-knob" data-value="35"></fieldset>		
				</fieldset>
	
				<span class="error"></span>
				<input type="hidden" value="submitted" name="submitted"/>
				<input type="submit" value="submit" name="submit"/>											
			</fieldset>
		</form>
		
		</div>
</div>


		<!--register.jsp-->
		${message}
		<!--register.jsp-->
		
    <script>
    
    $(function() { 
  
  
    
		$('#registerForm').submit(function(e) {
			e.preventDefault();
			console.log("clicked on reg - lets do an ajax post at some point");
			
			var postUrl = window.location;
			var formResults = $(this).serializeArray();
			console.log("formResults", formResults);
					
			$.post("register", formResults,
				function(data) {
			    	console.log("json response. " + data);
			    	var obj = jQuery.parseJSON(data);
			    	
			    	if(obj[0].response == "OK"){
			    		console.log("user registered succeffully");
			    		//close color box
			    		
			    		//refresh site
			    		console.log("current page",window.location.href);
			    		
						window.setInterval(function(){
							console.log("run a reload timer.");
							//location.reload();							
						},500);			    		
			    		//location.reload();
			    		//user has been registered succesfully
			    	}else{
			    		//there is an error with the registeration.
			    		console.log("backend error with reg - likely the username or email already exists");
			    		$('.error').html(obj[0].error);
			    	}
				}
			);			
						
		});    
	 
	 	console.log("setting slider backbone.");
		console.log("slider-controls-nav", $('[data-role="slider-controls-nav"]'));
	
	
		$('[data-role="slider-controls-nav"]').each(function() {
			new sliderControls({el: $(this)});
		});
	
	 
	    $( ".registration" ).tabs();
    
	 	$('[data-role="doughnut-knob"]').each(function() {
			console.log("take on a new backbone view.");
		  	var optionObj = {
				"data":
				{
					"min": 0,
					"max": 100,
					"width": 150,
					"height": 150,					
					"displayPrevious": true,
					"fgColor": "#E2B227"
				},
				"fieldName": $(this).data('fieldname'),
				"value": $(this).data('value'),
				"type": "standard"
			};
			
			console.log("optionObj", optionObj);
			
			$($(this)).doughnutKnob('init', optionObj);
		}); 		  
  
        
    });
    </script>
