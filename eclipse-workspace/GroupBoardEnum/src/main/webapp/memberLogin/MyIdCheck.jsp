<%@page import="member.memberServiceImpl"%>
<%@page import="board.BoardServiceImpl"%>
<%@page import="member.memberService"%>
<%@page import = "Model.Model_Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<%
	
	

	
	boolean result = (Boolean)request.getAttribute("checkId");
	
	System.out.println("idcheck에 "+result);
	
	memberService member = 	memberServiceImpl.getInstance();
	
%>

<body>
<div class="container">
<% if (result == true )
	{	%>
	<p class="txt">생성 가능한 아이디 입니다.</p>
	<input type='button' value='닫 기' class="close" onclick='window.close()'>
<%	}
else if (result == false )
	{	%>
	<p class="txt">이미 존재하는 아이디 입니다.</p>
	<input type='button' value='닫 기' class="close" onclick='window.close()'>
<%	
	}
%>

</div>
</body>
</html>