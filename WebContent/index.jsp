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
<link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
<script src="layui/layui.js"></script>

<script>
layui.use('element', function(){
  var element = layui.element;
  
});
</script>

</head>
<body>
<div class="layui-layout layui-layout-admin">
  <%@ include file="left.jsp"%>
  
  
  <div class="layui-body" style="background-color: #F5F5F5">
      <p class="STYLE32">★ <span class="STYLE41">A brief introduction to this web-app "Easy Appointment":</span></p>
      <p><span class="STYLE40">"Easy Appointment" is an hospital online appointment system designed for patients specifically. <br/>
      With this web-app patient can make an appointment with doctor conveniently.</span></p>
      <p class="STYLE32">★ <span class="STYLE41">How to make an appointment with doctor:</span></p>
      <p><span class="STYLE40">1. Please do registration first.</span></p>
      <p><span class="STYLE40">2. There are two ways to make appointments. You can click the "make an appointment" button and select a department. <br/>
      After that, doctors in this department will be listed in a form, and you just need to select one of them. <br/>
      Enter you user name and password to completed the process.</span></p>
      <p><span class="STYLE40">3. You can also click the "Doctor list" button to check all the doctors to directly select a doctor you want.</span></p>
      <p><span class="STYLE40">4. You can use our search button to find what you want quickly.</span></p>
      <p class="STYLE32">★ <span class="STYLE41">How to check your appointment status?</span></p>
      <p class="STYLE40">1. There are three status of your appointment. a)haven't been processed yet, b)approved, c)disapproved</p>
      <p class="STYLE40">2. You can click the "Appointment query" button check your appointment status.</p>
      <p class="STYLE40">3. Only when your appointment status is "approved", you can come to hospital to see the doctor</p>
      <p class="STYLE40"><span class="STYLE42">★<span class="STYLE41"> How to cancel your appointment?</span></span></p>
      <p class="STYLE38">Please call: 123456789, our administrator will cancel the appointment for you.</p>
      <p class="STYLE38"><span class="STYLE42">★</span> <span class="STYLE43">Announcements </span></p>
      <p class="STYLE39">1. patient management tel number: 123456789</p>
      <p class="STYLE39">2. xxxxxxxx.</p>
      <p class="STYLE39">3. xxxxxxxx.</p>
      <p class="STYLE39">4. xxxxxxxx.</p>
      <p class="STYLE39">&nbsp;</p>
    <p class="STYLE30"></p>      <p>&nbsp;</p>
    
  </div>
  
  <div class="layui-footer">
    <div align="right" style="color: red; font-size: small">&copy;Email:2528986L@student.gla.ac.uk</div>
  </div>
</div>

<%-- <table width="100%" height="734" border="0">
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
</table> --%>

</body>
</html>