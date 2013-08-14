<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="shazam-wrapper" class="regular">
		
		<form id="date" data-venue-id="<%=request.getParameter("venueId")%>" data-senderUid="<%=request.getParameter("senderUid")%>" data-recepientUid="<%=request.getParameter("recepientUid")%>" action="date" enctype="multipart/form-data" method="post" action="">
			
			<fieldset id="step1">				
			
				<!--  
				<%=request.getParameter("venueId")%>
				<%=request.getParameter("senderUid")%>
				<%=request.getParameter("recepientUid")%>
				-->
						
				<div id="canvasMap" data-lat="" data-long=""></div>
				
				<div id="venue">
					<div id="name"></div>
					<!-- <div id="id"></div>-->
					<div id="location"></div>
					<div id="rating"></div>
					
					<ul id="photos"></ul>
					
					<ul id="tips"></ul>
				</div>
				
				<style>
					#photos{
						overflow:hidden;
						width: 55%;
					}
					#photos li{
						float:left;
					}
					
					#photos li img{
						width: 150px;
					}
				</style>
				
				
				<ul class="venueImages">
					<li></li>
				</ul>				
				
				
				
			</fieldset>
			
			
			<fieldset id="step2">				
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