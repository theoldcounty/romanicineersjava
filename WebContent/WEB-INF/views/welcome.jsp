<jsp:include page="jsp/includes/header.jsp" />
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
							<div class="avatar">
								<img src="http://3.bp.blogspot.com/-nPkfVayQelQ/TlVBikw7SpI/AAAAAAAABFU/xii61syZwP8/s400/Jessica+Alba+iPhone+Wallpaper-11.jpg">
							</div>
							<div class="goals">
								<div class="head"></div>
								<div class="heart"></div>
								<div class="hand"></div>
								<div class="hotspothead"></div>
								<div class="hotspotheart"></div>
								<div class="hotspothand"></div>
							</div>
						</div>
						<div class="biospec">
							<h2>Jean Midel</h2>
							<div class="piechart">
								<div id="biometricPie"></div>
								<div class="nodata">No data</div>
							</div>
							<div class="gender"></div>
						</div>
					</div>
				</div>
				<div class="fullprofile"><div class="placement"><a href="#">See full profile</a></div></div>
				<nav class="icons">
					<ul>
						<li class="myphotos"><a href="#">My Photos</a> <span>0</span></li>
						<li class="followers"><a href="#">Followers</a> <span>0</span></li>
						<li class="followme"><a href="#">Follow me</a></li>
						<li class="talktome"><a href="#">Talk to me</a></li>
					</ul>
				<nav>
			</div>
			<div id="contents">
				<div class="wrap">

					<jsp:include page="jsp/includes/filters.jsp" />


					<h2>TESTWelcome on visual dating, find love, in a visual way</h2>

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


					<ul class="users" data-filter-users="true"></ul>

					<a id="getMore" href="#">More Members</a>
				</div>
			</div>
			<!--<div id="googlebelt">
				<iframe width="100%" height="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.co.uk/maps?q=google+map+embed&amp;aq=f&amp;ie=UTF8&amp;hl=en&amp;t=m&amp;ll=51.451867,-0.182648&amp;spn=0.04279,0.617981&amp;z=11&amp;output=embed"></iframe>
			</div>-->

		</div><!--container-->


<!--include footer-->
<jsp:include page="jsp/includes/footer.jsp" />
