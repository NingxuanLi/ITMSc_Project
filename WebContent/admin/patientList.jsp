<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String message = (String)request.getAttribute("message");
	if(!"null".equals(message)&&!"".equals(message)&&message!=null){
		out.print("<script>alert('"+message+"');</script>");
	}
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

<script type="text/javascript">
layui.use('element', function(){
	  var element = layui.element;
	  
});


function selectAll(){
	
	var ch=document.getElementsByName("delid");

	for(var i=0;i<ch.length;i++){
		ch[i].checked=true;
	}
	
}

function unselectAll(){

	var ch=document.getElementsByName("delid");
	
	for(var i=0;i<ch.length;i++){
		ch[i].checked=!ch[i].checked;
		
	}
}


function del(){
	
	var strId="";
	var arr=document.getElementsByName("delid");
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked){
			strId+=arr[i].value+","; 
		}	
	}
	
	location="patient?method=delete&strId="+strId;
}

function singleDel(pId){
	 location="patient?method=delete&pId="+pId;

}

 
function checkPatientName(){
	var  name = document.getElementById("checkName").value;
	chName=name; 
	location = "patient?method=showList&checkName="+name;
}

		

var pages;
var chName;
		
function init(){
	chName=document.getElementById("checkName").value; 
	pages = document.getElementById("pageRows").value;  
}

  
function changeRows(currentPage){
	document.getElementById("checkName").value=chName;
	var pageRows=document.getElementById("pageRows").value; 
	if(pageRows>0){
				
		location = "patient?method=showList&currentPage="+currentPage+"&pageRows="+pageRows+"&checkName="+chName;
		return true;
	}else if(pageRows==""){
				
		alert("can't be null ");
		document.getElementById("pageRows").value=pages;
		return false;
				
	}else{
		alert("must >0");
		document.getElementById("pageRows").value=pages;
	    return false;

	}
}					
 </script>
</head>
<body onload="init()">
<div class="layui-layout layui-layout-admin">
  <%@ include file="/admin/adminLeft.jsp"%>
  
  
  <div class="layui-body" style="background-color: #F5F5F5">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" >

								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										
										<td width="519">
											<label>
												<span style="font-size: medium;">Doctor name:</span>
												<input name="text" type="text" id="checkName" value="${checkName}" name="checkName" />
												
											</label>
											<button type="button" class="layui-btn" onclick="checkPatientName()">search</button>
										</td>
										<td width="679" align="left">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table id="subtree1" style="DISPLAY: " width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="20">
											<div class="layui-btn-group">
												<button type="button" class="layui-btn" onclick="selectAll()">select all</button>
												<button type="button" class="layui-btn" onclick="unselectAll()">invert select</button>
											</div>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<div class="layui-btn-group">												
												<button type="button" class="layui-btn layui-btn-danger" onclick="del()">delete patient</button>
											</div>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="1" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03" bordercolor="#DCDCDC">

												<tr>
													<th height="20" colspan="14" align="center"
														bgcolor="#EEEEEE" class="tablestyle_title">
														Patient list
													</th>
												</tr>
												<tr>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														select
													</td>
													<td width="10%" height="40" align="center"
														bgcolor="#EEEEEE">
														Patient name
													</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														Patient real name
													</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														Patient gender
													</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														Patient Tel number
													</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														Patient BRP number
													</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														operation
													</td>
												</tr>
													<c:forEach items="${patientList}" var="p">
														<tr align="center">
															<td bgcolor="#FFFFFF">
																
																<input type="checkbox" name="delid" value="${p.id}"/>
															</td>
															
															<td height="40" bgcolor="#FFFFFF">
																<c:if test="${p.color eq 'red'}">
																<span style="color:red">${p.name }</span>
															</c:if>
															<c:if test="${p.color eq 'black'}">
																${p.name }
															</c:if>
															</td>
															<td bgcolor="#FFFFFF">
																${p.realName}
															</td>
															<td bgcolor="#FFFFFF">
																${p.gender}
															</td>
															<td bgcolor="#FFFFFF">
																${p.tel}
															</td>
															<td bgcolor="#FFFFFF">
																${p.brp}
															</td>
										
															<td bgcolor="#FFFFFF">
																<button type="button" class="layui-btn layui-btn-danger" onclick="singleDel(${p.id})">delete</button>
															</td>
														</tr>
													</c:forEach>
												<tr align="center">
													<td colspan="7" bgcolor="#FFFFFF" height="50">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:red;">${page.rowsCount}</font> patients in total&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;<font style="color:red;">${page.pageCount}</font> pages&nbsp;&nbsp;&nbsp;&nbsp;
														<c:if test="${page.currentPage==1}">
															<i class="layui-icon layui-icon-prev"></i>&nbsp;&nbsp;&nbsp;&nbsp;
															<i class="layui-icon layui-icon-left" style="font-size: 18px"></i>&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
														<c:if test="${page.currentPage!=1}">
															<a href="javascript:changeRows(1)" class="layui-icon layui-icon-prev" style="color: #0000FF"></a> &nbsp;&nbsp;&nbsp;&nbsp;
															<a href="javascript:changeRows(${page.currentPage-1})" class="layui-icon layui-icon-left" style="color: #0000FF; font-size: 18px"></a> &nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
														<c:if test="${page.currentPage eq page.pageCount}">
															<i class="layui-icon layui-icon-right" style="font-size: 18px"></i>&nbsp;&nbsp;&nbsp;&nbsp;
															<i class="layui-icon layui-icon-next"></i>&nbsp;&nbsp;&nbsp;&nbsp;															
														</c:if>
														<c:if test="${page.currentPage ne page.pageCount}">
															<a href="javascript:changeRows(${page.currentPage+1})" class="layui-icon layui-icon-right" style="color: #0000FF; font-size: 18px"></a> &nbsp;&nbsp;&nbsp;&nbsp;
															<a href="javascript:changeRows(${page.pageCount})" class="layui-icon layui-icon-next" style="color: #0000FF"></a> &nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
														numbers shown per page <input type="text" id="pageRows" value="${page.pageRows}" onchange="changeRows(1)" size="6" style="color:red;"/>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														
														<select name="select" onchange="changeRows(this.value)">
															<option value="${page.currentPage}">page${page.currentPage}</option>
																<c:forEach begin="1" var="i" step="1" end="${page.pageCount}" >
																	<c:if test="${page.currentPage!=i}">
																		<option  value="${i}">page${i }</option>
																	</c:if>
																</c:forEach>
														</select>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<div align="right" style = "color: red; font-size: small">You can't delete a patient(in color red) who has already made an appointment</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
    
  </div>
  
  <div class="layui-footer">
    <div align="right" style="color: red; font-size: small">&copy;Email:2528986L@student.gla.ac.uk</div>
  </div>
</div>

</body>
</html>