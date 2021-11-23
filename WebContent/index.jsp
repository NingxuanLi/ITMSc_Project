<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String message = (String)request.getAttribute("message");
if(!"null".equals(message)&&!"".equals(message)&&message!=null){
	out.print("<script>alert('"+message+"');</script>");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Index</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />

</head>
<body>

<table width="100%" height="734" border="0">
  <tr>
    <td colspan="2"><img src="img/top_01.jpg" width="1079" height="200" /></td>
  </tr>
  <tr>
    <td width="20%" height="366" align="left" valign="top">
    <!-- static include left.jsp file -->
    	<%@ include file="left.jsp"%>
    </td>
    <td width="80%" align="left" valign="top">
    <table width="819" height="55" border="0">
      <tr>
        <td width="133" height="15">&nbsp;</td>
        <td width="500" rowspan="2" align="center" valign="bottom"><span class="STYLE13"><span class="STYLE16">Appointment information</span></span></td>
        <td width="548">&nbsp;</td>
      </tr>
      <tr>
        <td height="13" bgcolor="#87CEFA">&nbsp;</td>
        <td height="13" bgcolor="#87CEFA">&nbsp;</td>
      </tr>
      <tr>
        <td height="13" bgcolor="#FFFFFF">&nbsp;</td>
        <td align="center" valign="bottom">&nbsp;</td>
        <td height="13" bgcolor="#FFFFFF">&nbsp;</td>
      </tr>
    </table>
      <p class="STYLE32">★ <span class="STYLE41">Appointment process flow:</span></p>
      <p><span class="STYLE40">1. Please do registration first.</span></p>
      <p><span class="STYLE40">2. Please choose a department to do further operation.</span></p>
      <p><span class="STYLE40">3. You can also check the doctor list to choose the doctor to make an appointment.</span></p>
      <p><span class="STYLE40">4. please arrive hospital on time.</span></p>
      <p class="STYLE32">★ <span class="STYLE41">How to make sure that you have successfully made an appointment?</span></p>
      <p class="STYLE40"> You can use the "Appointment query" function to check your appointment status.</p>
      <p class="STYLE40"><span class="STYLE42">★<span class="STYLE41"> How to cancel your appointment?</span></span></p>
      <p class="STYLE38">Please call: 123456789, our administrator will cancel the appointment for you.</p>
      <p class="STYLE38"><span class="STYLE42">★</span> <span class="STYLE43">Announcements </span></p>
      <p class="STYLE39">1. patient management tel number: 123456789</p>
      <p class="STYLE39">2. xxxxxxxx.</p>
      <p class="STYLE39">3. xxxxxxxx.</p>
      <p class="STYLE39">4. xxxxxxxx.</p>
      <p class="STYLE39">&nbsp;</p>
    <p class="STYLE30"></p>      <p>&nbsp;</p>
    </td>
  </tr>
</table>

</body>
</html>