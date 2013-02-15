<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="colorboxWrapper" class="regular">
		<h1>Visual Dating</h1>

		<div class="registration">

		
		<form id="registerForm" action="edit_chart" enctype="multipart/form-data" method="post" action="">
						
				
			<fieldset id="step4">


				<label>Please indicate 5 of your interest and their importance from 0 to 100</label>
				
				<fieldset class="doughnutWrap">
					<select name="interests">
						<option value="Cinema">Cinema</option>
						<option value="Clubbing">Clubbing</option>
						<option value="Football">Football</option>
						<option value="Baseball">Baseball</option>					
					</select>
					<fieldset data-fieldname="interestknobs" id="knob1" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="5"></fieldset>
				</fieldset>
				
				<fieldset class="doughnutWrap">
					<select name="interests">
						<option value="Interest 02">Interest 02</option>				
					</select>
					<fieldset data-fieldname="interestknobs" id="knob2" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="15"></fieldset>
				</fieldset>
						
				<fieldset class="doughnutWrap">	
					<select name="interests">
						<option value="Interest 03">Interest 03</option>				
					</select>
					<fieldset data-fieldname="interestknobs" id="knob3" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="45"></fieldset>
				</fieldset>
				
				<fieldset class="doughnutWrap">
					<select name="interests">
						<option value="Interest 04">Interest 04</option>				
					</select>
					<fieldset data-fieldname="interestknobs" id="knob4" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="85"></fieldset>
				</fieldset>		
							
				<fieldset class="doughnutWrap">		
					<select name="interests">
						<option value="Interest 05">Interest 05</option>				
					</select>
					<fieldset data-fieldname="interestknobs" id="knob5" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="35"></fieldset>		
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
