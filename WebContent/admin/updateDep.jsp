<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
<script type="text/javascript">
function jumpBack(){

	location=department?method=showList;
}

</script>
</head>
<body>
<form action="department?method=modify&oldDepId=${department.depId}" method="post" name="form" >
		<div class="MainDiv">
				<table width="85%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							Department information update
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
														Department id:
													</td>
													<td>
														<input class="text" name="depId" value="${department.depId }"
															style="width: 154px;"  />
													</td>
												</tr>
												
												<tr>
													<td width="15%" align="right">
														Department name:
													</td>
													<td width="35%">
														<input class="text" name="depName" value="${department.depName }"
															style="width: 154px;" />
													</td>
												
												</tr>												
												
												<tr>
													<td colspan="2" style="color: red; font-size: 20;" width="15%" width="15%" align="right">
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