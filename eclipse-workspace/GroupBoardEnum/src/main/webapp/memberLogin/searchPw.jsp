<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��й�ȣ ã��</title>
<%
String path = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="../css/style_checkForm.css">
<script type="text/javascript" src="../js/loginScript.js"></script>
</head>
<body>
	<form method="post" action="<%=path%>/controller?command=searchPw" name="loginForm"  >
			<div class="contents_container">
				<div class="contents_header">
					<table>
						<tr>
							<td width="100" align="center">���̵� :  </td>
							<td width="400" align="center"><input type="text" size="35"	name="id" class="join" id="email" placeholder="���̵� �Է��ϼ���" >
							
						</tr>
						<tr>
							
							<td width="100" align="center">�̸��� :  </td>
							<td width="400" align="center"><input type="text" size="35"	name="email" class="join" id="email" placeholder="�̸����� �Է��ϼ���" >
						</tr>
						</table>
						
						<td class="button"><input type="submit" class="btn" value="��й�ȣ ã��"  ><input type="button" class="btn" value="�α��� ������" onclick="location='Mylogin.jsp'" ></td>

			</div>
		</div>
	</form>
</body>
<%-- <script type="text/javascript">
         
     function serchPw() 
     {
        // jsp�� ��� path�� �ڹٽ�ũ��Ʈ������ ����� �� �ִ� �����̴�. 
       	var path = '<%=request.getContextPath()%>';
		
    
     	var id = document.getElementById("id").value;
		var email = document.getElementById("email").value;

		
		window.open(path +"/controller?command=searchPw&id="+id+"&email="+ email, "",
				"width=300, height=200, top=300, left=1000", "_blank");
	}
</script> --%>
</html>