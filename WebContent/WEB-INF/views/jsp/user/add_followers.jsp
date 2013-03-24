<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="shazam-wrapper" class="regular">
		
		<form id="addFollower" action="add_followers" enctype="multipart/form-data" method="post" action="">
			
			<fieldset id="step1">

				//are you sure you want to follow this user.

				<input type="hidden" value="submitted" name="submitted"/>
				<input type="submit" value="submit" name="submit"/>				
			
			</fieldset>
		</form>
		
		<div class="error"></div>
		</div>
</div>