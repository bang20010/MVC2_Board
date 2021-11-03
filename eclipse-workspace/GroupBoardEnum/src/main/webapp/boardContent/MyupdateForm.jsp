<%@page import="board.BoardDataBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_updateForm.css">
</head>

<body>
	<%
	
		
		System.out.println("Myupdate에 들어옴");	
		
		int num = (int)request.getAttribute("num");
	
		String path = request.getContextPath();
	
		// 	getUpdate자체가 넘어오는 값이 null
		
		BoardDataBean getUpdate = (BoardDataBean)request.getAttribute("getUpdate");
	
		String id = getUpdate.getId();
		
		String checkID = (String)session.getAttribute("id");
		
		if(id.equals(checkID))
		{
		String subject = getUpdate.getSubject();
		String fullContent = getUpdate.getContent();
		getUpdate.setId(checkID);
	%>
	<div class="container">
		<form method="post" name="updateform"
			action="<%=path%>/controller?command=content_update&num=<%=num%>">
			<table>
				<tr>
					<td colspan="2">※ 게시글 수정 ※</td>
				</tr>
	
				<tr>
					<td width="100" align="center">수정자(ID)</td>
					<td width="400" align="left"><input type="text" name="id"  size="10" value="<%=session.getAttribute("id")%>" class="input" readonly="readonly">
				</tr>

				<tr>
					<td width="100" align="center">비밀번호</td>
					<td width="400" align="left"><input type="password" size="10"
						maxlength="16" name="passwd" class="input" required="required"> ← 기존 설정하신 게시글 비밀번호를 입력하세요.
					</td>
				</tr>

				<tr>
					<td width="100" align="center">제목</td>
					<td width="400" align="left"><input type="text" size="70"
						maxlength="50" name="subject" class="input" value="<%=subject %>" required="required">
					</td>
				</tr>

				<tr>
					<td width="100" align="center">내용</td>
					<td width="400" align="left"><textarea name="content"
							rows="20" cols="60" class="input" required="required"><%=fullContent %></textarea>
					</td> 
				</tr>



				<tr>
					<td colspan=2 align="center"><input type="submit" value="수정하기"
						class="insert_btn"> <input type="reset" value="다시 작성"
						class="insert_btn"> <input type="button" value="목록보기"
						class="insert_btn" onclick="window.location='list.jsp'"></td>
				</tr>

			</table>
		</form>
	</div>
	<%} else {%>
		<script>
		alert('다른 사람의 글을 삭제 할 수 없습니다. ');
		window.history.back();
		</script>
	
	<%}%>
</body>
</html>
	