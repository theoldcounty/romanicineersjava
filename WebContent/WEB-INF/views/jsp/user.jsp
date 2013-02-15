<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="includes/header.jsp" />
<!--include header-->
	<div id="container"><!--container-->
			<div id="contents">
				<div class="wrap">

					<jsp:include page="includes/filters.jsp" />
${people[0]}
${people[1]}
					<div class="gendersign"></div>
					<h2>
						<c:if test="${people[0].whichscreenname == 'realname'}">
							${people[0].realname}
						</c:if>
						<c:if test="${people[0].whichscreenname != 'realname'}">
							${people[0].username}
						</c:if>
					</h2>
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
						<div class="featureImg"><img src="http://www.hdwallpapersdepot.com/wp-content/uploads/2012/07/4.jpg"></div>
						<div class="profilePics">
							<c:if test="${people[1].countGallery > 1}">
								<div id="galleryCarousel" data-carousel="" data-count="${people[1].countGallery}">
									<div class="wrapper">
									<ul>
										<c:set var="pictures" value="${people[1].gallery}"/>									
										<c:forEach items="${pictures}" varStatus="loop"> 
											<li><a class="group" href="${pictures[loop.index].full}"><img src="${pictures[loop.index].thumbnail}"></a></li>
										</c:forEach>								</ul>
									</div>
								</div>
							</c:if>
						</div>
					</div>

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
							<div class="about"><p>${people[0].about}</p></div>
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
								<a class="cboxElement" href="edit_chart">Add Chart</a>
							</div>
						</li>
						<li>
							<h2>I'm Seeking for</h2>
							<div id="seekingPie"></div>
							<div class="nodata">
								<p>Show what you want in a relationship, add a visual chart</p>
								<a class="cboxElement" href="edit_chart">Add Chart</a>
							</div>
						</li>
						<li>
							<h2>Quiz</h2>
							<div id="quizchart"></div>
							<div class="nodata">
								<p>Where do you want to visit?, add a visual chart</p>
								<a class="cboxElement" href="edit_chart">Add Chart</a>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div id="googlebelt">
				<div class="title"><span>My Location</span></div>
				<div class="location"><div id="canvasMap" data-lat="${people[0].latitude}" data-long="${people[0].longitude}"></div></div>
			</div>
	</div><!--container-->
			
<!--include footer-->    
<jsp:include page="includes/footer.jsp" />