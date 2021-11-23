<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="60" >
	    <table width="99%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="50%"><img src="img/doctor_top.jpg" width="300" height="60" border="0" /></td>
	        <td width="50%" align="right" style="font-size:12px;vertical-align:top;">&copy;Email:2528986L@student.gla.ac.uk</td>
	      </tr>
	    </table>
    </td>
  </tr>
</table>

</body>
</html>