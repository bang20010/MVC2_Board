<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>비밀번호 찾기</title>
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
							<td width="100" align="center">아이디 :  </td>
							<td width="400" align="center"><input type="text" size="35"	name="id" class="join" id="email" placeholder="아이디를 입력하세요" >
							
						</tr>
						<tr>
							
							<td width="100" align="center">이메일 :  </td>
							<td width="400" align="center"><input type="text" size="35"	name="email" class="join" id="email" placeholder="이메일을 입력하세요" >
						</tr>
						</table>
						
						<td class="button"><input type="submit" class="btn" value="비밀번호 찾기"  ><input type="button" class="btn" value="로그인 페이지" onclick="location='Mylogin.jsp'" ></td>

			</div>
		</div>
	</form>
</body>
<%-- <script type="text/javascript">
         
     function serchPw() 
     {
        // jsp의 경로 path를 자바스크립트에서도 사용할 수 있는 변수이다. 
       	var path = '<%=request.getContextPath()%>';
		
    
     	var id = document.getElementById("id").value;
		var email = document.getElementById("email").value;

		
		window.open(path +"/controller?command=searchPw&id="+id+"&email="+ email, "",
				"width=300, height=200, top=300, left=1000", "_blank");
	}
</script> --%>
</html>