
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 작성</title>
<link href="../css/style_insertForm.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/boardScript.js"></script>
</head>

<body>
<h1>게시글 작성하기</h1>
<form method="post" name="insertform" 
action="<%=path%>/controller?command=content_write" onsubmit="return chec()">
<table>

<tr>
<td width="100"  align="center" >작성자(ID)</td>
<td width="400" align="left">
<input type="text" size="10" maxlength="10" name="id" class="input" value="<%=session.getAttribute("id") %>" readonly="readonly">
</tr>

<tr>
<td width="100"  align="center">비밀번호</td>
<td width="400" align="left">
<input type="password" size="10" maxlength="16" name="pw" >
</td>
</tr>

<tr>
<td width="100"  align="center">제목</td>
<td width="400" align="left">
<input type="text" size="38" maxlength="50" name="subject" >
</td>
</tr>


<tr>
<td width="100"  align="center">내용</td>
<td width="400" align="left">
<textarea name="content" rows="20" cols="40" ></textarea>
</td>
</tr>



<tr>
<td colspan=2  align="center">
<input type="submit" value="글쓰기">
<input type="reset" value="다시 작성">
<input type="button" value="목록보기" onclick="window.location='list.jsp'">
</td>
</tr>

</table>
</form>
</body>
</html>