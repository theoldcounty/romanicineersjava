<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="shazam-wrapper" class="regular">
		
		<form id="sendPrivateMessage" action="sendPrivateMessages" enctype="multipart/form-data" method="post" action="">
			
			<fieldset id="step1">				
				<textarea name="message" rows="4" cols="50"></textarea>
				<input type="text" name="recepientUid" value="51895628d1efa7a5d9df83c9"/>
				<input type="text" name="senderUid" value="518b090fd1ef11c72b4bcb85"/>

				<input type="hidden" value="submitted" name="submitted"/>
				<input type="submit" value="submit" name="submit"/>				
			
			</fieldset>
		</form>
		
		<div class="error"></div>
		</div>
</div>