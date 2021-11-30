<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  
<div class="layui-header">
    <div class="layui-logo layui-hide-xs layui-bg-black" >Easy Appointment</div>
    

    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item layui-hide-xs"><a href="doctor_login.jsp"><i class="layui-icon layui-icon-friends" style="font-size: 15px"></i>&nbsp;Doctor login</a></li>
      <li class="layui-nav-item layui-hide-xs"><a href="admin_login.jsp"><i class="layui-icon layui-icon-friends" style="font-size: 15px"></i>&nbsp;Admin login</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      
      <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <li class="layui-nav-item"><a href="index.jsp">Information</a></li>
        <li class="layui-nav-item"><a href="patient_registration.jsp">Patient registration</a></li>
        <li class="layui-nav-item"><a href="doctor?method=frontShowList">Doctor list</a></li>
        <li class="layui-nav-item"><a href="department?method=frontShowList">Make an appointment</a></li>
        <li class="layui-nav-item"><a href="appointment_query.jsp">Appointment query</a></li>
      </ul>
    </div>
  </div>
   

