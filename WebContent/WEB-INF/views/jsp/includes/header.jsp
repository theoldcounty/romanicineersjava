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
									<li><a class="shazam form" href="edit">Edit</a></li>
									<li><a class="" href="logout">Logout (${personName})</a></li>
								</ul>
						  </c:when>
						  <c:otherwise>
								<ul>
									<li><a class="shazam form" href="register">Sign up</a></li>
									<li><a class="shazam form" href="login">Login</a></li>									
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
				<ul id="calltoaction"><!-- 
					<li class="quiz"><a>Quiz</a></li>
					<li class="diagrams"><a>More Diagrams</a></li>-->
					<li class="filters"><a><div class="icon"><img src="resources/images/icon_filters.png"></div>Filters</a></li>
					<li class="search"><a><div class="icon"><img src="resources/images/icon_search.png"></div>Search</a></li>
					<li class="advanced_search"><a><div class="icon"><img src="resources/images/icon_search.png"></div>Advanced Search</a></li>
				</ul>
			</div>
		</div>		
<!-- ${inSession}-->