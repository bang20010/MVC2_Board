

<%@page import="Model.Model_Board"%>
<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.Model_Reply"%>
<%@page import="java.util.List"%>
<%@page import="reply.MyReplyDataBean"%>
<%@page import="board.BoardDataBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 본문</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_contents.css">
<script type="text/javascript">
	var path = '<%=request.getContextPath()%>';
</script>
</head>
<body>
	<%
		//날짜 데이터를 String 형변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		//프로젝트 이름까지 경로를 지정
		String path = request.getContextPath();
		
		//DB안에 데이터 값을 MyBoardDataBean을 통해 웹 페이지에 DB에 저장된 값을 산출하는 코드
		
		//getAttribute는 이전에 다른 jsp 또는 Serlvet페이지에서 매게변수 content를 가져옴
		BoardDataBean content = (BoardDataBean)request.getAttribute("content");
		String id = content.getId();
		String subject = content.getSubject();
		String fullContent = content.getContent();
		int readCount = content.getReadcount();
		
		//Model 클래스로 값을 전달함
		Model_Board model = new Model_Board();
		
		   
		//댓글 등록 기능 구현부
		int num = (int)request.getAttribute("num");
		
		model.setNum(num);
		
		
		String sessionId = (String)request.getAttribute("sessionId");
		
		System.out.println("Mycontent에 들어오는 sessionId 값 : "+sessionId);
		
		MyReplyDataBean replydata = new MyReplyDataBean();
		
	
	
		
		//댓글 리스트
		List<MyReplyDataBean> replyList = (List<MyReplyDataBean>) request.getAttribute("replyList");
		
		for(MyReplyDataBean re:replyList)
			{
				System.out.println(re.getId_reply());			
			}
		
	%>

	<div class="container">
		<!-- 목록, 수정, 삭제 버튼 -->
		<div class="btn_wrap">
		<form method="post" action="<%=path%>/controller?command=content_getupdate&num=<%=content.getNum()%>">
			<input type="button" value="목 록" class="btn"
				onclick="location.href='boardContent/Mylist.jsp'">
				<input type="submit" value="수 정" class="btn">
				<input type="button" value="삭 제" class="btn"
				onclick="location.href='boardContent/MydeleteForm.jsp?num=<%=content.getNum()%>'">
		</form>
		</div>

		<!-- 글 내용  -->
		<form method="post" action="<%=path%>/controller?command=contents">
			<div class="contents_container">
				<div class="contents_header">
				
					<h2 class="subject"><%=subject%></h2>
					<div class="num"><%=num %></div>
					<div class="id"><%=id %></div>
					<div class="insertDate"><%=sdf.format(content.getReg_date()) %></div>
					<div class="count"><%=readCount %></div>
					<div class="content"><%=fullContent %></div>
				</div>
			</div>
		</form>
	
	  <!-- 댓글  -->
      <div class="comment_container">
         <h2 class="commnet_h">댓글</h2>
         <form name="replyForm" action="<%=path%>/controller?command=reply_write" method="post">
            <input type="hidden" id="num" name="num" value="<%=num%>"> 
            <input type="hidden" id="sessionId" name="sessionId" value="<%=sessionId%>"> 
            <input type="text" name="comment" id="comment" onKeypress="javascript:if(event.keyCode==13) {insertReply();}"> 
            <input type="submit" value="등 록" class="comment_btn">
         </form>
         
         <div class="cList_wrap">
            <%
            // DB의 댓글테이블을 불러옴
            for (MyReplyDataBean reply : replyList) 
            {
            	if(session.getAttribute("id").equals(reply.getId()))
            	{
            		int id_reply = reply.getId_reply();
            		System.out.println("mycontent 안 id_reply : "+id_reply);
            %>
            
          <ul class="comment_list">
               <li class="name"><%=reply.getId()%></li>
               <li class="comment"><%=reply.getComment()%><span class="rBtnWrap">
               <input type="button" value="수정" class="re_btn" onclick="window.open('boardContent/MyupdateReply.jsp?id_reply=<%=reply.getId_reply()%>&comment=<%=reply.getComment()%>','','width=600, height=300, top=300, left=500','_blank');">&nbsp;
               <input type="button" value="삭제" class="re_btn" onclick="if(confirm('삭제 하시겠습니까?')){ location.href='controller?command=reply_delete&id_reply=<%=reply.getId_reply()%>&contentNum=<%=content.getNum()%>'}"></span></li>
               <li class="insertDate"><%=sdf.format(reply.getReplyDate())%></li>
            </ul>
            <%
            	}else {
            		%>
            		<ul class="comment_list">
                    <li class="name"><%=reply.getId()%></li>
                    <li class="comment"><%=reply.getComment()%></li>
                    <li class="insertDate"><%=sdf.format(reply.getReplyDate())%></li>
                	</ul>
                 <% 
            	}
            }
            %>
		</div>
	</div>
</div>
</body>
</html>