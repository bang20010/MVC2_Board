<%@page import="Model.Model_Member"%>
<%@page import="member.MyMemberDataBean"%>
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
<%
	
	

	
	String result = (String)request.getAttribute("searchid");
	
	String path = request.getContextPath();

	System.out.println("idcheck�� "+result);
	
	memberService member = 	memberServiceImpl.getInstance();
	MyMemberDataBean model = new MyMemberDataBean();
	
	System.out.println("serchResultId���� �� : "+model.getId());
	
	
%>

<div class="container">
<% if (result != null )
	{	%>
	<p class="txt">ȸ������ ���̵�� <%=result%> �Դϴ� </p>
	<input type="button" class="btn" value="�α��� ������" onclick="location='memberLogin/Mylogin.jsp'" >
<%	}
else if (result == null )
	{	%>
	<p class="txt">ȸ������ ���̵� �����ϴ�.	</p>
	<input type="button" class="btn" value="���̵� ã��" onclick="location='memberLogin/searchId.jsp'" >
<%	
	}
%>

</div>
</body>
</html>