<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="shazam-wrapper" class="regular">
		<div id="formType" data-form-type="venuefinder"></div>
		
		<div class="venuefinder">
			<ul>
				<li><a href="#step1">Step 1<br>approve venue</a></li>
				<li><a href="#step2">Step 2<br>approve date/time for date</a></li>
			</ul>
			<form id="venueForm" action="venueForm" enctype="multipart/form-data" method="post" action="">
				<fieldset id="step1">
					show the venue stuff from the json feed,	

				</fieldset>	

				<fieldset id="step2">
					//choose a date/time
					
					
					<input type="hidden" value="submitted" name="submitted"/>
					<input type="submit" value="submit" name="submit"/>								
				</fieldset>					
			</form>
			<div class="error"></div>			
		</div>
</div>
