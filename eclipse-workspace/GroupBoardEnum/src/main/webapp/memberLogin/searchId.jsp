<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���̵� ã��</title>
<%
String path = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="../css/style_checkForm.css">
<script type="text/javascript" src="../js/loginScript.js"></script>
</head>
<body>
		<form method="post" action="<%=path%>/controller?command=searchId" name="loginForm"  >
			<div class="contents_container">
				<div class="contents_header">
					<table>
						<tr> 
							<td width="110" align="center">�̸���  :</td>
							<td width="400" align="center"><input type="text" size="35"	name="email" class="join"  placeholder="�̸����� �Է��ϼ���" >
						</tr>
					</table>
							<input type="submit" class="btn" value="���̵� ã��" ><input type="button" class="btn" value="�α��� ������" onclick="location='/Mylogin.jsp'" >
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
         
   <%--   function serchId() 
     {
        // jsp�� ��� path�� �ڹٽ�ũ��Ʈ������ ����� �� �ִ� �����̴�.
       	var path = '<%=request.getContextPath()%>';
		
    
     
		var email = document.getElementById("email").value;

		
		window.open(path +"/controller?command=searchId&email="+ email, "",
				"width=300, height=200, top=300, left=1000", "_blank");
	} --%>
</script>
</html>