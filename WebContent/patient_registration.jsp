<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient registration</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
<script src="layui/layui.js"></script>

</head>
<body>
<div class="layui-layout layui-layout-admin">
  <%@ include file="left.jsp"%>
  
  
  <div class="layui-body">
    <form action="patient?method=add" method="post">

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="20%" height="35" class="login-text02">
								Name:
								<br />
							</td>
							<td width="80%">
								<input name="name" type="text" size="30" />
							</td>
						</tr>
						<tr>
							<td height="35" class="login-text02">
								Password:
								<br />
							</td>
							<td>
								<input name="password" type="password" size="30" />
							</td>
						</tr>
						<tr>
							<td height="35" class="login-text02">
								Confirm password:
								<br />
							</td>
							<td>
								<input name="confirmPassword" type="password" size="30" />
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" class="login-text02">
								Real name:
								<br />
							</td>
							<td width="80%">
								<input name="realName" type="text" size="30" />
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" class="login-text02">
								Gender:
								<br />
							</td>
							<td width="80%">
								<input type="radio" name="gender" value="male" /><span class = "login-text02">male</span> &nbsp;
								<input type="radio" name="gender" value="female" /><span class = "login-text02">female</span>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" class="login-text02">
								Tel number:
								<br />
							</td>
							<td width="80%">
								<input name="telNumber" type="text" size="30" />
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" class="login-text02">
								BRP number:
								<br />
							</td>
							<td width="80%">
								<input name="brpNumber" type="text" size="30" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td style="color: red; font-size: 20;">${error }</td>

						</tr>

						<tr>
							<td height="35">
								&nbsp;
							</td>
							<td>
								<input name="button" type="submit" class="right-button01" value="submit" />
								<input name="reset" type="reset" class="right-button02" value="reset" />
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