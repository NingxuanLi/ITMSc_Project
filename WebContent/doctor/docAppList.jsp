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
<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					
				</td>
			</tr>
			<tr>
				<td>
					<table id="subtree1" style="DISPLAY: " width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">

												<tr>
													<th height="20" colspan="14" align="center"
														bgcolor="#EEEEEE" class="tablestyle_title">
														Doctor ${sessionScope.doctor.docName}, your Appointments
													</th>
												</tr>
												<tr>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														Patient name
													</td>
													<td width="7%" align="center" bgcolor="#EEEEEE">
														Patient real name
													</td>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														Patient gender
													</td>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														Patient tel
													</td>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														patient BRP
													</td>
													<td width="12%" align="center" bgcolor="#EEEEEE">
														Appointment time
													</td>
													
												</tr>
													<c:forEach items="${docAppList}" var="app">
														<tr align="center">																											
															<td bgcolor="#FFFFFF">
																${app.pName}
															</td>
															<td bgcolor="#FFFFFF">
																${app.pRealName}
															</td>
															<td bgcolor="#FFFFFF">
																${app.pGender}
															</td>
															<td bgcolor="#FFFFFF">
																${app.pTelNum}
															</td>
															<td bgcolor="#FFFFFF">
																${app.pBrpNum}
															</td>
															<td bgcolor="#FFFFFF">
																${app.appTime}
															</td>
															
														</tr>
													</c:forEach>
												
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="6">
								<img src="images/spacer.gif" width="1" height="1" />
							</td>
						</tr>
						<tr>
							<td height="33">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>