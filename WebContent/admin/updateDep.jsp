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
		<input type="hidden" name="method" value="update">
		<input type="hidden" name="id" value="${user.userId}" > 
			<div class="MainDiv">
				<table width="99%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							Department information edit
						</th>
					</tr>
					<tr>
						<td class="CPanel">
							<table border="0" cellpadding="0" cellspacing="0"
								style="width: 100%">
		
								<TR>
									<TD width="100%">
										<fieldset style="height: 100%;">
											<legend>
												Department information
											</legend>

											<br />
										</fieldset>
									</TD>
								</TR>
							</TABLE>
							<table border="0" cellpadding="2" cellspacing="1"
								style="width: 100%">

								<tr>
									<td nowrap align="right" width="11%">
										Department id:
									</td>
									<td width="27%">
										<input name='depId' type="text" class="text"
											style="width: 154px" value="${department.depId}" />
										<span class="red">*</span>
									</td>

									<td align="right" width="25%">
										&nbsp;
									</td>
									<td width="37%">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
								</tr>
								<tr>
									<td width="11%" align="right" nowrap>
										Department name:
									</td>
									<td width="27%">
										<input name='depName' type="text" class="text"
											style="width: 154px" value="${department.depName}" />
										<span class="red">*</span>
									</td>

									<td align="right" width="25%">
										&nbsp;
									</td>
									
								</tr>
								<tr>&nbsp;</tr>
								<tr>&nbsp;</tr>
								
							</table>
							<table>
								<TR>
									<TD colspan="2" align="center" height="50px">
										<input type="submit" name="Submit" value="save" class="button"
											/>

										<input type="reset" name="Submit2" value="reset" class="button"							 />
									</TD>
								</TR>
							</TABLE>
							</td>
							</tr>
							</table>
		</form>

</body>
</html>