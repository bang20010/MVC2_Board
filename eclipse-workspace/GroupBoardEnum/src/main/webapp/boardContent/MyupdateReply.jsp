<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%
int replyNum = Integer.parseInt(request.getParameter("id_reply"));

System.out.println("myupdateRelpy안에 replyNum : "+replyNum);

String path = request.getContextPath();

String comment = request.getParameter("comment");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<link rel="stylesheet" type="text/css" href="style_contents.css">
</head>
<body>
	<div class="mo_container">
		<h3 class="modifyTitle">댓글 수정</h3>
		<form action="<%=path%>/controller?command=reply_update&id_reply=<%=replyNum%>"
			method="post">
			<textarea rows="7" cols="60" name="replyModify" id="mo_cmt"><%=comment %></textarea>
			<div id="mo_btn_Wrap">
				<input type="submit" value="수 정" class="mo_btn"> 
				<input type="button" value="닫 기" class="mo_btn" onclick="window.close()">
			</div>
		</form>
	</div>

</body>
</html>