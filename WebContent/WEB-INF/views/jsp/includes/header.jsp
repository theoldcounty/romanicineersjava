<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<head>
		<jsp:include page="styles.jsp" />
		<title>Romaninceers</title>
	</head>
	${message}
	<body class="${page}">
		<header>
			<div class="wrap">
				<div class="alignheader">
					<a href="/" title="Home" rel="home" id="logo"><img src="resources/images/logo.png" alt="Home"></a>
					<nav id="loginmenu">
						<c:choose>
						  <c:when test="${inSession != null}">
								<ul>
								    <li><a class="form" href="logout">Logout</a></li>
									<li><a class="form" href="edit">Edit</a></li>
								</ul>
						  </c:when>
						  <c:otherwise>
								<ul>
									<li><a class="form" href="showRegisterUser">Sign up</a></li>
									<li><a class="form" href="login">Login</a></li>
								</ul>
						  </c:otherwise>
						</c:choose>
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
<!-- ${inSession}-->