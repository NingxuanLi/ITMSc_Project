<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>	
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

		function link(){    //点击添加医生 信息
			location="doctor?method=gotoAdd";
		}


		//删除所选医生信息 
		function del(){ 
			
			
			var strId="";
			var arr=document.getElementsByName("delid"); //复选框名字 
		
				for(var i=0;i<arr.length;i++){
					if(arr[i].checked){
						
						strId+=arr[i].value+","; //传过去 解析 	
					}
				}
				location="doctor?method=delete&strId="+strId;

		}


		
		function checkDocName(){
			var  name = document.getElementById("docName").value;
			chName=name; //查找姓名时不让清空 
			location = "doctor?method=showList&checkName="+name;
		}
		
		//改变文本框中的每页显示几行 的值 
		var pages;
		var chName;

		function init(){
			chName=document.getElementById("docName").value;//记录姓名 
			pages = document.getElementById("pageRows").value; //记录初始值 
		}


		 //改变每页显示多少行  --并且把当前页面传过去  
		function changeRows(currentPage){
			document.getElementById("docName").value=chName;//不让姓名清空 
			var pageRows=document.getElementById("pageRows").value;  //得到改变的值 
			if(pageRows>0){
				
				location = "doctor?method=showList&currentPage="+currentPage+"&pageRows="+pageRows+"&checkName="+chName;
				return true;
			}else if(pageRows==""){
				
				alert("can't be null ");
				document.getElementById("pageRows").value=pages;
				return false;
				
			}else{
				alert("must>0");
				document.getElementById("pageRows").value=pages;
				return false;

			}
		}
			
 </script>

</head>
<body onload="init()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" background="images/nav04.gif">

								<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<!-- <td width="24">
											<img src="images/ico07.gif" width="20" height="18" />
										</td> -->
										<td width="519">
											<label>
												Doctor name:
												<input name="text" type="text" id="docName" value="${checkName }" name="docName" />
											</label>
											<input name="Submit" type="button" class="right-button02"
												value="search" onclick="checkDocName()"/>
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
												class="right-font08">select all</a>-
												<a href="javascript:unselectAll();" class="right-font08" >invert select</a>
											</span>
											<input name="Submit" type="button" class="right-button08"
												value="delete doctor info" onclick="del();"/>
											<input name="Submit" type="button" class="right-button08"
												value="add a new doctor" onclick="link();" />
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
														Doctor list
													</th>
												</tr>
												<tr>
													<td width="8%" align="center" bgcolor="#EEEEEE">
														select
													</td>
													
													<td width="14%" height="20" align="center"
														bgcolor="#EEEEEE">
														Doctor name
													</td>
													
												    
													
													
													<td width="14%" align="center" bgcolor="#EEEEEE">
														Doctor department
													</td>
													<td width="14%" align="center" bgcolor="#EEEEEE">
														Doctor appointment date
													</td>
													<td width="14%" align="center" bgcolor="#EEEEEE">
														Doctor appointment fee
													</td>
												
													<td width="14%" align="center" bgcolor="#EEEEEE">
														operation
													</td>
												</tr>
													<c:forEach items="${docDtoList}" var="doc">
														<tr align="center">
														<td bgcolor="#FFFFFF">
															<input type="checkbox" name="delid" value="${doc.docId}"/>
														</td>
														<td bgcolor="#FFFFFF" height="20">
															<c:if test="${doc.color eq 'red'}">
																<span style="color:red">${doc.docName }</span>
															</c:if>
															<c:if test="${doc.color eq 'black'}">
																${doc.docName }
															</c:if>
														</td>
														
														<td height="20" bgcolor="#FFFFFF">
															${doc.depName}
														</td>
														<td height="20" bgcolor="#FFFFFF">
															${doc.docTime }
														</td>
														<td height="20" bgcolor="#FFFFFF">
															${doc.money }
														</td>
														
														<td bgcolor="#FFFFFF">
															<a href="doctor?method=gotoModify&docId=${doc.docId}">edit</a>&nbsp;|&nbsp;
															<a href="doctor?method=delete&docId=${doc.docId}">delete</a>
														</td>
													</tr>
													</c:forEach>
												<tr align="center">
													<td colspan="7" bgcolor="#FFFFFF">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-family:隶书;font-size:small;color:red;">${page.rowsCount}</font>doctors in total&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;<font style="font-family:隶书;font-size:small;color:red;">${page.pageCount}</font>pages&nbsp;&nbsp;&nbsp;&nbsp;
														<c:if test="${page.currentPage==1}">
															first page&nbsp;&nbsp;&nbsp;&nbsp;
															last page&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
														<c:if test="${page.currentPage!=1}">
															<a href="javascript:changeRows(1)">first page</a>&nbsp;&nbsp;&nbsp;&nbsp;
															<a href="javascript:changeRows(${page.currentPage-1})">last page</a>&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
														<c:if test="${page.currentPage==page.pageCount}">
															next page&nbsp;&nbsp;&nbsp;&nbsp;
															end page&nbsp;&nbsp;&nbsp;&nbsp;															
														</c:if>
														<c:if test="${page.currentPage!=page.pageCount}">
															<a href="javascript:changeRows(${page.currentPage+1})">next page</a>&nbsp;&nbsp;&nbsp;&nbsp;
															<a href="javascript:changeRows(${page.pageCount})">end page</a>&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
														numbers shown per page <input type="text" id="pageRows" value="${page.pageRows}" onchange="changeRows(1)" size="6" style="font-family:隶书;font-size:x-small;color:red;"/>
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
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="6">
								<img src="images/spacer.gif" width="1" height="1" />
							</td>
						</tr>
						<tr>
							<td height="33">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

</body>
</html>