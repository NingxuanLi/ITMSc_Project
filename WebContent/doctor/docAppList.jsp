<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript">
		
//paging function
var pages;
		
function init(){ 
	pages = document.getElementById("pageRows").value; 
}


function changeRows(currentPage){
	var pageRows=document.getElementById("pageRows").value;  

	if(pageRows>0){	
		
		location = "appointment?method=showDoctorList&currentPage="+currentPage+"&pageRows="+pageRows;
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
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					
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
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">

												<tr>
													<th height="20" colspan="14" align="center"
														bgcolor="#EEEEEE" class="tablestyle_title">
														Doctor ${sessionScope.doctor.docName}, your Appointments
													</th>
												</tr>
												<tr>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														Patient name
													</td>
													<td width="7%" align="center" bgcolor="#EEEEEE">
														Patient real name
													</td>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														Patient gender
													</td>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														Patient tel
													</td>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														patient BRP
													</td>
													<td width="12%" align="center" bgcolor="#EEEEEE">
														Appointment time
													</td>
													
												</tr>
													<c:forEach items="${docAppList}" var="app">
														<tr align="center">																											
															<td bgcolor="#FFFFFF">
																${app.pName}
															</td>
															<td bgcolor="#FFFFFF">
																${app.pRealName}
															</td>
															<td bgcolor="#FFFFFF">
																${app.pGender}
															</td>
															<td bgcolor="#FFFFFF">
																${app.pTelNum}
															</td>
															<td bgcolor="#FFFFFF">
																${app.pBrpNum}
															</td>
															<td bgcolor="#FFFFFF">
																${app.appTime}
															</td>
															
														</tr>
													</c:forEach>
												<tr align="center">
													<td colspan="7" bgcolor="#FFFFFF">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-size:small;color:red;">${page.rowsCount}</font>appointments to be processed&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;<font style="font-size:small;color:red;">${page.pageCount}</font> pages&nbsp;&nbsp;&nbsp;&nbsp;
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
														numbers shown per page <input type="text" id="pageRows" value="${page.pageRows}" onchange="changeRows(1)" size="6" style="font-size:x-small;color:red;"/>
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
</body>
</html>