<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/style_login.css">
<link rel="shortcut icon" href="#">
<script type="text/javascript" src="../js/loginScript.js"></script>
<title>로그인 페이지</title>
</head>
<body>
<% 
String path=request.getContextPath();
System.out.println(path);
%>

   <div class="container">
      <h1 class="contents_title">로그인</h1>
      <form action="<%=path%>/controller?command=login" method="post" name="loginForm" onsubmit="return checkLogin()">
         <div class="contents_container">
            <div class="contents_header">
               <input type="text" id="id" name="id" placeholder="ID" ><br>
               <input type="password" id="passwd" name="passwd" placeholder="Password" >
            </div> 

            <div class="contents_btn">
               <input type="submit" value="로그인" id="btn"><br>
               <a href="searchId.jsp" id="joinTag">아이디 찾기</a>
               <a href="searchPw.jsp" id="joinTag">비밀번호 찾기</a>
               <a href="MyjoinForm.jsp" id="joinTag">회원가입</a>
            </div>
         </div>
      </form>
   </div>
</body>
</html>