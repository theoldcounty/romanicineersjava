<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="includes/header.jsp" />
<!--include header-->
	<div id="container" class="user"><!--container-->
			
			<!--scheduleDate-->
			<div class="scheduleDate">
				<!--googlemaps-->
				<div class="googlemaps">
					<!--page-container-->
					<div class="page-container">
							<section class="maps-panel">
								<div class="data-attributes">
									<div id="googleMapData">
										<a id="userData" href="#" data-user-lat="51.529622428012544" data-user-long="-0.1381740757634631">User Focused</a><br>
										<a id="candidateData" href="#" data-candidate-lat="53.331647402998776" data-candidate-long="-2.9767332668951405">Candidate Focused</a><br>
									</div>
								</div>
								<ul class="control-container">
									<li>
										<div class="header-wrapper">
											<h1>Find a place of interest</h1>
											<p>Open light box with venue of interest, picture of candidate profile and user profile. Small list/pie charts of reminder interests, pics of the venue.. button step to schedule a date.... form showing the date/time for the date, small message.. gets sent to the private email of both parties... an email notification is sent out with details from the venue etc... the VENUE location, details,
										</div>
									</li>
									<li>
										<form id="searchForm" action="#">
											<input type="hidden" name="enableCoupons" value="false"/>
											<input type="hidden" name="longitude" value=""/>
											<input type="hidden" name="latitude" value=""/>
											<input type="hidden" name="id" value="114">
											<input type="hidden" name="searchfield" value="">
											<input type="hidden" name="radius" value="5">
											<div class="search-textfield-container">
												<input id="searchTextField" class="search-textfield" type="text" placeholder="Location, postcode or street"/>
												<a hreF="#" data-id="reset-button" class="close-button">close-icon</a>
				
												<select name="queryFoursquare" id="queryFoursquare" class="foursquare-textfield">
													<option value="theatre">Theatre</option>
													<option value="bowling">Bowling</option>
													<option value="restaurant">Restaurant</option>
													<option value="cafe">Cafe</option>
													<option value="cinema">Cinema</option>
													<option value="ice cream">Ice Cream</option>
													<option value="comedy shows">Comedy Shows</option>
													<option value="pub">Pub</option>
													<option value="club">Club</option>
													<option value="concerts">Concerts</option>
													<option value="parks">Parks</option>
													<option value="museums">Museums</option>
													<option value="zoo">Zoo</option>
													<option value="horse back riding">Horse back riding</option>
													<option value="picnic">Picnic</option>
													<option value="amusement parks">Amusement Parks</option>
													<option value="Water parks">Water Parks</option>
													<option value="hiking">Hiking</option>
													<option value="aquarium">Aquarium</option>
													<option value="driving range">Driving Range</option>
													<option value="art gallery">Art Gallery</option>
													<option value="local music show">Local Music Show</option>
													<option value="ice-skating">Ice-Skating</option>
													<option value="stringfellows">Stringfellows</option>
												</select>
				
												<span data-id="search-button" class="link-button small">
													<input class="text" type="button" value="Go"/>
												</span>
											</div>
										</form>
									</li>
								</ul>
							</section>
							<div id="map_canvas"></div>
					</div>
					<!--page-container-->
				</div>
				<!--googlemaps-->
			</div>
			<!--scheduleDate-->
	</div><!--container-->

<!--include footer-->
<jsp:include page="includes/footer.jsp" />











