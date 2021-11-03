<%@page import="board.BoardService"%>
<%@page import="board.BoardServiceImpl"%>
<%@page import="board.BoardDataBean"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//jsp에 no-catch를 적용하는 코드[서버 지연을 줄이기 위해 정적 컨텐츠[image,js,css]를 임시저장하여 웹 사본 정보를 불러옴으로 더 빠르게 열람이 가능하다]
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", 0L);
%>
<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

String path = request.getContextPath();

String checkID = (String)session.getAttribute("id");

if(checkID == null)
{
	response.sendRedirect(path+"/memberLogin/Mylogin.jsp");
}
else{

String pageNum = request.getParameter("pageNum");

if (pageNum == null)
{
	pageNum = "1";
}
int pageNow = Integer.parseInt(pageNum);
int pageSize = 10;

BoardService dbPro = BoardServiceImpl.getInstance();
int count = dbPro.count();
int start = (pageNow - 1) * pageSize + 1;
int end = pageNow * pageSize;

List<BoardDataBean> boardList = null;

if (count > 0) {
	boardList = dbPro.getAll(start, end);
	session.setAttribute("readCount", "yes");
}

int number = count - (pageNow - 1) * pageSize;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../css/style_list.css">
</head>
<body>	
	<table>
		<tr>
		<td width="200px">
		<form action="<%=path%>/controller?logout" method="post" class="logoutForm">	
		<%=session.getAttribute("id")%> 환영합니다</div>
		<input type="submit" value="로그아웃" class="logout_btn"> 
		</td>
		</tr>
		</form>
		</table>
	<div class="container">
	<div class="userid">

		</div>
		<br>
		<%
			if (count == 0) {
		%>
		<table class="list_table">
			<tr>
				<th width="1000px">게시판에 저장된 글이 없습니다.</th>
			</tr>
		</table>

		<%
			} else {
		%>
		<table class="list_table" cellspacing="0">
			<tr>
				<th width="50px"></th>
				<th width="700px">제 목</th>
				<th width="100px">작성자</th>
				<th width="100px">작성일</th>
				<th width="50px">조회</th>
			</tr>

			<%
				for (int i = 0; i < boardList.size(); i++) {
						BoardDataBean board = boardList.get(i);
			%>
			<tr>
				<td><%=number--%></td>
				<td><a href="<%=path%>/controller?command=contents&num=<%=board.getNum()%>"><%=board.getSubject()%></a></td>
				<td><%=board.getId()%></td>
				<td><%=sdf.format(board.getReg_date())%></td>
				<td><%=board.getReadcount()%></td>
			</tr>
			<%
			}
			}
			%>
		</table>
			<input type="button" value="글쓰기" class="insert_btn"
			onclick="window.location='MyinsertForm.jsp'">
		<div class="pageNum">
			<%
			if (count > 0) {
				int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

				for (int i = 1; i <= pageCount; i++) {
			%>
			<a href="list.jsp?pageNum=<%=i%>">[<%=i%>]
			</a>
			<%
			}
			}
}
			%>
	</div>
</body>
</html>