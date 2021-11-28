<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		
//paging function
var pages;
		
function init(){ 
	pages = document.getElementById("pageRows").value;  //initial value
}

  
function changeRows(currentPage){
	var pageRows=document.getElementById("pageRows").value;   //changed value
	if(pageRows>0){	
		location = "appointment?method=showList&currentPage="+currentPage+"&pageRows="+pageRows;
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

function approve(id){
	if(confirm("Approve this appointment?")){
		location.href = "appointment?method=approve&appId="+id;
	}else{
		return false;
	}
}

function disapprove(id){
	if(confirm("Disapprove this appointment?")){
		location.href = "appointment?method=disapprove&appId="+id;
	}else{
		return false;
	}
}

function approveAll(){	
	var strId="";
	var arr=document.getElementsByName("delid");  
	
		for(var i=0;i<arr.length;i++){
			if(arr[i].checked){	
				strId+=arr[i].value+",";  	
			}
		}
	location="appointment?method=approve&strId="+strId;
	
}

function disapproveAll(){
	var strId="";
	var arr=document.getElementsByName("delid");  	
		for(var i=0;i<arr.length;i++){
			if(arr[i].checked){	
				strId+=arr[i].value+",";  	
			}
		}
	location="appointment?method=disapprove&strId="+strId;
}
		
 </script>
</head>
<body onload="init{}" >
<div class="layui-layout layui-layout-admin">
  <%@ include file="/admin/adminLeft.jsp"%>
  
  
  <div class="layui-body" style="background-color: #F5F5F5">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			
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
												<button type="button" class="layui-btn layui-btn-normal" onclick="approveAll()">approve all</button>
												<button type="button" class="layui-btn layui-btn-danger" onclick="disapproveAll()">disapprove all</button>
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
														Appointments to be processed
													</th>
												</tr>
												<tr>
													<td width="10%"  height="40" align="center" bgcolor="#EEEEEE">
														select
													</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														Patient
													</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														Doctor
													</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														Department
													</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">
														Date
													</td>
													<td  width="10%" align="center" bgcolor="#EEEEEE">
														operation
													</td>
												</tr>
													<c:forEach items="${appList}" var="app">
														<tr align="center">
															<td  height="20" bgcolor="#FFFFFF">
																
																<input type="checkbox" name="delid" value="${app.appId}"/>
															</td>													
															<td bgcolor="#FFFFFF">
																${app.pName}
															</td>
															<td bgcolor="#FFFFFF">
																${app.docName}
															</td>
															<td bgcolor="#FFFFFF">
																${app.depName}
															</td>
															<td bgcolor="#FFFFFF">
																${app.appTime}
															</td>
															<td bgcolor="#FFFFFF">
																<button type="button" class="layui-btn" onclick="approve(${app.appId})">approve</button>
																<button type="button" class="layui-btn layui-btn-danger" onclick="disapprove(${app.appId})">disapprove</button>
																
															</td>
															
															
														</tr>
													</c:forEach>
												<tr align="center">
													<td colspan="6" bgcolor="#FFFFFF" height="50">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:red;">${page.rowsCount}</font> appointments in total&nbsp;&nbsp;&nbsp;&nbsp;
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