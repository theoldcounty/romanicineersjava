<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<head>
		<link href="css/generic.css" type="text/css" rel="stylesheet" />
		<link href="css/isotope.css" type="text/css" rel="stylesheet" />
		<link href="css/colorbox.css" type="text/css" rel="stylesheet" />

		<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />

		<title>test</title>
	</head>
	<body>
		<header>
			<div class="wrap">
				<div class="alignheader">
					<a href="/" title="Home" rel="home" id="logo"><img src="images/logo.png" alt="Home"></a>

					<nav id="loginmenu">
						<ul>
							<li><a class="form" href="register.php">Sign up</a></li>
							<li><a href="#">Login</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
		<div id="interactionpane">
			<div class="wrap">
				<nav id="returnhome">
					<ul>
						<li class="home"><a href="/romancineers/template-home.php">Home</a></li>
					</ul>
				</nav>
				<nav id="calltoaction">
					<ul>
						<li class="quiz"><a>Quiz</a></li>
						<li class="diagrams"><a>More Diagrams</a></li>
						<li class="filters"><a>Filters</a></li>
						<li class="search"><a>Search</a></li>
					</ul>
				</nav>
			</div>
		</div>
<!-- 

	${inSession}

	<c:choose>
	  <c:when test="${inSession != null}">
			<ul id="user" class="menu">
				<li><a href="logout">Logout</a></li>
			    <li><a href="edit">Edit</a></li>
			</ul>
	  </c:when>
	  <c:otherwise>
			<ul id="guest" class="menu">
				<li><a href="login">Login</a></li>
			    <li><a href="register">Register</a></li>
			</ul>
	  </c:otherwise>
	</c:choose>-->
