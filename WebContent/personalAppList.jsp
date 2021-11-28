<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
<script src="layui/layui.js"></script>

<script>
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</head>
<body >
<div class="layui-layout layui-layout-admin">
  <%@ include file="left.jsp"%>
  
  
  <div class="layui-body" style="background-color: #F5F5F5">
    <table width="100%" border="1" cellpadding="4"
					cellspacing="1" bgcolor="#464646" class="newfont03" bordercolor="#DCDCDC">

					<tr>
						<th height="20" colspan="14" align="center"
							bgcolor="#EEEEEE" class="tablestyle_title">
							${patient.name},  Your appointments
						</th>
					</tr>
					<tr>
						
						<td width="10%" align="center" bgcolor="#EEEEEE">
							Department name
						</td>
						<td width="10%" height="40" align="center"
							bgcolor="#EEEEEE">
							Doctor name
						</td>
						<td width="10%" align="center" bgcolor="#EEEEEE">
							Appointment fee
						</td>
						
						<td width="10%" align="center" bgcolor="#EEEEEE">
							Date
						</td>
						<td width="10%" align="center" bgcolor="#EEEEEE">
							Status
						</td>
					</tr>
						<c:forEach items="${personalAppList}" var="pApp">
							<tr align="center">
								
								<td height="40" bgcolor="#FFFFFF">
									${pApp.depName}
								</td>
								<td  bgcolor="#FFFFFF">
									${pApp.docName}
								</td>
								<td  bgcolor="#FFFFFF">
									${pApp.money}
								</td>
								
								<td  bgcolor="#FFFFFF">
									${pApp.appTime}
								</td>
								<td  bgcolor="#FFFFFF">
									${pApp.appState}
								</td>								
							</tr>
						</c:forEach>						
				</table>    
  </div>
  
  <div class="layui-footer">
    <div align="right" style="color: red; font-size: small">&copy;Email:2528986L@student.gla.ac.uk</div>
  </div>
</div>
</body>
</html>