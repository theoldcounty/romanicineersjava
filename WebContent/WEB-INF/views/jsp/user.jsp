<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="includes/header.jsp" />
<!--include header-->
	<div id="container" class="user"><!--container-->
			<div id="contents">
				<div class="wrap">

					<jsp:include page="includes/filters.jsp" />
						${people[0]}
						${people[1]}
						
					<div class="gendersign"></div>
					<h2>
						<c:if test="${people[0].whichscreenname == 'realname'}">${people[0].realname}</c:if>
						<c:if test="${people[0].whichscreenname != 'realname'}">${people[0].username}</c:if>
					</h2>
					<div class="progressbar"><div class="bar"></div></div>
					<div class="details" data-uid="${people[0]._id}">
						<c:forEach var="countryList" items="${countryList}">
							<c:choose>
								<c:when test="${people[0].country == countryList.key}">
									${countryList.value}
								</c:when>
							</c:choose>
						</c:forEach>
						, ${people[1].ageInYears} years old</div>

					<div class="gallery">
						<div class="featureImg">
							<img src="http://4.bp.blogspot.com/-GKSz-e2NS5A/Tk7MevN7CiI/AAAAAAAACXI/nBdBOhzfnoc/s1600/Jessica-Alba_bee-media.blogspot.com+%25281%2529">
							<a class="iframebox" href="showUpload2">Add Picture</a>
						</div>
						<div class="profilePics">
							<div id="galleryCarousel" data-carousel="true" data-count="4">
								<div class="wrapper">
									<ul>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									<li><a class="group" href="#"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></li>
									</ul>
								</div>
							</div>
							<c:if test="${people[1].countGallery > 1}">
								<div id="galleryCarousel" data-carousel="" data-count="${people[1].countGallery}">
									<div class="wrapper">
									<ul>
										<c:set var="pictures" value="${people[1].gallery}"/>									
										<c:forEach items="${pictures}" varStatus="loop"> 
											<li><a class="group" href="${pictures[loop.index].full}"><img src="${pictures[loop.index].thumbnail}"></a></li>
										</c:forEach>
									</ul>
									</div>
								</div>
							</c:if>
						</div>
					</div>
					
					<div class="calltoaction">
						<ul>
							<li><a href="#">Schedule a date</a></li>
							<li><a href="#">Private Message user</a></li>
						</ul>
					</div>
										
					<!--scheduleDate-->
					<div class="scheduleDate">
					
<div class="googlemaps">
	<div class="page-container">
		<div class="page-content">
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
	</div>
