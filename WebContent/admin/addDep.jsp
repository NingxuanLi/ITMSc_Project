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
<script type="text/javascript">
function change(){

	var va=document.getElementById("depId").value;
	location=department?method=add&dep=dva;
}

</script>
</head>
<body class="ContentBody">
<form action="department?method=add" method="post"  name="form">
			<div class="MainDiv">
				<table width="85%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							Add a new department
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
												department information:
											</legend>
											<table border="0" cellpadding="2" cellspacing="1"
												style="width: 100%">

												
												<tr align="left">
													<td align="right">
														department id:
													</td>
													<td>
														<input class="text" name="depId" id="depId"
															style="width: 154px;"  />
													</td>
												</tr>
												
												<tr>
													<td width="15%" align="right">
														department name:
													</td>
													<td width="35%">
														<input class="text" name="depName" id="depName"
															style="width: 154px;" />
													</td>
												
												</tr>
												<tr>
													<td width="15%" align="center" colspan="2" style="color: red">
														${error}
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
							<input type="submit" name="Submit" value="add" class="button" onclick="change()"/>

							<input type="reset" name="Submit2" value="reset" class="button" />
								
						</TD>
					</TR>
				</TABLE>

			</div>
		</form>

</body>
</html>