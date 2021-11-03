<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	System.out.println("Mydelte에 들어옴");	

	int num = Integer.parseInt(request.getParameter("num"));
	
	System.out.println("Mydelte에 num 값"+num);
	
	String path = request.getContextPath();
%>
<title>게시판 글 삭제</title>
<link rel="stylesheet" type="text/css" href="../css/style_deleteForm.css">
<script type="text/javascript">
	function deletePwChk(){
		if(!document.delForm.passwd.value){
			   alert("비밀번호를 입력하십시요.");
			   document.delForm.passwd.focus();
			   return false;
		    }
	}
</script>
</head>
<body>
<form method="POST" name="delForm" action="<%=path %>/controller?command=content_delet" onsubmit="return deletePwChk()"> 
 <div class="container">
 	<ul>
 		<li><b>비밀번호를 입력해 주세요.</b></li>
 		<li>
	 		비밀번호   
	       <input type="password" name="passwd" size="8" maxlength="12">
		   <input type="hidden" name="num" value="<%=num%>">
 		</li>
 		<li>
 			<input type="submit" value="삭 제" class="btn" >
      		<input type="button" value="취 소" class="btn"
       onclick="document.location.href='<%=path%>/controller?command=contents&num=<%=num%>'"> 
 		</li>
 	</ul>
   
</div> 
</form>
</body>
</html>