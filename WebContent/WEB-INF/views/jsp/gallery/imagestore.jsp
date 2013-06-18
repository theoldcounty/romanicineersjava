<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--include header-->

<div id="colorboxWrapper" class="regular">
	<h1>Visual Dating</h1>

	<div class="imageForm">						
		${message}				
		<form id="imageForm" action="uploadimage" method="post" enctype="multipart/form-data">
			<label>userId</label>
		    <input type="text" name="userId" />
		   <br/>
		    <label>image</label>			
		    <input type="file" name="image" />
			<br/>
			
			<input type="hidden" value="submitted" name="submitted"/>
			<input type="submit" value="submit" name="submit"/>
		</form>
		<!--imageupload.jsp-->
	</div>
</div>