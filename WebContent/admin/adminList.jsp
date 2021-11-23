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

<script type="text/javascript">


//select all
function selectAll(){
	
	var ch=document.getElementsByName("delid");

	for(var i=0;i<ch.length;i++){
		ch[i].checked=true;
	}
	
}
//invert select
function unselectAll(){

	var ch=document.getElementsByName("delid");
	
	for(var i=0;i<ch.length;i++){
		ch[i].checked=!ch[i].checked;
		
	}
}

//delete
function del(){
	
	var adminIdStr="";
	var arr=document.getElementsByName("delid"); //checkbox 
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked){
			adminIdStr+=arr[i].value+","; 
		}	
	}

	location="admin?method=delete&adminIdArr="+adminIdStr;
}


//add
function link(){
 location="admin/addAdmin.jsp";

}



//search function
function checkAdmin(){
	var  name = document.getElementById("checkName").value;
	chName=name; 
	location = "admin?method=showList&checkName="+name;
}

		
//paging function 
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
				
		location = "admin?method=showList&currentPage="+currentPage+"&pageRows="+pageRows+"&checkName="+chName;
		return true;
	}else if(pageRows==""){
				
		alert("can't be null ");
		document.getElementById("pageRows").value=pages;
		return false;
		
	}else{
		alert("must > 0");
		document.getElementById("pageRows").value=pages;
		return false;

	}
}
			
		
 </script>

</head>
<body onload = "init()" >
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" >

								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										
										<td width="519">
											<label>
												Admin name:
												<input name="text" type="text" id="checkName" value="${checkName}" name="checkName" />
											</label>
											<input name="Submit" type="button" class="right-button02"
												value="search" onclick="checkAdmin()"/>
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
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="20">
											<span class="newfont07">select:<a href="javascript:selectAll();"
												class="right-font08" >select all</a>-<a
												href="javascript:unselectAll();" class="right-font08" >invert select</a>
											</span>
											<input name="Submit" type="button" class="right-button08"
												value="delete admin" onclick="del()"/>
											<input name="Submit" type="button" class="right-button08"
												value="add a new admin" onclick="link();" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">

												<tr>
													<th height="20" colspan="14" align="center"
														bgcolor="#EEEEEE" class="tablestyle_title">
														Admin List
													</th>
												</tr>
												<tr>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														select
													</td>
													<td width="12%" height="20" align="center"
														bgcolor="#EEEEEE">
														Admin name
													</td>
													<td width="7%" align="center" bgcolor="#EEEEEE">
														Admin password
													</td>
													<td width="11%" align="center" bgcolor="#EEEEEE">
														operation
													</td>
												</tr>
													<c:forEach items="${adminList}" var="adminList">
														<tr align="center">
															<td bgcolor="#FFFFFF">
																<!-- 复选框 delid-->
																<input type="checkbox" name="delid" value="${adminList.adminId}"/>
															</td>
															<td bgcolor="#FFFFFF">
																${adminList.adminName}
															</td>
															<td height="20" bgcolor="#FFFFFF">
																${adminList.adminPassword }
															</td>
										
															<td bgcolor="#FFFFFF">
																<a href="admin?method=gotoModify&adminId=${adminList.adminId}">edit</a>&nbsp;|&nbsp;
																<a href="admin?method=delete&adminId=${adminList.adminId}">delete</a>
															</td>
														</tr>
													</c:forEach>
												<tr align="center">
													<td colspan="4" bgcolor="#FFFFFF">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:red;">${page.rowsCount}</font> admins in total&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;<font style="color:red;">${page.pageCount}</font> pages&nbsp;&nbsp;&nbsp;&nbsp;
														<c:if test="${page.currentPage==1}">
															first page&nbsp;&nbsp;&nbsp;&nbsp;
															last page&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
														<c:if test="${page.currentPage!=1}">
															<a href="javascript:changeRows(1)">first page</a>&nbsp;&nbsp;&nbsp;&nbsp;
															<a href="javascript:changeRows(${page.currentPage-1})">last page</a>&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
														<c:if test="${page.currentPage eq page.pageCount}">
															next page&nbsp;&nbsp;&nbsp;&nbsp;
															end page&nbsp;&nbsp;&nbsp;&nbsp;															
														</c:if>
														<c:if test="${page.currentPage ne page.pageCount}">
															<a href="javascript:changeRows(${page.currentPage+1})">next page</a>&nbsp;&nbsp;&nbsp;&nbsp;
															<a href="javascript:changeRows(${page.pageCount})">end page</a>&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
														numbers shown per page<input type="text" id="pageRows" value="${page.pageRows}" onchange="changeRows(1)" size="6" style="color:red;"/>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														
														<select name="select" onchange="changeRows(this.value)">
															<option value="${page.currentPage}">page${page.currentPage}</option>
																<c:forEach begin="1" var="i" step="1" end="${page.pageCount}" >
																	<c:if test="${page.currentPage!=i}">
																		<option  value="${i}">${i }page</option>
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

</body>
</html>