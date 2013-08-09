<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<head>
		<jsp:include page="styles.jsp" />
		<title>Romaninceers</title>

	</head>
	<body class="${page}">
		<header>
			<div class="wrap">
				<div class="alignheader">
					<a href="/springApp21/" title="Home" rel="home" id="logo"><img src="resources/images/logo.png" alt="Home"></a>
					<nav id="loginmenu">
						<c:choose>
						  <c:when test="${inSession}">
								<ul>
									<li><a class="fancyboxtrigger fancybox.ajax form" href="edit">Edit</a></li>
									<li><a class="" href="logout">Logout (${personName})</a></li>
								</ul>
						  </c:when>
						  <c:otherwise>
								<ul>
									<li><a class="fancyboxtrigger fancybox.ajax form" href="register">Sign up</a></li>
									<li><a class="fancyboxtrigger fancybox.ajax form" href="login">Login</a></li>
								</ul>
						  </c:otherwise>
						</c:choose>
					</nav>
				</div>
			</div>
		</header>
		<div id="interactionpane">
			<div class="wrap">
				<ul id="returnhome">
					<li class="home"><a href="/springApp21"><div class="icon"><img src="resources/images/icon_home.png"></div>Home</a></li>
				</ul>
				<ul id="calltoaction">
					<li class="quiz"><a>Quiz</a></li>
					<li class="filters"><a><div class="icon"><img src="resources/images/icon_filters.png"></div>Filters</a></li>
				</ul>
			</div>
		</div>
<!-- ${inSession}-->

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

