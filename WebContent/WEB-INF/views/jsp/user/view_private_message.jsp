<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="shazam-wrapper" class="regular">
		<div class="privateMessages">
			
			
			${privatemessages}
			


			  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
			  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
			  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
			  <link rel="stylesheet" href="/resources/demos/style.css" />
			  <script>
			  $(function() {
			    $( "#tabs" ).tabs({
			      collapsible: true
			    });
			  });
			  
				$(document).ready(function() {
				    console.log( "ready!" );
				    


$(".list .messageData .subject a").click(function() {
	console.log("Handler for .click() called.");
});

/*
.specificPrivateMessage .userData .name

.specificPrivateMessage .userData .date


.specificPrivateMessage .messageData .subject
.specificPrivateMessage .messageData .message
*/







				    
				});			  
			  
			  </script>
  			
  			
  			<style>
  				.ui-tabs .ui-tabs-panel {
  					overflow:hidden;
  				}
  				
  				.privateMessages ul{
  					margin:0;
  					padding:0;
				}
  				
  				.privateMessages ul li{
  					overflow:hidden;
					list-style-type: none;
					width: 100%;
					float: left;
					margin-bottom: 6px;
					border-top: 1px solid black;  					
  				}
				
  				
  				.image{
					background: red;
					width: 50px;
					height: 50px;
					float: left;
  				}
  				
  				.userData{
  					float: left;
  				}
  				
  				.messageData{
  					float: left;
  				}
  				
  				.pagination{
  					
  				}
  				
  				.pagination li{
					width: auto!important;
					border: none!important;
					width: 20px!important;
					height: 20px!important;
					margin-right: 5px;
					background: grey;
					float: left;
					text-align: center;			
  				}
  			</style>
  			
			<div id="tabs">
				<ul>
					<li><a href="#inbox">Inbox</a></li>
					<li><a href="#sent">Sent</a></li>
				</ul>
				<div id="inbox" class="privatemessages">
					<div class="list">
						<ul>
							<c:forEach items="${privatemessages.inbox}" varStatus="loop">
								<li>
									<div class="image">Img</div>
									<div class="userData">
										<div class="name">Sandy Mccain ${privatemessages.inbox[loop.index].senderUid}</div>
										<div class="date"><!-- July 23rd 2012 at 1:34p.m. --> ${privatemessages.inbox[loop.index].date.ts}</div>								
									</div>
									<div class="messageData">
										<div class="subject"><a href="#">TEST SUBJECT -- ${privatemessages.inbox[loop.index].subject}</a></div>
										<div class="message">${privatemessages.inbox[loop.index].message}</div>								
									</div>							
								</li>
							</c:forEach>
						</ul>
						
						<ul class="pagination">
							<li>1</li>
							<li>2</li>
						</ul>
					
					</div>
					
					<div class="specificPrivateMessage">
						<div class="userData">
							<div class="name">Sandy Mccain</div>
							<div class="date">July 23rd 2012 at 1:34p.m.</div>								
						</div>
						<div class="messageData">
							<div class="subject">title</div>
							<div class="message">lorep ipsum der time asda asd  asd asd as da sdsada</div>								
						</div>
					</div>
				</div>
				<div id="sent" class="privatemessages">
					<ul>
						<c:forEach items="${privatemessages.sent}" varStatus="loop">
							<li>
								<div class="image">Img</div>
								<div class="userData">
									<div class="name">Mel Mccain ${privatemessages.inbox[loop.index].senderUid}</div>
									<div class="date">July 23rd 2012 at 1:34p.m.  ${privatemessages.inbox[loop.index].date.ts}</div>								
								</div>
								<div class="messageData">
									<div class="subject">${privatemessages.inbox[loop.index].subject}</div>
									<div class="message">${privatemessages.inbox[loop.index].message}</div>								
								</div>							
							</li>
						</c:forEach>
					</ul>
					
					<ul class="pagination">
						<li>1</li>
						<li>2</li>
					</ul>									
				</div>
			</div>

		
		</div>
		
		<div class="error"></div>
</div>