</div>					
					</div>
					<!--scheduleDate-->
										
					<!--profileDetails-->
					<div class="profileDetails">	
						<!--collection-->
						<ul class="collection">
							<li>
								<ul class="physical">
									<li id="gender"><div class="title">*Gender ></div><div class="result">${people[0].gender}</div></li>
									<li id="ethnicity"><div class="title">*Ethnicity ></div><div class="result">
										<c:forEach var="ethnic" items="${ethnicityList}">
											<c:choose>
												<c:when test="${people[0].ethnicity == ethnic.key}">
													${ethnic.value}
												</c:when>
											</c:choose>
										</c:forEach>
									</div></li>						
									<li id="relationship"><div class="title">Relationship ></div><div class="result">${people[0].kindofrelationship}</div></li>
									<li id="bodytype"><div class="title">Body Type ></div><div class="result">${people[0].bodytype}</div></li>
									<li id="haircolour"><div class="title">Hair Colour ></div><div class="result">${people[0].haircolor}</div></li>
									<li id="eyecolour"><div class="title">Eye Colour ></div><div class="result">${people[0].eyecolor}</div></li>
									<li id="children"><div class="title">Children ></div><div class="result">${people[0].children}</div></li>
									<li id="education"><div class="title">Education ></div><div class="result">${people[0].education}</div></li>
									<li id="occupation"><div class="title">Occupation ></div><div class="result">${people[0].occupation}</div></li>
									<li id="languages"><div class="title">Spoken Languages ></div><div class="result"><c:forEach var="language" items="${people[0].languages}">${language} </c:forEach></div></li>
								</ul>
							</li>
							<li>
								<h2>About me</h2>
								<div class="about">
									<p>${people[0].about}</p>
									<p>Lorem impsum dolor sit amet, consecteur adsdfsd. erlit. Maescenae aliaqul ipsum vehiaclsd anti test. Cos soc asd kasd aodsa.</p>
								</div>
							</li>
							<li>
								<h2>My identity</h2>
								<ul id="sliderResults" class="indicators"></ul>
							</li>
							<li>
								<h2>My main interests</h2>
								<div id="interestsPie"></div>
								<div class="nodata">
									<p>Show you are interesting, add a visual chart</p>
									<a class="shazam form" href="edit_chart?type=interests&userId=${people[0]._id}">Add Chart</a>
								</div>
							</li>
							<li>
								<h2>I'm Seeking for</h2>
								<div id="seekingPie"></div>
								<div class="nodata">
									<p>Show what you want in a relationship, add a visual chart</p>
									<a class="shazam form" href="edit_chart?type=seeking-relationship&userId=${people[0]._id}">Add Chart</a>
								</div>
							</li>
							<li>
								<h2>Quiz</h2>
								<div id="quizchart"></div>
								<div class="nodata">
									<p>Where do you want to visit?, add a visual chart</p>
									<a class="shazam form" href="edit_chart?type=future-holiday&userId=${people[0]._id}">Add Chart</a>
								</div>
							</li>
						</ul>
						<!--collection-->
						
						<!--other-candidates-->
						<div id="other-candidates">
							<ul class="users">
								<li class="element odd" data-user-country="United Kingdom" data-user-interests="movies" data-user-online="1" data-user-gender="Male" data-user-name="Brad Pitt" data-user-id="21"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></div></li>
								<li class="element even" data-user-country="United Kingdom" data-user-interests="oil painting, sex" data-user-gender="Female" data-user-name="Jessica Alba" data-user-id="23"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://images.cryhavok.org/d/1707-2/Jessica+Alba+in+Fishnets.jpg"></a></div></li>
								<li class="element odd" data-user-country="United Kingdom" data-user-interests="robots, sex" data-user-gender="Female" data-user-name="Beyonce Knowles" data-user-id="25"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://images.starpulse.com/Photos/Previews/Beyonce-Knowles-sb32.jpg"></a></div></li>
								<li class="element even" data-user-country="United States" data-user-interests="sex" data-user-gender="Female" data-user-name="Gwen Stefani" data-user-id="27"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://beautiful-pics.org/wp-content/uploads/2011/12/Gwen_Stefani_7.jpg"></a></div></li>
								<li class="element odd" data-user-country="United States" data-user-interests="tennis, sex" data-user-gender="Male" data-user-name="David Bowie" data-user-id="29"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://artopia444.files.wordpress.com/2010/01/rs433david-bowie-rolling-stone-no-433-october-1984-posters.jpg"></a></div></li>
								<li class="element even" data-user-country="United States" data-user-interests="sex" data-user-gender="Female" data-user-name="Jeri Ryan" data-user-id="31"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://api.ning.com/files/yXVPvJ2tIpTs7Tq0fE2XprWLRMz5dWvfO3S-R8yP63kihM0ULCAEG8eXczUcnNhmzsqrpk*pjadPu9mhHw3zeyfQw1*LQRGc/JeriRyan_353.jpg"></a></div></li>
								<li class="element odd" data-user-country="United Kingdom" data-user-interests="films, sex" data-user-gender="Male" data-user-name="Christian Bale" data-user-id="32"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://collider.com/wp-content/image-base/People/C/Chrisrian_Bale/Christian%20Bale%20image%20(1).jpg"></a></div></li>
								<li class="element even" data-user-country="United Kingdom" data-user-interests="sex" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://www.orble.com/images/meganfox1.jpg"></a></div></li>
								<li class="element odd" data-user-country="United Kingdom" data-user-interests="robots, spaceflight, football, movies" data-user-gender="Male" data-user-name="Johnny Depp" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://shechive.files.wordpress.com/2011/11/britney-spears-13.jpg?w=500&h=666"></a></div></li>
								<li class="element even" data-user-country="United Kingdom" data-user-interests="spaceflight, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://www.maniacworld.com/angelina-jolie-cleavage.jpg"></a></div></li>
								<li class="element odd" data-user-country="United Kingdom" data-user-interests="football, movies" data-user-online="1" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://i144.photobucket.com/albums/r164/johnkb8/Babes/Hottie2-1-1.jpg"></a></div></li>
								<li class="element even" data-user-country="United Kingdom" data-user-interests="sex" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://blogs.sundaymercury.net/anorak-city/kelly-brook.jpg"></a></div></li>
								<li class="element odd" data-user-country="France" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://img.ibtimes.com/www/data/images/full/2012/04/23/266060-bollywood-actress-aishwarya-gestures-in-kolkata-in-2008.jpg"></a></div></li>
								<li class="element even" data-user-country="United Kingdom" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://www.rgbpicture.com/img/cool/wreslers/wreslers01.jpg"></a></div></li>
								<li class="element odd" data-user-country="United Kingdom" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://www.yee.ch/movies/L/LI/Lindsay_Lohan/Lindsay_Lohan_pussy.jpg"></a></div></li>
								<li class="element even" data-user-country="Japan" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://www.cinemavoyage.com/images/dianekruger2007062927685011.jpg"></a></div></li>
								<li class="element odd" data-user-country="United Kingdom" data-user-interests="football" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://www.top39.com/wp-content/uploads/2010/07/Lucy_Liu.jpg"></a></div></li>
								<li class="element even" data-user-country="United Kingdom" data-user-interests="spaceflight, sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://www.cinemavoyage.com/images/juliavothbitchslapmaxim200805.jpg"></a></div></li>
								<li class="element odd" data-user-country="United Kingdom" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://media.santabanta.com/newsite/cinemascope/feed/amrita-puri10.jpg"></a></div></li>
								<li class="element even" data-user-country="France" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="http://img.ezinemark.com/imagemanager2/files/30004254/2011/10/2011-10-14-16-02-44-5-the-italian-actress-and-model-sara-tommasi-used-to.jpeg"></a></div></li>
								<li class="element odd" data-user-country="Spain" data-user-interests="movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="user?id=4fa6eddc0234964172522248"><img src="https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRvGlbHG2p2zNW3NO5qkGFdTNqFAcao206fjbt21TvHxbeR-Tw0"></a></div></li>
							</ul>
						</div>
						<!--other-candidates-->
					</div>
					<!--profileDetails-->	
				</div>
			</div>
			<div id="googlebelt">
				<div class="title"><span>My Location</span></div>
				<div class="location"><div id="canvasMap" data-lat="${people[0].latitude}" data-long="${people[0].longitude}"></div></div>
			</div>
	</div><!--container-->
			
<!--include footer-->    
<jsp:include page="includes/footer.jsp" />