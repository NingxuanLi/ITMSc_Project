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
<script type="text/javascript">
layui.use('element', function(){
	  var element = layui.element;
	  
});


 

	
	function checkDocName(){
		var  name = document.getElementById("docName").value;
		chName=name;  
		location = "doctor?method=frontShowList&checkName="+name;
	}
	
	var pages;
	var chName;
	
	function init(){
		chName=document.getElementById("docName").value;
		pages = document.getElementById("pageRows").value; 
	}
	
	function changeRows(currentPage){
		document.getElementById("docName").value=chName;
		var pageRows=document.getElementById("pageRows").value;  
		if(pageRows>0){
					
			location = "doctor?method=frontShowList&currentPage="+currentPage+"&pageRows="+pageRows;
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
	
	function appointmentMake(docId){    
		location="doctor?method=appointmentMake&docId=" + docId;
	}
			
		
 </script>
</head>
<body onload="init()">
<div class="layui-layout layui-layout-admin">
  <%@ include file="left.jsp"%>
  
  
  <div class="layui-body" style="background-color: #F5F5F5">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>								
			<td width="519">
				<label>
					<span style="font-size: medium;">Doctor name:</span>
						<input name="text" type="text" id="docName" value="${checkName}" name="docName" />
					</label>
						<button type="button" class="layui-btn" onclick="checkDocName()">search</button>	
			</td>
			<td width="679" align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			</tr>
	</table>
    <table width="100%" border="1" cellpadding="4"
					cellspacing="1" bgcolor="#464646" class="newfont03" bordercolor="#DCDCDC">

					<tr>
						<th height="20" colspan="14" align="center"
							bgcolor="#EEEEEE" class="tablestyle_title">
							Doctor list
						</th>
					</tr>
					<tr>
						<td width="10%" height="40" align="center"
							bgcolor="#EEEEEE">
							Name
						</td>
						<td width="10%"  align="center" bgcolor="#EEEEEE">
							Department 
						</td>
						<td width="10%"  align="center"
							bgcolor="#EEEEEE">
							Available Appointment time
						</td>
						<td width="10%" align="center"  bgcolor="#EEEEEE">
							Appointment fee
						</td>
						<td width="10%" align="center" bgcolor="#EEEEEE">
							operation
						</td>
					</tr>
						<c:forEach items="${docDtoList}" var="doc">
							<tr align="center">
								<td bgcolor="#FFFFFF">
									${doc.docName}
								</td>
								<td height="40" bgcolor="#FFFFFF">
									${doc.depName}
								</td>
								<td  bgcolor="#FFFFFF">
									${doc.docTime}
								</td>
								<td  bgcolor="#FFFFFF">
									${doc.money}
								</td>
								<td bgcolor="#FFFFFF">
									<button type="button" class="layui-btn" onclick="appointmentMake(${doc.docId})">select</button>
								</td>
							</tr>
						</c:forEach>
					<tr align="center">
						<td colspan="6" bgcolor="#FFFFFF" height="50">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:red;">${page.rowsCount}</font> doctors in total&nbsp;&nbsp;&nbsp;&nbsp;
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
    
  </div>
  
  <div class="layui-footer">
    <div align="right" style="color: red; font-size: small">&copy;Email:2528986L@student.gla.ac.uk</div>
  </div>
</div>
</body>
</html>