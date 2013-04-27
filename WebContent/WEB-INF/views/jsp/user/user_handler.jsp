<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="shazam-wrapper" class="regular">
		
		<form id="editForm" class="<%=request.getParameter("section")%>" action="edit_user" enctype="multipart/form-data" method="post" action="">
			
			<fieldset id="account">
						
				<fieldset class="fifty-fifty">
					
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
			
			
			<fieldset id="about">
				<label>About</label>
				<textarea name="about" rows="4" cols="50"></textarea>
			</fieldset>
			
			<fieldset id="physical">			
				<label>Ethnicity</label>
				<select name="ethnicity">
					<option value="1">Asian</option>
					<option value="2">Black</option>
				</select>

				<label>Country</label>					
				<select name="country">
					<option value="HND">Honduras</option>
				</select>

				<input type="hidden" name="latitude" value="55.378051">
				<input type="hidden" name="longitude" value="-3.43597299999999">								
				
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
				
				<label>Body Type</label>
				<select name="bodytype">
					<option value="Athletic">Athletic</option>			
				</select>	
				
				<label>Hair Colour</label>
				<select name="haircolor">
					<option value="Blonde">Blonde</option>			
				</select>
				
				<label>Eye Colour</label>
				<select name="eyecolor">
					<option value="Blue">Blue</option>			
				</select>
				
				<label>Children</label>
				<select name="children">
					<option value="None">None</option>
					<option value="1">1</option>
					<option value="2">2</option>	
				</select>
				
				<label>Occupation</label>
				<select name="occupation">
					<option value="Unemployed">Unemployed</option>
					<option value="Web Developer">Web Developer</option>
					<option value="Doctor">Doctor</option>	
				</select>

				<label>Education</label>
				<select name="education">
					<option value="None">None</option>
					<option value="Degree">Degree</option>
					<option value="Masters">Masters</option>	
				</select>

				<label>Gender</label>
				<select name="gender">
					<option value="Male">Male</option>
					<option value="Female">Female</option>	
				</select>
								
				<label>Seeking</label>
				<select name="seeking">
					<option value="Male">Male</option>
					<option value="Female">Female</option>	
				</select>																						
			</fieldset>
			
			<fieldset id="personality">

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
			
			</fieldset>
						
			<fieldset id="global">
				<input type="hidden" name="section" value="<%=request.getParameter("section")%>">
				<input type="text" name="userId" value="<%=request.getParameter("userId")%>">
				
				<input type="hidden" value="submitted" name="submitted"/>
				<input type="submit" value="submit" name="submit"/>				
			</fieldset>		
			
		</form>
		
		<div class="error"></div>
		</div>
</div>