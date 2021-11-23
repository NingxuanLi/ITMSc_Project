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
</head>
<body>
<table width="100%" height="734" border="0">
		<tr>
			<td colspan="2">
				<img src="img/top_01.jpg" width="1079" height="235" />
			</td>
		</tr>
		<tr>
			<td width="20%" height="366" align="left" valign="top">
				<!-- static include left.jsp -->
				<%@ include file="left.jsp"%>
			</td>
			<td width="80%" align="left" valign="top">
				<table width="819" height="55" border="0">
					<tr>
						<td width="133" height="15">
							&nbsp;
						</td>
						<td width="300" rowspan="2" align="center" valign="bottom">
							<span class="STYLE14 STYLE13">
								<span class="STYLE16">Patient Registration</span>
							</span>
						</td>
						<td width="548">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td height="13" bgcolor="#87CEFA">
							&nbsp;
						</td>
						<td height="13" bgcolor="#87CEFA">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td height="13" bgcolor="#FFFFFF">
							&nbsp;
						</td>
						<td align="center" valign="bottom">
							&nbsp;
						</td>
						<td height="13" bgcolor="#FFFFFF">
							&nbsp;
						</td>
					</tr>
				</table>
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
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>
</body>
</html>