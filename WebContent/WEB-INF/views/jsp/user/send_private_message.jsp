<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="shazam-wrapper" class="regular">
		
		<form id="sendPrivateMessage" action="send_private_message" enctype="multipart/form-data" method="post" action="">
			
			<fieldset id="step4">

				
				<textarea name="privatemessage" rows="4" cols="50"></textarea>

				<input type="hidden" value="submitted" name="submitted"/>
				<input type="submit" value="submit" name="submit"/>				
			
			</fieldset>
		</form>
		
		<div class="error"></div>
		</div>
</div>