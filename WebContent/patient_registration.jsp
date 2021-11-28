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
<title>Patient registration</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
<script src="layui/layui.js"></script>

<script>
layui.use(['element', 'form'], function(){
  var element = layui.element;
  var form = layui.form;
  
});
</script>

</head>
<body>
<div class="layui-layout layui-layout-admin">
  <%@ include file="left.jsp"%>
  
  
  <div class="layui-body" style="background-color: #F5F5F5">
    <form action="patient?method=add" method="post">
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
        <div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px">Confirm password:</label>
            <div class="layui-input-block">
            	<input type="password" name="confirmPassword"  class="layui-input" style="width: 300px">
    		</div>
        </div>
        <div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px">Real name:</label>
            <div class="layui-input-block">
            	<input type="text" name="realName"  class="layui-input" style="width: 300px">
    		</div>
        </div>
        <div class="layui-form-item">
    		<label class="layui-form-label"style="width: 120px">Gender:</label>
    		<div class="layui-input-block">
      			<input type="radio" name="gender" value="male" /><span class = "login-text02" style="font-size:large;"><i class="layui-icon layui-icon-male" style="font-size: 15px"></i>&nbsp;male</span> &nbsp;
      			<input type="radio" name="gender" value="female" /><span class = "login-text02" style="font-size:large;"><i class="layui-icon layui-icon-female" style="font-size: 15px"></i>&nbsp;female</span>
    		</div>
  		</div>
  		<div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px">Tel number:</label>
            <div class="layui-input-block">
            	<input type="text" name="telNumber"  class="layui-input" style="width: 300px">
    		</div>
        </div>
        <div class="layui-form-item">
    		<label class="layui-form-label" style="width: 120px">BRP number:</label>
            <div class="layui-input-block">
            	<input type="text" name="brpNumber"  class="layui-input" style="width: 300px">
    		</div>
        </div>
    	<div align="left" style="color: red; font-size: 20; align-items: flex-end;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${error }</div>       
        <div class="layui-form-item">
    		<div class="layui-input-block">
      			<button type="submit" class="layui-btn">submit</button>
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