<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>아이디 찾기</title>
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
							<td width="110" align="center">이메일  :</td>
							<td width="400" align="center"><input type="text" size="35"	name="email" class="join"  placeholder="이메일을 입력하세요" >
						</tr>
					</table>
							<input type="submit" class="btn" value="아이디 찾기" ><input type="button" class="btn" value="로그인 페이지" onclick="location='/Mylogin.jsp'" >
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
         
   <%--   function serchId() 
     {
        // jsp의 경로 path를 자바스크립트에서도 사용할 수 있는 변수이다.
       	var path = '<%=request.getContextPath()%>';
		
    
     
		var email = document.getElementById("email").value;

		
		window.open(path +"/controller?command=searchId&email="+ email, "",
				"width=300, height=200, top=300, left=1000", "_blank");
	} --%>
</script>
</html>