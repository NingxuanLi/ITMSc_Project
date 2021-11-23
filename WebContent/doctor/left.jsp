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
					
					<td width="75%" height="22" class="left-font01">Hello, doctor <span class="left-font02">${sessionScope.doctor.docName}</span></td>
				  </tr>
				  <tr>
					<td height="22" class="left-font01">
						[<a href="doctor?method=logout" target="_top" class="left-font01">log out</a>]
						[<a href="doctor/index.jsp" target="_top" class="left-font01">back to index</a>]
						</td>
				  </tr>
				</table>
			</td>
		  </tr>
		</table>

      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29"><table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="10%"><img name="img5" id="img5" src="images/ico04.gif" width="8" height="11" /></td>
                  <td width="90%"><a href="appointment?method=showDoctorList" target="mainFrame" class="left-font03">Appointment list</a></td>
                </tr>
            </table></td>
          </tr>
      </table>

	  

		

	  </TD>
  </tr>
</table>
</body>
</html>