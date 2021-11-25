<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  
<div class="layui-header">
    <div class="layui-logo layui-hide-xs layui-bg-black" >Welcome</div>
    <!-- 头部区域（可配合layui 已有的水平导航） -->

    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item layui-hide-xs"><a href="doctor_login.jsp">Doctor login</a></li>
      <li class="layui-nav-item layui-hide-xs"><a href="admin_login.jsp">Admin login</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <li class="layui-nav-item"><a href="index.jsp">Information</a></li>
        <li class="layui-nav-item"><a href="patient_registration.jsp">Patient registration</a></li>
        <li class="layui-nav-item"><a href="doctor?method=frontShowList">Doctor list</a></li>
        <li class="layui-nav-item"><a href="department?method=frontShowList">Make an appointment</a></li>
        <li class="layui-nav-item"><a href="appointment_query.jsp">Appointment query</a></li>
      </ul>
    </div>
  </div>
    <!-- <table width="254" height="230" border="1" cellpadding="0" cellspacing="0">
      <tr>
        <td align="center"><span class="STYLE12"><a href="index.jsp" style = "text-decoration:none">Appointment Information</a></span></td>
      </tr>
      
      <tr>
        <td align="center"><span class="STYLE12"><a href="patient_registration.jsp" style = "text-decoration:none" >Patient registration </a></span></td>
      </tr>
      <tr>
        <td align="center"><span class="STYLE12"><a href="department?method=frontShowList" style = "text-decoration:none">Make an appointment</a></span></td>
      </tr>
      <tr>
        <td align="center"><span class="STYLE12"><a href="appointment_query.jsp" style = "text-decoration:none">Appointment query</a></span></td>
      </tr>
      <tr>
        <td align="center"><span class="STYLE12"><a href="doctor?method=frontShowList" style = "text-decoration:none">Doctor list</a></span></td>
      </tr>
      <tr>
        <td align="center"><span class="STYLE12"><a href="doctor_login.jsp" style = "text-decoration:none">Doctor login</a></span></td>
      </tr>
      <tr>
        <td align="center"><span class="STYLE12"><a href="admin_login.jsp" style = "text-decoration:none">Administrator login</a></span></td>
      </tr>
      
    </table>
	<table width="253" height="280" border="0">
        <tr>
          <td align="left" valign="top"><img src="img/left_01.jpg" width="243" height="270" /></td>
        </tr>
      </table> -->

