<%@page import="member.memberServiceImpl"%>
<%@page import="member.memberService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">

body{background-color:#eee;}
.container{ position:absolute; top:50%; left:50%; transform:translate(-50%,-50%); text-align:center; }
.close{padding:10px 20px ; background-color:#fff;  border:1px solid #ccc; cursor:pointer; }
.txt{background-color:#fff; width:200px; padding:20px; text-align:center; border-radius:10px;}
</style>
</head>
<%
	
	String result = (String)request.getAttribute("searchpw");
	
	System.out.println("idcheck에 "+result);
	
	memberService member = 	memberServiceImpl.getInstance();
	
%>
<body>
<div class="container">
<% if (result != null )
	{	%>
	<p class="txt">회원님의 비밀번호는 <%=result%> 입니다.</p>
	<input type="button" class="btn" value="로그인 페이지" onclick="location='memberLogin/Mylogin.jsp'" >
<%	}
else if (result == null )
	{	%>
	<p class="txt">회원님의 아이디나 이메일이 존재하지 않습니다.</p>
	<input type="button" class="btn" value="비밀번호 찾기" onclick="location='memberLogin/serchPw.jsp'" >
<%	
	}
%>
</div>

</body>
</html>