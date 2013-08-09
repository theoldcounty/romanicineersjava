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
