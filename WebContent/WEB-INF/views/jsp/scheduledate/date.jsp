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
				
				<div class="grid"><!--grid-->
					<div class="left fifty">		
						<div id="venue">
							<div class="grid"><!--grid-->
								<div class="left fifty">
									<div id="map"></div>
									<div id="name"></div>
									<div id="location"></div>
									<div id="rating"></div>		
								</div>
								<div class="right fifty last">
									<ul id="events"></ul>
									<ul id="tips"></ul>
								</div>
							</div><!--grid-->
							
							<div class="grid"><!--grid-->			
								<div class="left hundred">
									<ul id="photos"></ul>
								</div>
							</div><!--grid-->
						</div>
					</div>
					<div class="right twenty last">
						<h3>Candidate profile</h3>
						
						picture<br/>
						
						<h3>Charlize Theron, 21</h3>
						<h3>London, United Kingdom</h3>
						
						<div>
							pie chart of interests
						</div>
						
					</div>			
				</div><!--grid-->				
				
				<style>
					.grid{
						overflow:hidden;
					}
					.left{
						float:left;
					}
					.right{
						float:right;
					}
					

					.twenty{
						width: 20%;
					}
					.fifty{
						width: 50%;
					}
					.eighty{
						width: 80%;
					}
					.hundred{
						width: 100%;
					}
										
					#date{
						width: 100%;
					}
					
					
					#tips{
						overflow:hidden;
						height: 300px;
					}
					
				
					#photos{
						overflow:hidden;
						padding: 0;
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
			
			<!-- 
			<fieldset id="step2">				
				<textarea name="message" rows="4" cols="50"></textarea>
				<input type="text" value="" name="recepientUid"/>
				<input type="text" value="" name="senderUid"/>

				<input type="hidden" value="submitted" name="submitted"/>
				<input type="submit" value="submit" name="submit"/>				
			
			</fieldset>-->
		</form>
		
		<div class="error"></div>
		</div>
</div>