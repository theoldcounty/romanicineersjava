<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="shazam-wrapper" class="regular">
		
		<form id="sendPrivateMessage" action="sendPrivateMessages" enctype="multipart/form-data" method="post" action="">
			
			<fieldset id="step1">				
				<textarea name="message" rows="4" cols="50"></textarea>
				<input type="text" value="" name="recepientUid"/>
				<input type="text" value="" name="senderUid"/>

				<input type="hidden" value="submitted" name="submitted"/>
				<input type="submit" value="submit" name="submit"/>				
			
			</fieldset>
		</form>
		
		<div class="error"></div>
		</div>
</div>