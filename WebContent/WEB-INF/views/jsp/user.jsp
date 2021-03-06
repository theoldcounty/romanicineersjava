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
						
						

					<div class="usersummary">
						<div class="gendersign ${fn:toLowerCase(people[0].gender)}"></div>
						<h2>
							<c:if test="${people[0].whichscreenname == 'realname'}">${people[0].realname}</c:if>
							<c:if test="${people[0].whichscreenname != 'realname'}">${people[0].username}</c:if>
						</h2>
						<div class="progressbar"><div class="bar"></div></div>
					</div>
					
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
						<div class="featureImg" data-image-id="${gImages.imgId}">
							<a class="fancyboximage fancybox.image" rel="gallery1" href="retrieveimage?image_id=${people[1].galleryResponse[0].imgId}&height=750" title="Hot Girl">
								<img src="retrieveimage?image_id=${people[1].galleryResponse[0].imgId}&width=430">
							</a>
						</div>
						<div class="profilePics">
						
							<div class="swiper-holder">
								<div data-swiper="true" data-loop="true" data-mode="horizontal" data-scrollcontainer="false" data-freemode="false" data-freemodefluid="false" data-slidesperview="1" data-slidespergroup="1" data-speed="500" class="swiper-container">
										<div class="swiper-wrapper">
	
											<c:forEach var="gImages" items="${people[1].galleryResponse}" varStatus="counter">
													<c:choose>
														<c:when test="${counter.count % 9 == 0}">
																<!-- group start-->	
																<div class="swiper-slide">
														</c:when>
														<c:otherwise>
															<c:if test="${counter.count == 1}">
																<!-- group start-->	
																<div class="swiper-slide">
															</c:if>  				
														</c:otherwise>   												
													</c:choose>																					
												
														<div class="imageholder" data-image-id="${gImages.imgId}">
															<a class="fancyboximage fancybox.image" rel="gallery1" href="retrieveimage?image_id=${gImages.imgId}&height=750" title="Hot Girl">
																<img src="retrieveimage?image_id=${gImages.imgId}&width=160">
															</a>
														</div>
												
												
													<c:choose>
														<c:when test="${counter.count % 8 == 0}">
																</div>
																<!-- group end-->
														</c:when>
														<c:otherwise>
															<c:if test="${counter.last}">
																</div>
																<!-- group end-->	
															</c:if>  				
														</c:otherwise>   												
													</c:choose>											
											</c:forEach>
										
									</div>
									
														
								</div>
							
							
									<a class="arrowhandler arrow-left"></a>
									<a class="arrowhandler arrow-right"></a>
							</div>
						</div>
					</div>

					<div class="calltoaction">
						<ul>
							<li><a class="fancyboxtrigger fancybox.ajax" href="viewFollowers">*(unlogged) Followers - shows users following (overlay)</a></li>
							<li><a class="fancyboxtrigger fancybox.ajax" href="addFollowers">*(unlogged) Follow me - action to follow the user (overlay)</a></li>
							<li><a href="scheduledate">*(unlogged) Schedule a date  <!-- &userId=${people[0]._id}--></a></li>

							<li><a class="fancyboxtrigger fancybox.ajax" href="imageform?userId=${people[0]._id}">*(Logged In)My Photos/Add Picture (overlay)</a></li>

							<li><a class="fancyboxtrigger fancybox.ajax" href="sendPrivateMessages">*(unlogged)Private Message user (overlay)</a></li>
							<li><a class="fancyboxtrigger fancybox.ajax" href="viewPrivateMessages?userId=${people[0]._id}">*(Logged In)My Inbox view messages (overlay)</a></li>
						</ul>
					</div>

					<!--profileDetails-->
					<div class="profileDetails">
						<!--collection-->
						<ul class="collection">
							<li>
								<ul class="physical">
									<li id="gender">
										<div class="title">*Gender ></div>
										<div class="result">${people[0].gender}</div>
									</li>
									<li id="ethnicity">
										<div class="title">*Ethnicity ></div>
										<div class="result">
											<c:forEach var="ethnic" items="${ethnicityList}">
												<c:choose>
													<c:when test="${people[0].ethnicity == ethnic.key}">
														${ethnic.value}
													</c:when>
												</c:choose>
											</c:forEach>
										</div>
									</li>
									<li id="relationship">
										<div class="title">Relationship ></div>
										<div class="result">${people[0].kindofrelationship}</div>
									</li>
									<li id="bodytype">
										<div class="title">Body Type ></div>
										<div class="result">${people[0].bodytype}</div>
									</li>
									<li id="haircolour">
										<div class="title">Hair Colour ></div>
										<div class="result">${people[0].haircolor}</div>
									</li>
									<li id="eyecolour">
										<div class="title">Eye Colour ></div>
										<div class="result">${people[0].eyecolor}</div>
									</li>
									<li id="children">
										<div class="title">Children ></div>
										<div class="result">${people[0].children}</div>
									</li>
									<li id="education">
										<div class="title">Education ></div>
										<div class="result">${people[0].education}</div>
									</li>
									<li id="occupation">
										<div class="title">Occupation ></div>
										<div class="result">${people[0].occupation}</div>
									</li>
									<li id="languages">
										<div class="title">Spoken Languages ></div>
										<div class="result">
											<c:forEach var="language" items="${people[0].languages}">
												${language}
											</c:forEach>
										</div>
									</li>
								</ul>
								<a class="fancyboxtrigger fancybox.ajax form" href="edit_user?section=physical&userId=${people[0]._id}">Edit Physical Section</a>

								<a class="fancyboxtrigger fancybox.ajax form" href="edit_user?section=account&userId=${people[0]._id}">Edit Account Section</a>


							</li>
							<li>
								<h2>About me</h2>
								<div class="about">
									<p>${people[0].about}</p>
									<a class="fancyboxtrigger fancybox.ajax form" href="edit_user?section=about&userId=${people[0]._id}">Edit About Section</a>
								</div>
							</li>
							<li>
								<h2>My identity</h2>
								<ul id="sliderResults" class="indicators"></ul>
								<a class="fancyboxtrigger fancybox.ajax form" href="edit_user?section=personality&userId=${people[0]._id}">Edit Personality Section</a>
							</li>
							<li>
								<h2>My main interests</h2>
								<div id="interestsPie"></div>
								<div class="editdata">
									<a class="fancyboxtrigger fancybox.ajax form" href="edit_chart?type=interests&userId=${people[0]._id}">Edit Chart</a>
								</div>
								<div class="nodata">
									<p>Show you are interesting, add a visual chart</p>
									<a class="fancyboxtrigger fancybox.ajax form" href="edit_chart?type=interests&userId=${people[0]._id}">Add Chart</a>
								</div>
							</li>
							<li>
								<h2>I'm Seeking for</h2>
								<div id="seekingPie"></div>
								<div class="editdata">
									<a class="fancyboxtrigger fancybox.ajax form" href="edit_chart?type=seeking&userId=${people[0]._id}">Edit Chart</a>
								</div>
								<div class="nodata">
									<p>Show what you want in a relationship, add a visual chart</p>
									<a class="fancyboxtrigger fancybox.ajax form" href="edit_chart?type=seeking&userId=${people[0]._id}">Add Chart</a>
								</div>
							</li>
							<li>
								<h2>Quiz</h2>
								<div id="quizchart"></div>
								<div class="editdata">
									<a class="fancyboxtrigger fancybox.ajax form" href="edit_chart?type=visiting&userId=${people[0]._id}">Edit Chart</a>
								</div>
								<div class="nodata">
									<p>Where do you want to visit?, add a visual chart</p>
									<a class="fancyboxtrigger fancybox.ajax form" href="edit_chart?type=visiting&userId=${people[0]._id}">Add Chart</a>
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
