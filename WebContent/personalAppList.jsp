<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
						<td width="400" rowspan="2" align="center" valign="bottom">
							<span class="STYLE14 STYLE13">
								<span class="STYLE16">Appointment information</span>
							</span>
						</td>
						<td width=400">
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

				<table width="100%" border="0" cellpadding="4"
					cellspacing="1" bgcolor="#464646" class="newfont03">

					<tr>
						<th height="20" colspan="14" align="center"
							bgcolor="#EEEEEE" class="tablestyle_title">
							Your appointments
						</th>
					</tr>
					<tr>
						
						<td width="10%" align="center" bgcolor="#EEEEEE">
							Department name
						</td>
						<td width="7%" height="20" align="center"
							bgcolor="#EEEEEE">
							Doctor name
						</td>
						<td width="7%" align="center" bgcolor="#EEEEEE">
							Appointment fee
						</td>
						
						<td width="10%" align="center" bgcolor="#EEEEEE">
							Date
						</td>
						<td width="8%" align="center" bgcolor="#EEEEEE">
							Status
						</td>
					</tr>
						<c:forEach items="${perosonalAppList}" var="pApp">
							<tr align="center">
								
								<td height="20" bgcolor="#FFFFFF">
									${pApp.depName}
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${pApp.docName}
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${pApp.money}
								</td>
								
								<td height="20" bgcolor="#FFFFFF">
									${pApp.appTime}
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${pApp.appState}
								</td>								
							</tr>
						</c:forEach>					
				</table>

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