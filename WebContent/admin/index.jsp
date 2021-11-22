<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
  <frameset rows="59,*" cols="*" frameborder="no" border="0" framespacing="0">
   	<frame src="admin/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  		<frameset cols="213,*" frameborder="no" border="0" framespacing="0">
  			<frame src="admin/left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
  			<frame src="admin/mainframe.jsp" name="mainFrame" id="mainFrame" title="mainFrame" />
  		</frameset>
  </frameset>
<body>



</body>
</html>