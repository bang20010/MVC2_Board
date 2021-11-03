
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="../css/style_joinFrom.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>
	<%
		String path = request.getContextPath();
	%>

	<form method="post" name="joinform" action="<%=path%>/controller?command=signup" onsubmit="return join_check()">
		<div class="container">
			<div class="btn_wrap">
				<input type="submit" value="가 입" class="btn" >
				 <input type="reset" value="다시쓰기" class="btn"> 
				 <input type="button" value="로그인 화면으로" class="btn" onclick="location.href='Mylogin.jsp'">
			</div>


  
			<div class="contents_container">
				<div class="contents_header">
					<table>
						<tr>
							<td width="100" align="center">아이디:</td>
							<td width="400" align="center"><input type="text" size="35"	maxlength="12" name="id" class="join" id="id" placeholder="영어 숫자 포함 4~12자를 입력" ><!--  -->
							<td><input type="button" class="btn" value="아이디 찾기" onclick="checkId()" ></td>
						</tr>

						<tr>
							<td width="100" align="center">비밀번호:</td>
							<td width="400" align="center">
							<input type="password" size="35" maxlength="16" id="pw" name="pw" class="join" placeholder="영어 숫자 특수문자 포함 8~16자를 입력" ></td>
						</tr>
						<tr>
							<td width="100" align="center">비밀번호 확인:</td>
							<td width="400" align="center"><input type="Password" size="35" maxlength="16" id="confrimpw" name="confrimpw" class="join" placeholder="영어 숫자 특수문자 포함 8~16자를 입력" ></td>
						</tr>
						
						<tr>
							<td width="100" align="center">이름:</td>
							<td width="400" align="center"><input type="text" size="35"
								maxlength="50" id="name" name="name" class="join" placeholder="이름을 입력하세요" ></td>
						</tr>

						<tr>
							<td width="100" align="cent er">전화번호:</td>
							<td width="400" align="center"><input type="text" size="35"
								maxlength="11" id="phone" name="phone" class="join" placeholder="-없이 입력하세요" ></td>
						</tr>


						<tr>
							<td width="100" align="center">Email:</td>
							<td width="400" align="center"><input type="text" size="35"
								maxlength="50" id="email" name="email" class="join" placeholder="이메일을 입력하세요" ></td>
						</tr>

					</table>

				</div>
			</div>
		</div>
	</form>

</body>

<script type="text/javascript" src="../js/joinScript.js"></script>
<script type="text/javascript">
         
     function checkId() 
     {
        // jsp의 경로 path를 자바스크립트에서도 사용할 수 있는 변수이다. 
       	var path = '<%=request.getContextPath()%>';
		
     
		var idCheck = document.getElementById("id").value;

		
		window.open(path +"/controller?command=checkId&id="+ idCheck, "",
				"width=300, height=200, top=300, left=1000", "_blank");
	}
</script>


</html>