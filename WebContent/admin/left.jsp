<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
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

<SCRIPT language=JavaScript>
function picture(idt){
    var nametu="xiaotu"+idt;
    var tp = document.getElementById(nametu);
    tp.src="images/ico05.gif";
	
	for(var i=1;i<30;i++)
	{
	  
	  var nametu2="xiaotu"+i;
	  if(i!=idt*1)
	  {
	    var tp2=document.getElementById('xiaotu'+i);
		if(tp2!=undefined)
	    {tp2.src="images/ico06.gif";}
	  }
	}
}

function list(idstr){
	var name1="subtree"+idstr;
	var name2="img"+idstr;
	var objectobj=document.all(name1);
	var imgobj=document.all(name2);


	
	if(objectobj.style.display=="none"){
		for(i=1;i<10;i++){
			var name3="img"+i;
			var name="subtree"+i;
			var o=document.all(name);
			if(o!=undefined){
				o.style.display="none";
				var image=document.all(name3);
				image.src="images/ico04.gif";
			}
		}
		objectobj.style.display="";
		imgobj.src="images/ico03.gif";
	}
	else{
		objectobj.style.display="none";
		imgobj.src="images/ico04.gif";
	}
}

</SCRIPT>

</head>
<body>
<table width="198" border="0" cellpadding="0" cellspacing="0" class="left-table01">
  <tr>
    <TD>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="207" height="55" background="images/nav01.gif">
				<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
					
					<td width="75%" height="22" class="left-font01">Hello, <span class="left-font02">${sessionScope.admin.adminName}</span></td>
				  </tr>
				  <tr>
					<td height="22" class="left-font01">
						[<a href="admin?method=logout" target="_top" class="left-font01">log out</a>]
						[<a href="admin/index.jsp" target="_top" class="left-font01">back to index</a>]
						</td>
				  </tr>
				</table>
			</td>
		  </tr>
		</table>
			  <!-- administrator management -->
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29"><table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="8%"><img name="img5" id="img5" src="images/ico04.gif" width="8" height="11" /></td>
                  <td width="92%"><a href="javascript:" target="mainFrame" class="left-font03" onClick="list('5');">Admin management</a></td>
                </tr>
            </table></td>
          </tr>
      </table>

	  <table id="subtree5" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
        <tr>
          <td width="9%" height="20"><img id="xiaotu13" src="images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="admin/addAdmin.jsp" target="mainFrame" class="left-font03" onClick="picture('13');">add admin</a></td>
        </tr>
        <tr>
          <td height="20"><img id="xiaotu14" src="images/ico06.gif" width="8" height="12" /></td>
          <td><a href="admin?method=showList" target="mainFrame" class="left-font03" onClick="picture('14');">admin list</a></td>
        </tr>
      </table>
		
		<!--  doctor management    -->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img8" id="img8" src="images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('8');" >Doctor management</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		<table id="subtree8" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu20" src="images/ico06.gif" width="8" height="12" /></td>

				  <td width="91%"><a href="doctor?method=gotoAdd" target="mainFrame" class="left-font03" onClick="picture('20');">add doctor</a></td>
				</tr>
				<tr>
				  <td width="9%" height="21" ><img id="xiaotu21" src="images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="doctor?method=showList" target="mainFrame" class="left-font03" onClick="picture('21');">doctor list</a></td>
				</tr>
				
      </table>
      
      
		<!--  patient management    -->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img7" id="img7" src="images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('7');" >Patient management</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		<table id="subtree7" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
				
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu18" src="images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
					<a href="patient?method=showList" target="mainFrame" class="left-font03" onClick="picture('18');">patient list</a></td>
				</tr>
				
      </table>
		
		<!-- appointment management    -->
        <TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img1" id="img1" src="images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('1');" >App management</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		<table id="subtree1" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu1" src="images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="appointment?method=showList" target="mainFrame" class="left-font03" onClick="picture('1');">Pending appointments</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu1" src="images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="appointment?method=showArchiveList" target="mainFrame" class="left-font03" onClick="picture('1');">Archive appointments</a></td>
				</tr>
      </table>
  

	  <!--  department management    -->
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29"><table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="8%" height="12"><img name="img3" id="img3" src="images/ico04.gif" width="8" height="11" /></td>
                  <td width="92%"><a href="javascript:" target="mainFrame" class="left-font03" onClick="list('3');" >Dep management</a></td>
                </tr>
            </table></td>
          </tr>
      </table>
	  
	  <table id="subtree3" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
        <tr>
          <td width="9%" height="20" ><img id="xiaotu8" src="images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="admin/addDep.jsp" target="mainFrame" class="left-font03" onClick="picture('8');">Add dep</a></td>
        </tr>
		<tr>
          <td width="9%" height="20" ><img id="xiaotu9" src="images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="department?method=showList" target="mainFrame" class="left-font03" >dep list</a></td>
        </tr>
		
      </table>

	  </TD>
  </tr>
</table>
</body>
</html>