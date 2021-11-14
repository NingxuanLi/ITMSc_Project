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
				<td bgcolor="#FFFFFF"></td>
			</tr>
			<tr>
				<td>
					<table width="768" height="500" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td valign="top">
								<img src="img/admin_right.jpg" width="800" height="600" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

</body>
</html>