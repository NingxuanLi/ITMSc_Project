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
</head>
<body class = "ContentBody">
		<form action="doctor?method=modify&docId=${doctorDto.docId}" method="post"   name="form">
			<div class="MainDiv">
				<table width="85%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							Doctor information update
						</th>
					</tr>
					<tr>
						<td class="CPanel">

							<table border="0" cellpadding="0" cellspacing="0" 
								style="width: 60%">
								<tr>
		
								</tr>
								<tr align="center">
									<td class="TablePanel">
										&nbsp;
									</td>
								</tr>
								<TR>
									<TD width="100%">
										<fieldset style="height: 100%;">
											<legend>
												Doctor update:
											</legend>
											<table border="0" cellpadding="2" cellspacing="1"
												style="width: 100%">

												
												<tr align="left">
													<td align="right">
														Doctor name:
													</td>
													<td>
														<input class="text" name="docName" value="${doctorDto.docName }"
															style="width: 154px;"  />
													</td>
												</tr>
												<tr>
													<td width="15%" align="right">
														Doctor department:
													</td>
													<td width="35%">
														<select class="text" name="depId"
															style="width: 154px;"  >
															<c:if test="${not empty depList}">
												     			<c:forEach var="dep" items="${depList}">
												     				<c:if test="${dep.depName eq doctorDto.depName}">
												     					<option value="${dep.depId}" selected="selected">${dep.depName}</option>
												     				</c:if>
												     				<c:if test="${dep.depName ne doctorDto.depName}">
												     					<option value="${dep.depId}" >${dep.depName}</option>
												     				</c:if>
												     			</c:forEach>
												     		</c:if>
														</select>
													</td>
												</tr>
												<tr>
													<td width="15%" align="right">
														Appointment fee:
													</td>
													<td width="35%">
														<input class="text" name="money" value="${doctorDto.money }"
															style="width: 154px;" />
													</td>
												
												</tr>
												
												<tr>
													<td width="15%" align="right">
														Appointment date:
													</td>
													<td width="35%">
														<input class="text" name="docTime" type="text" value="${doctorDto.docTime }"
															style="width: 154px;" />
													</td>
												</tr>
												
												
											</table>
											<br />
										</fieldset>
									</TD>
								</TR>
							</TABLE>


						</td>
					</tr>




					<TR>
						<TD colspan="2" align="center" height="50px">
							<input type="submit" name="Submit" value="submit" class="button"/>

							<input type="reset" name="Submit2" value="reset" class="button"
								 />
						</TD>
					</TR>
				</TABLE>

			</div>
		</form>


</body>
</html>