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
    <form action="admin?method=add" method="post"  name="form">
			<div class="MainDiv">
				<table width="85%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							Add a new administrator
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
												A new Administrator
											</legend>
											<table border="0" cellpadding="2" cellspacing="1"
												style="width: 100%">

												
												<tr align="left">
													<td align="right">
														Admin name:
													</td>
													<td>
														<input class="text" name="adminName"
															style="width: 154px;"  />
													</td>
												</tr>
												
												<tr>
													<td width="15%" align="right">
														Admin password:
													</td>
													<td width="35%">
														<input class="text" name="adminPassword" 
															style="width: 154px;" />
													</td>
												
												</tr>
												
												<tr>
													 <td colspan="2" height="25" align="left" >
														<span style="color: red" >${error}</span>
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




					<tr>
						<TD colspan="2" align="center" height="50px">
							<input type="submit" name="Submit" value="add" class="button"/>

							<input type="reset" name="Submit2" value="reset" class="button"
								 />
						</TD>
					</tr>
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