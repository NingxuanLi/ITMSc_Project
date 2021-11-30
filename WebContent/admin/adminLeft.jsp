<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  


<div class="layui-header">
    <div class="layui-logo layui-hide-xs layui-bg-black" >Admin</div>
    

    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item layui-hide layui-show-md-inline-block">        
          
          Hello, Administrator ${sessionScope.admin.adminName}
      </li>
      <li class="layui-nav-item layui-hide-xs"><a href="admin/index.jsp"><i class="layui-icon layui-icon-home" style="font-size: 15px"></i>&nbsp;back to index</a></li>
      <li class="layui-nav-item layui-hide-xs"><a href="admin?method=logout"><i class="layui-icon layui-icon-logout" style="font-size: 15px"></i>&nbsp;log out</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black" >
    <div class="layui-side-scroll">
      
      <ul class="layui-nav layui-nav-tree" lay-shrink="all" lay-filter="test">
        <li class="layui-nav-item ">
          <a class="" href="javascript:;">Admin management</a>
          <dl class="layui-nav-child">
            <dd><a href="admin/addAdmin.jsp">add admin</a></dd>
            <dd><a href="admin?method=showList">admin list</a></dd>           
          </dl>
        </li>
        <li class="layui-nav-item ">
          <a class="" href="javascript:;">Doctor management</a>
          <dl class="layui-nav-child">
            <dd><a href="doctor?method=gotoAdd">add doctor</a></dd>
            <dd><a href="doctor?method=showList">doctor list</a></dd>           
          </dl>
        </li>
        <li class="layui-nav-item ">
          <a class="" href="javascript:;">Department management</a>
          <dl class="layui-nav-child">
            <dd><a href="admin/addDep.jsp">add department</a></dd>
            <dd><a href="department?method=showList">department list</a></dd>           
          </dl>
        </li>
        <li class="layui-nav-item ">
          <a class="" href="javascript:;">Patient management</a>
          <dl class="layui-nav-child">
            <dd><a href="patient?method=showList">patient list</a></dd>           
          </dl>
        </li>
        <li class="layui-nav-item ">
          <a class="" href="javascript:;">Appointment management</a>
          <dl class="layui-nav-child">
            <dd><a href="appointment?method=showList">pending appointments</a></dd>
            <dd><a href="appointment?method=showArchiveList">archive appointments</a></dd>           
          </dl>
        </li>
              
      </ul>
    </div>
  </div>


