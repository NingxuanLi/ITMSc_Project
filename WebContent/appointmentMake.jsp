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
<link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
<script src="layui/layui.js"></script>
<script >
function link(){

	location="patient_registration.jsp";
}

layui.use('element', function(){
	  var element = layui.element;
	  
});
</script>

</head>
<body>
<div class="layui-layout layui-layout-admin">
  <%@ include file="left.jsp"%>
  
  
  <div class="layui-body" style="background-color: #F5F5F5">
    <form action="appointment?method=add&docId=${dto.docId }" method="post">
			
				

						<table width="100%" border="1" cellpadding="4" cellspacing="1"
							bgcolor="#464646" class="newfont03" bordercolor="#DCDCDC">

							<tr>
								<th height="20" colspan="5" align="center" bgcolor="#EEEEEE"
									class="tablestyle_title">
									Doctor information
								</th>
							</tr>
							<tr>

								<td width="12%" height="40" align="center" bgcolor="#EEEEEE">
									Name
								</td>
								<td width="12%" align="center" bgcolor="#EEEEEE">
									Department
								</td>
								<td width="12%" align="center" bgcolor="#EEEEEE">
									Appointment date
								</td>
								<td width="12%" align="center" bgcolor="#EEEEEE">
									Appointment fee
								</td>


							</tr>

							<tr align="center">

								<td bgcolor="#FFFFFF" height="40">
									${dto.docName }
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${dto.depName}
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${dto.docTime }
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${dto.money }
								</td>

							</tr>

						</table>
		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		
		<div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px" >Name:</label>
            <div class="layui-input-block">
            	<input type="text" name="name"  class="layui-input" style="width: 300px">
    		</div>
        </div>
        <div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px">Password:</label>
            <div class="layui-input-block">
            	<input type="password" name="password"  class="layui-input" style="width: 300px">
    		</div>
        </div>
        <div align="left" style="color: red; font-size: 20; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${error }</div>       
        <div class="layui-form-item">
    		<div class="layui-input-block">
      			<button type="submit" class="layui-btn" >appoint</button>
      			<button type="reset" class="layui-btn layui-btn-primary">reset</button>
    		</div>
  		</div>						
		</form>
    
  </div>
  
  <div class="layui-footer">
    <div align="right" style="color: red; font-size: small">&copy;Email:2528986L@student.gla.ac.uk</div>
  </div>
</div>

</body>
</html>