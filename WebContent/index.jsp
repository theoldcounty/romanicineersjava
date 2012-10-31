<jsp:include page="WEB-INF/jsp/includes/header.jsp" />
<!--include header-->

	<!--
	<div class="container">
		<h2>The proto dating site</h2>
		<div>
		thumbnails list of members
		</div>

		${message}

		<h2>View Users</h2>
		<ul>
			<li><a href="members">List Users</a></li>
		</ul>

		<h2>Search Users</h2>
		<ul>
			<li><a href="search?query=rob">Search through members</a></li>
		</ul>

		<h2>Inbox functions</h2>
		<ul>
			<li><a href="messages?mode=compose">Compose</a></li>
			<li><a href="messages?mode=sent">Read Sent messages</a></li>
			<li><a href="messages?mode=received">Read received messages</a></li>
		</ul>


		<h2>Upload function</h2>
		<ul>
			<li><a href="gallery">View gallery</a></li>
			<li><a href="galleryupload">File Upload</a></li>
		</ul>

	</div>
	-->


		<div id="container"><!--container-->
			<div id="tooltip">
				<div class="pointer"></div>
				<div class="biodetails">
					<div class="wrapper">
						<div class="bioimg">
							<div class="avatar"><img src="http://3.bp.blogspot.com/-nPkfVayQelQ/TlVBikw7SpI/AAAAAAAABFU/xii61syZwP8/s400/Jessica+Alba+iPhone+Wallpaper-11.jpg"></div>
							<div class="goals">
								<div class="head"></div>
								<div class="heart"></div>
								<div class="hand"></div>
							</div>
						</div>
						<div class="biospec">
							<h2>Jean Midel</h2>
							<div class="piechart"><div id="biometricPie"></div></div>
							<div class="gender"></div>
						</div>
					</div>
				</div>
				<div class="fullprofile"><div class="placement">See full profile</div></div>
				<nav class="icons">
					<ul>
						<li class="myphotos"><a href="#">My Photos</a></li>
						<li class="followers"><a href="#">Followers</a></li>
						<li class="followme"><a href="#">Follow me</a></li>
						<li class="talktome"><a href="#">Talk to me</a></li>
					</ul>
				<nav>
			</div>
			<div id="contents">
				<div class="wrap">

					<jsp:include page="WEB-INF/jsp/includes/filters.jsp" />


					<h2>Welcome on visual dating, find love, in a visual way</h2>

<!--
<section id="options" class="clearfix">
    <h3>Sort</h3>

    <ul class="option-set clearfix">
      <li><a href="#sortBy=original-order" class="selected">original-order</a></li>
      <li><a href="#sortBy=name">name</a></li>
      <li><a href="#sortBy=random">random</a></li>
    </ul>

    <h3>Sort direction</h3>

    <ul class="option-set clearfix">
      <li><a href="#sortAscending=true" class="selected">sort ascending</a></li>
      <li><a href="#sortAscending=false">sort descending</a></li>
    </ul>
