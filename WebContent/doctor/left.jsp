<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<div class="layui-header">
    <div class="layui-logo layui-hide-xs layui-bg-black" >Doctor</div>

    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item layui-hide layui-show-md-inline-block">        
          Hello, Doctor ${sessionScope.doctor.docName}
      </li>
      <li class="layui-nav-item layui-hide-xs"><a href="doctor/index.jsp">back to index</a></li>
      <li class="layui-nav-item layui-hide-xs"><a href="doctor?method=logout">log out</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black" >
    <div class="layui-side-scroll">
      
      <ul class="layui-nav layui-nav-tree" lay-shrink="all" lay-filter="test">
        <li class="layui-nav-item ">
          <a class="" href="javascript:;">Appointment</a>
          <dl class="layui-nav-child">
            <dd><a href="appointment?method=showDoctorList">Appointment list</a></dd>          
          </dl>
        </li>            
      </ul>
    </div>
  </div>