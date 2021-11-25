<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
<link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
<script src="layui/layui.js"></script>
<script >
function link(){

	location="patient_registration.jsp";
}

layui.use('element', function(){
	  var element = layui.element;
	  
});
</script>

</head>
<body>
<div class="layui-layout layui-layout-admin">
  <%@ include file="/admin/adminLeft.jsp"%>
  
  
  <div class="layui-body">
    <form action="appointment?method=add&docId=${dto.docId }" method="post">
			
				

						<table width="80%" border="0" cellpadding="4" cellspacing="1"
							bgcolor="#464646" class="newfont03">

							<tr>
								<th height="20" colspan="5" align="center" bgcolor="#EEEEEE"
									class="tablestyle_title">
									Doctor information
								</th>
							</tr>
							<tr>

								<td width="12%" height="20" align="center" bgcolor="#EEEEEE">
									Name
								</td>
								<td width="12%" align="center" bgcolor="#EEEEEE">
									Department
								</td>
								<td width="12%" align="center" bgcolor="#EEEEEE">
									Appointment date
								</td>
								<td width="12%" align="center" bgcolor="#EEEEEE">
									Appointment fee
								</td>


							</tr>

							<tr align="center">

								<td bgcolor="#FFFFFF" height="20">
									${dto.docName }
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${dto.depName}
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${dto.docTime }
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${dto.money }
								</td>

							</tr>

						</table>
						<table width="448" height="204" border="0" align="left">
							<tr>
								<td width="162">
									&nbsp;
								</td>
								<td colspan="2">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="login-text02">
									Name:
								</td>
								<td colspan="2">
									<input type="text" id="name" name="name" height="20" />
								</td>
							</tr>
							<tr>
								<td class="login-text02">
									Password:
								</td>
								<td colspan="2">
									<input type="text" id="password" name="password" height="20" />
								</td>
							</tr>
							<tr>
								<td colspan="3"><span style="align:center;color:red" align="center">${error }</span></td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<input type="submit" name="Submit" class="right-button01" value="appoint" />
									&nbsp;&nbsp;
									<input type="button" name="button2" class="right-button01" value="register" onclick="link()"/>
								</td>

							</tr>
						</table>


		</form>
    
  </div>
  
  <div class="layui-footer">
    <div align="right" style="color: red; font-size: small">&copy;Email:2528986L@student.gla.ac.uk</div>
  </div>
</div>

</body>
</html>