</section>  -->


					<ul class="users">
						<li class="element odd" data-user-country="United Kingdom" data-user-interests="movies" data-user-online="1" data-user-gender="Male" data-user-name="Brad Pitt" data-user-id="21"><div class="avatar"><a href="template-profile.php"><img src="http://www.thevintagedane.com/wp-content/uploads/2012/01/brad-pitt-wired-magazine-04-250x250.jpg"></a></div></li>
						<li class="element even" data-user-country="United Kingdom" data-user-interests="oil painting, sex" data-user-gender="Female" data-user-name="Jessica Alba" data-user-id="23"><div class="avatar"><a href="template-profile.php"><img src="http://images.cryhavok.org/d/1707-2/Jessica+Alba+in+Fishnets.jpg"></a></div></li>
						<li class="element odd" data-user-country="United Kingdom" data-user-interests="robots, sex" data-user-gender="Female" data-user-name="Beyonce Knowles" data-user-id="25"><div class="avatar"><a href="template-profile.php"><img src="http://images.starpulse.com/Photos/Previews/Beyonce-Knowles-sb32.jpg"></a></div></li>
						<li class="element even" data-user-country="United States" data-user-interests="sex" data-user-gender="Female" data-user-name="Gwen Stefani" data-user-id="27"><div class="avatar"><a href="template-profile.php"><img src="http://beautiful-pics.org/wp-content/uploads/2011/12/Gwen_Stefani_7.jpg"></a></div></li>
						<li class="element odd" data-user-country="United States" data-user-interests="tennis, sex" data-user-gender="Male" data-user-name="David Bowie" data-user-id="29"><div class="avatar"><a href="template-profile.php"><img src="http://artopia444.files.wordpress.com/2010/01/rs433david-bowie-rolling-stone-no-433-october-1984-posters.jpg"></a></div></li>
						<li class="element even" data-user-country="United States" data-user-interests="sex" data-user-gender="Female" data-user-name="Jeri Ryan" data-user-id="31"><div class="avatar"><a href="template-profile.php"><img src="http://api.ning.com/files/yXVPvJ2tIpTs7Tq0fE2XprWLRMz5dWvfO3S-R8yP63kihM0ULCAEG8eXczUcnNhmzsqrpk*pjadPu9mhHw3zeyfQw1*LQRGc/JeriRyan_353.jpg"></a></div></li>
						<li class="element odd" data-user-country="United Kingdom" data-user-interests="films, sex" data-user-gender="Male" data-user-name="Christian Bale" data-user-id="32"><div class="avatar"><a href="template-profile.php"><img src="http://collider.com/wp-content/image-base/People/C/Chrisrian_Bale/Christian%20Bale%20image%20(1).jpg"></a></div></li>
						<li class="element even" data-user-country="United Kingdom" data-user-interests="sex" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://www.orble.com/images/meganfox1.jpg"></a></div></li>
						<li class="element odd" data-user-country="United Kingdom" data-user-interests="robots, spaceflight, football, movies" data-user-gender="Male" data-user-name="Johnny Depp" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://images2.fanpop.com/image/photos/12100000/young-adorable-Johnny-johnny-depp-12183502-300-400.jpg"></a></div></li>
						<li class="element even" data-user-country="United Kingdom" data-user-interests="spaceflight, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://www.maniacworld.com/angelina-jolie-cleavage.jpg"></a></div></li>
						<li class="element odd" data-user-country="United Kingdom" data-user-interests="football, movies" data-user-online="1" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://i144.photobucket.com/albums/r164/johnkb8/Babes/Hottie2-1-1.jpg"></a></div></li>
						<li class="element even" data-user-country="United Kingdom" data-user-interests="sex" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://blogs.sundaymercury.net/anorak-city/kelly-brook.jpg"></a></div></li>
						<li class="element odd" data-user-country="France" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://img.ibtimes.com/www/data/images/full/2012/04/23/266060-bollywood-actress-aishwarya-gestures-in-kolkata-in-2008.jpg"></a></div></li>
						<li class="element even" data-user-country="United Kingdom" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://www.rgbpicture.com/img/cool/wreslers/wreslers01.jpg"></a></div></li>
						<li class="element odd" data-user-country="United Kingdom" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://www.yee.ch/movies/L/LI/Lindsay_Lohan/Lindsay_Lohan_pussy.jpg"></a></div></li>
						<li class="element even" data-user-country="Japan" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://www.cinemavoyage.com/images/dianekruger2007062927685011.jpg"></a></div></li>
						<li class="element odd" data-user-country="United Kingdom" data-user-interests="football" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://www.top39.com/wp-content/uploads/2010/07/Lucy_Liu.jpg"></a></div></li>
						<li class="element even" data-user-country="United Kingdom" data-user-interests="spaceflight, sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://www.cinemavoyage.com/images/juliavothbitchslapmaxim200805.jpg"></a></div></li>
						<li class="element odd" data-user-country="United Kingdom" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://media.santabanta.com/newsite/cinemascope/feed/amrita-puri10.jpg"></a></div></li>
						<li class="element even" data-user-country="France" data-user-interests="sex, football, movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://img.ezinemark.com/imagemanager2/files/30004254/2011/10/2011-10-14-16-02-44-5-the-italian-actress-and-model-sara-tommasi-used-to.jpeg"></a></div></li>
						<li class="element odd" data-user-country="Spain" data-user-interests="movies" data-user-gender="Female" data-user-name="Jamie Pressley" data-user-id="34"><div class="avatar"><a href="template-profile.php"><img src="http://s1.moviefanfare.com/uploads/2010/09/scarlett-johansson1.jpg"></a></div></li>
					</ul>

					<a id="getMore" href="#">More Members</a>
				</div>
			</div>
			<!--<div id="googlebelt">
				<iframe width="100%" height="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.co.uk/maps?q=google+map+embed&amp;aq=f&amp;ie=UTF8&amp;hl=en&amp;t=m&amp;ll=51.451867,-0.182648&amp;spn=0.04279,0.617981&amp;z=11&amp;output=embed"></iframe>
			</div>-->

		</div><!--container-->


<!--include footer-->
<jsp:include page="WEB-INF/jsp/includes/footer.jsp" />
