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
<script type="text/javascript">		

var pages;

		
function init(){
	
	pages = document.getElementById("pageRows").value; 

 
function changeRows(currentPage){

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
		
 </script>
</head>
<body>
<table width="100%" height="734" border="0">
			<tr>
			<td colspan="2">
				<img src="img/top_01.jpg" width="1079" height="235" />
			</td>
		</tr>
		<tr>
			<td width="20%" height="366" align="left" valign="top">
				<!-- static include left.jsp -->
				<%@ include file="left.jsp"%>
			</td>
			<td width="80%" align="left" valign="top">
				<table width="819" height="55" border="0">
					<tr>
						<td width="133" height="15">
							&nbsp;
						</td>
						<td width="400" rowspan="2" align="center" valign="bottom">
							<span class="STYLE14 STYLE13">
								<span class="STYLE16">Please select a doctor</span>
							</span>
						</td>
						<td width=400">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td height="13" bgcolor="#87CEFA">
							&nbsp;
						</td>
						<td height="13" bgcolor="#87CEFA">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td height="13" bgcolor="#FFFFFF">
							&nbsp;
						</td>
						<td align="center" valign="bottom">
							&nbsp;
						</td>
						<td height="13" bgcolor="#FFFFFF">
							&nbsp;
						</td>
					</tr>
				</table>

				<table width="100%" border="0" cellpadding="4"
					cellspacing="1" bgcolor="#464646" class="newfont03">

					<tr>
						<th height="20" colspan="14" align="center"
							bgcolor="#EEEEEE" class="tablestyle_title">
							Doctor list
						</th>
					</tr>
					<tr>
						<td width="7%" height="20" align="center"
							bgcolor="#EEEEEE">
							Name
						</td>
						<td width="7%" align="center" bgcolor="#EEEEEE">
							Department 
						</td>
						<td width="15%" height="20" align="center"
							bgcolor="#EEEEEE">
							Available Appointment time
						</td>
						<td width="11%" align="center" bgcolor="#EEEEEE">
							Appointment fee
						</td>
						<td width="11%" align="center" bgcolor="#EEEEEE">
							operation
						</td>
					</tr>
						<c:forEach items="${docDtoList}" var="doc">
							<tr align="center">
								<td bgcolor="#FFFFFF">
									${doc.docName}
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${doc.depName}
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${doc.docTime}
								</td>
								<td height="20" bgcolor="#FFFFFF">
									${doc.money}
								</td>
								<td bgcolor="#FFFFFF">
									<a href="doctor?method=appointmentMake&docId=${doc.docId}">select</a>
								</td>
							</tr>
						</c:forEach>
					<tr align="center">
						<td colspan="5" bgcolor="#FFFFFF">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-size:small;color:red;">${page.rowsCount}</font>departments in total&nbsp;&nbsp;&nbsp;&nbsp;
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
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
</body>
</html>