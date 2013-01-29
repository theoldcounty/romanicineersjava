<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<html>
 <body>
  <h2 align="center">Spring 2.5 MVC File Upload Example</h2>
  <hr width=600 >
  <p align="center">
   <img src="${filepath}" alt="Upload Image" />
   <br>
   <core:out value="File Name :: "></core:out>
   <core:out value="${fileName}"></core:out>
   <core:out value="-Uploaded Successfully."></core:out>
  </p>
 </body>
</html>