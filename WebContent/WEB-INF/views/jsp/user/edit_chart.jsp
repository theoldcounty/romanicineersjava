<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--include header-->

<div id="colorboxWrapper" class="regular">
		<div id="formType" data-form-type="doughnutcharts" data-chart-type="<%=request.getParameter("type")%>"></div>
		
		<form id="chartForm" action="edit_chart" enctype="multipart/form-data" method="post" action="">
			
			<fieldset id="step4">


				<label>Please indicate 5 of your interest and their importance from 0 to 100</label>
				
				<div id="holderCharts">
					<fieldset class="doughnutWrap">
						<select name="interests">
							<option value="Cinema">Cinema</option>
							<option value="Clubbing">Clubbing</option>
							<option value="Football">Football</option>
							<option value="Baseball">Baseball</option>					
						</select>
						<fieldset data-fieldname="interestknobs" id="knob1" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="5" data-pie-size="85"></fieldset>
					</fieldset>
					
					<fieldset class="doughnutWrap">
						<select name="interests">
							<option value="Interest 02">Interest 02</option>				
						</select>
						<fieldset data-fieldname="interestknobs" id="knob2" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="15" data-pie-size="85"></fieldset>
					</fieldset>
							
					<fieldset class="doughnutWrap">	
						<select name="interests">
							<option value="Interest 03">Interest 03</option>				
						</select>
						<fieldset data-fieldname="interestknobs" id="knob3" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="45" data-pie-size="85"></fieldset>
					</fieldset>
					
					<fieldset class="doughnutWrap">
						<select name="interests">
							<option value="Interest 04">Interest 04</option>				
						</select>
						<fieldset data-fieldname="interestknobs" id="knob4" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="85" data-pie-size="85"></fieldset>
					</fieldset>		
								
					<fieldset class="doughnutWrap">		
						<select name="interests">
							<option value="Interest 05">Interest 05</option>				
						</select>
						<fieldset data-fieldname="interestknobs" id="knob5" class="knob" data-color="#E2B227" data-role="doughnut-knob" data-value="35" data-pie-size="85"></fieldset>		
					</fieldset>
				</div>
						
				
				<span class="error"></span>
				<input type="hidden" value="submitted" name="submitted"/>
				<input type="submit" value="submit" name="submit"/>				
			
			</fieldset>
		</form>
		
		</div>
</div>


		<!--register.jsp-->
		${message}
		<!--register.jsp-->