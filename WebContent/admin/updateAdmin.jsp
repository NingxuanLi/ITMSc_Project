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
<link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
<script src="layui/layui.js"></script>

<script>
layui.use('element', function(){
  var element = layui.element;
  
});
</script>

</head>

<body >
<div class="layui-layout layui-layout-admin">
  <%@ include file="/admin/adminLeft.jsp"%>
  
  
  <div class="layui-body">
    <form action="admin?method=modify&adminId=${admin.adminId}" method="post"   name="form">
			<div class="MainDiv">
				<table width="85%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							Admin information update
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
												Admin update:
											</legend>
											<table border="0" cellpadding="2" cellspacing="1"
												style="width: 100%">

												
												<tr align="left">
													<td align="right">
														Admin name:
													</td>
													<td>
														<input class="text" name="adminName" value="${admin.adminName }"
															style="width: 154px;"  />
													</td>
												</tr>
												
												<tr>
													<td width="15%" align="right">
														Admin password:
													</td>
													<td width="35%">
														<input class="text" name="adminPassword" value="${admin.adminPassword }"
															style="width: 154px;" />
													</td>
												
												</tr>
												<tr>
													<td style="color: red; font-size: 20;" colspan="2" width="15%" align="left" >
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
  </div>
  
  <div class="layui-footer">
    <div align="right" style="color: red; font-size: small">&copy;Email:2528986L@student.gla.ac.uk</div>
  </div>
</div>

</body>
</html>