<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="shazam-wrapper" class="regular">
		<div class="privateMessages">
			
			
			${privatemessages}
			
			<ul>
				<c:forEach items="${privatemessages}" varStatus="loop">
					<li>${privatemessages[loop.index].message}</li>
				</c:forEach>
			
			
				<li><a href="#">message 1</a></li>
				<li><a href="#">message 2</a></li>
				<li><a href="#">message 3</a></li>
				<li><a href="#">message 4</a></li>
			</ul>
		
		</div>
		
		<div class="error"></div>
</div>