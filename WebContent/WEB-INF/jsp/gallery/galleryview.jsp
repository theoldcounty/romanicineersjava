<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	//viewing users's uploaded images.
	
	
	<img src="C:\Users\robsl\Workspaces\MyEclipse 10\.metadata\.me_tcat\webapps\springApp21/images/Koala.jpg">
	
    ${images}
    
	<h2>list of images</h2>

	<table border="1">
		<c:forEach var="image" items="${images}">
			<tr>							
				<td>${image}</td>
			</tr>
		</c:forEach>   
	</table>    
    
</body>
</html>