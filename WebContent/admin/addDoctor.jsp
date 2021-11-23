<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String message = (String)request.getAttribute("message");
	if(!"null".equals(message)&&!"".equals(message)&&message!=null){
		out.print("<script>alert('"+message+"');</script>");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />

<script type="text/javascript">
	window.onload=function(){
		var date = new Date();
		var str=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
		console.log(str);
		document.getElementById("docTime").value=str;
	}
	
</script>

</head>
<body >
<form action="doctor?method=add" method="post"  name="form">
			<div class="MainDiv">
				<table width="85%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							Add a new doctor
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
												A new doctor
											</legend>
											<table border="0" cellpadding="2" cellspacing="1"
												style="width: 100%">

												
												<tr align="left">
													<td align="right">
														Doctor name:
													</td>
													<td>
														<input class="text" name="docName"
															style="width: 154px;"  />
													</td>
												</tr>
												<tr align="left">
													<td align="right">
														Doctor password:
													</td>
													<td>
														<input class="text" name="docPassword"
															style="width: 154px;"  />
													</td>
												</tr>
												<tr>
													<td width="15%" align="right">
														Doctor department
													</td>
													<td width="35%">
														<select class="text" name="depId"
															style="width: 154px;"  >
															<c:if test="${not empty depList}">
												     			<c:forEach var="dep" items="${depList}">
												     				<option value="${dep.depId}">${dep.depName}</option>
												     			</c:forEach>
												     		</c:if>
														</select>
													</td>
												</tr>
												<tr>
													<td width="15%" align="right">
														Doctor appointment fee
													</td>
													<td width="35%">
														<input class="text" name="money" value="0"
															style="width: 154px;" />
													</td>
												
												</tr>
												
												<tr>
													<td width="15%" align="right">
														Doctor app date:
													</td>
													<td width="35%">
														<input class="text" id="docTime" name="docTime" type="text" value="xxxx-xx-xx"
															style="width: 154px;" />
													</td>
												</tr>
												<tr>
													<td colspan="2"  style="color: red; font-size: 20;" width="15%" align="left" >
														${error }
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
							<input type="submit" name="Submit" value="add" class="button"/>

							<input type="reset" name="Submit2" value="reset" class="button"
								 />
						</TD>
					</TR>
				</TABLE>

			</div>
		</form>
</body>
</html>