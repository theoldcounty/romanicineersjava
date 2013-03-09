<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="colorboxWrapper" class="regular">
		<div class="registration">
			<ul>
				<li><a href="#step1">Step 1<br>identification</a></li>
				<li><a href="#step2">Step 2<br>your qualities</a></li>
				<li><a href="#step3">Step 3<br>your personality</a></li>
			</ul>

		<form id="registerForm" action="register" enctype="multipart/form-data" method="post" action="">
			<fieldset id="step1">
				<fieldset class="fifty-fifty">
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
				
				<fieldset class="fifty-fifty last">
					<fieldset class="dob">
						<label>Date of Birth*</label>
						<input type="text" name="birthday" placeholder="dd" class="day">
						<input type="text" name="birthmonth" placeholder="mm" class="month">
						<input type="text" name="birthyear" placeholder="yyyy" class="year">			
					</fieldset>
					
					<fieldset class="gender">
						<label>Gender*</label>
						<input type="radio" name="gender" value="Male" checked="checked">Male <input type="radio" name="gender" value="Female">Female
					</fieldset>
				</fieldset>			
			
			</fieldset>
			
			<fieldset id="step2">
				

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

				<input type="hidden" name="latitude" value="51.523911">
				<input type="hidden" name="longitude" value="-0.12763">								
				
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
					<select name="goal1">
						<option value="Creativity">Creativity</option>
						<option value="Drama">Drama</option>
						<option value="Entertainment">Entertainment</option>
						<option value="Family">Family</option>
						<option value="Power">Power</option>
						<option value="Popularity">Popularity</option>		
					</select>
					<select name="goal2">
						<option value="Health">Health</option>
						<option value="Hobbies">Hobbies</option>
						<option value="Fun">Fun</option>
						<option value="Friends">Friends</option>
						<option value="Education">Education</option>
						<option value="Marriage">Marriage</option>			
					</select>
					<select name="goal3">
						<option value="Adventure">Adventure</option>
						<option value="Career">Career</option>
						<option value="Community">Community</option>
						<option value="Music">Music</option>
						<option value="Sports">Sports</option>
						<option value="Travel">Travel</option>
						<option value="Possessions">Possessions</option>		
					</select>
				</fieldset>
				
				<label>Please indicate what describes you more</label>
				<div id="controls" class="sliderToggles" data-role="slider-controls-nav">
					<span data-negative="Reserved" data-positive="Outgoing" data-field="personality">88</span>
					<span data-negative="Direct" data-positive="Flexible"  data-field="personality">77</span>
					<span data-negative="Senstitive" data-positive="Steady"  data-field="personality">55</span>
					<span data-negative="Consitent" data-positive="Curious"  data-field="personality">33</span>
					<span data-negative="Couple" data-positive="Family"  data-field="personality">40</span>
					<span data-negative="Tender" data-positive="Passionate"  data-field="personality">45</span>
					<span data-negative="Focus" data-positive="Funny"  data-field="personality">70</span>
				</div>

				
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
			    	
			    	console.log("obj " + obj);
			    	
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
					"fgColor": $(this).data('color')
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
