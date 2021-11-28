<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrator login</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
<script src="layui/layui.js"></script>

<script language="javascript" >
layui.use(['element', 'form'], function(){
	  var element = layui.element;
	  var form = layui.form;
});
	
function changeCode(img){
debugger;
	var d = new Date();
	img.src = "image?t="+d.getTime();		
}
</script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <%@ include file="left.jsp"%>
  
  
  <div class="layui-body" style="background-color: #F5F5F5">
    <form action="admin?method=adminLogin" method="post">
		<div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px" >Admin name:</label>
            <div class="layui-input-block">
            	<input type="text" name="adminName"  class="layui-input" style="width: 300px">
    		</div>
        </div>
        <div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px">Password:</label>
            <div class="layui-input-block">
            	<input type="password" name="password"  class="layui-input" style="width: 300px">
    		</div>
        </div>
        <div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px">Captcha code:</label>
            <div class="layui-input-block">
            	<img src="image" alt="no picture" onclick="changeCode(this)" />
    		</div>
        </div>
        <div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px">Captcha:</label>
            <div class="layui-input-block">
            	<input type="text" name="code"  class="layui-input" style="width: 300px">
    		</div>
        </div>
        <div align="left" style="color: red; font-size: 20; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${error }</div>       
        <div class="layui-form-item">
    		<div class="layui-input-block">
      			<button type="submit" class="layui-btn" >login</button>
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