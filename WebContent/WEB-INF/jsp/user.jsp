<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />
<!--include header-->
	<div id="container"><!--container-->
			<div id="contents">
				<div class="wrap">

					<jsp:include page="includes/filters.jsp" />

					<div class="gendersign"></div>
					<h2>Marie-Ceicle Dolezal</h2>
					<div class="details">Montreal, Canada, 35 years old</div>

					<div class="gallery">
						<div class="featureImg"><img src="http://www.hdwallpapersdepot.com/wp-content/uploads/2012/07/4.jpg"></div>
						<div class="profilePics">
							<div id="galleryCarousel" data-carousel="">
								<div class="wrapper">
								<ul>
									<li><a class="group" href="photos.php"><img src="http://www.hdwallpapersdepot.com/wp-content/uploads/2012/07/Jessica-Alba-3.jpg"></a></li>
									<li><a class="group" href="photos.php"><img src="http://www.hdwallpapersdepot.com/wp-content/uploads/2012/07/Jessica-Alba.jpg"></a></li>
									<li><a class="group" href="photos.php"><img src="http://photos.imageevent.com/afap/wallpapers/stars/jessicaalba//Jessica%20Alba%20---1.jpg"></a></li>
									<li><a class="group" href="photos.php"><img src="http://www.topnews.in/light/files/Jessica-Alba_9.jpg"></a></li>
									<li><a class="group" href="photos.php"><img src="http://4.bp.blogspot.com/_aGZwEIFiNOA/S_S6JNZpk1I/AAAAAAAAD08/Pd-A9qq8o7Y/s1600/Jessica-Alba-fashion--26-celebrity-64992_492_650.jpg"></a></li>
									<li><a class="group" href="http://3.bp.blogspot.com/_aGZwEIFiNOA/S_S6Qx_lo9I/AAAAAAAAD1E/-ho-n1K4SdM/s1600/jessica-alba-49.jpg"><img src="http://3.bp.blogspot.com/_aGZwEIFiNOA/S_S6Qx_lo9I/AAAAAAAAD1E/-ho-n1K4SdM/s1600/jessica-alba-49.jpg"></a></li>
									<li><a class="group" href="http://4.bp.blogspot.com/_llNxv7aJ0ww/TPBd4xz5TDI/AAAAAAAAAEM/7W49xhVeTFk/s1600/Jessica-Alba-fashion--26-celebrity-64991_907_1200.jpg"><img src="http://4.bp.blogspot.com/_llNxv7aJ0ww/TPBd4xz5TDI/AAAAAAAAAEM/7W49xhVeTFk/s1600/Jessica-Alba-fashion--26-celebrity-64991_907_1200.jpg"></a></li>
									<li><a class="group" href="http://www.fansshare.com/photos/jessicaalba/jessica-alba-no-clothes-1371176159.jpg"><img src="http://www.fansshare.com/photos/jessicaalba/jessica-alba-no-clothes-1371176159.jpg"></a></li>
									<li><a class="group" href="http://www.whatsonxiamen.com/ent_images/9683_3.jpg"><img src="http://www.whatsonxiamen.com/ent_images/9683_3.jpg"></a></li>
								</ul>
								</div>
							</div>
						</div>
					</div>

					<ul class="collection">
						<li>
							<ul class="physical">
								<li id="relationship"><div class="title">Relationship ></div><div class="result">Single</div></li>
								<li id="bodytype"><div class="title">Body Type ></div><div class="result">Athletic</div></li>
								<li id="haircolour"><div class="title">Hair Colour ></div><div class="result">Blonde</div></li>
								<li id="eyecolour"><div class="title">Eye Colour ></div><div class="result">Blue</div></li>
								<li id="children"><div class="title">Children ></div><div class="result">None</div></li>
								<li id="education"><div class="title">Education ></div><div class="result">Degree</div></li>
								<li id="occupation"><div class="title">Occupation ></div><div class="result">Doctor</div></li>
							</ul>
						</li>
						<li>
							<h2>About me</h2>
							<div class="about"><p>Lorem impsum dolar dis stuff lorep impsum dis stuff lorep impsum dis stuff lorep impsum dis stuff lorep impsum</p></div>
						</li>
						<li>
							<h2>My identity</h2>
							<ul id="sliderResults" class="indicators"></ul>

							<!--<div id="controls" class="sliderToggles" data-role="slider-controls-nav">
								<span>88</span>
								<span>77</span>
								<span>55</span>
								<span>33</span>
								<span>40</span>
								<span>45</span>
								<span>70</span>
							</div>-->
						</li>
						<li>
							<h2>My main interests</h2>
							<div id="interestsPie"></div>
						</li>
						<li>
							<h2>I'm Seeking for</h2>
							<div id="seekingPie"></div>
						</li>
						<li>
							<h2>Quiz</h2>
							<div id="quizchart"></div>
						</li>
					</ul>


				</div>
			</div>
			<div id="googlebelt">
				<div class="title"><span>My Location</span></div>
				<div class="location"><iframe width="100%" height="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.co.uk/maps?q=google+map+embed&amp;aq=f&amp;ie=UTF8&amp;hl=en&amp;t=m&amp;ll=51.451867,-0.182648&amp;spn=0.04279,0.617981&amp;z=11&amp;output=embed"></iframe></div>
			</div>
	</div><!--container-->
		
				
<!-- 	<h2>Specific Member</h2>
			
			<table border="1">
				<c:forEach var="person" items="${people}">
					<tr>
						<td>${person.username}</td>
						<td>${person.emailaddress}</td>
						<td>${person.password}</td>
						<td>${person.gender}</td>
						<td>${person.birthdate}</td>
						<td>${person.ethnicity}</td>								
						<td>${person.country}</td>
						<td>${person.registeredon}</td>
						<td>${person.lastloggedon}</td>								
						<td>${person.isloggedon}</td>
					</tr>
				</c:forEach>   
			</table>

			<table border="1">
				<c:forEach var="person" items="${people}">
					<tr>
						<td>${person.seeking}</td>
					</tr>
				</c:forEach>   
			</table>			
			-->
			
<!--include footer-->    
<jsp:include page="includes/footer.jsp" />