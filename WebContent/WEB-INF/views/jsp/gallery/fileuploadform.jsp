<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<script language="JavaScript">
function Validate(){
  var image =document.getElementById("file").value;
  if(image!=''){
	var checkimg = image.toLowerCase();
	if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.gif|\.GIF|\.jpeg|\.JPEG)$/)){
	 alert("Please enter Image File Extensions .jpg,.png,.jpeg,.gif");
	 document.getElementById("file").focus();
	 return false;
	}
  }
  return true;
 }
</script>
</head>
<body>
 <h2 align="center">Spring 2.5 MVC File Upload Example</h2>
 <hr width=600 >
 <form:form method="POST" commandName="fileUpload" enctype="multipart/form-data" onsubmit="return Validate();">
 <table align="center" >
  <tr>
   <td><form:label path="file">Please Select File:</form:label></td>
   <td><input type="file" name="file" id="file" /></td>
  </tr>
  <tr>
   <td></td>
   <td><font color="red"><form:errors path="file"  /></font></td>
  </tr>
  <tr>
   <td></td>
   <td><input type="submit" value="File Upload" /></td>
  </tr>
 </table>
 </form:form>
</body>
</html>