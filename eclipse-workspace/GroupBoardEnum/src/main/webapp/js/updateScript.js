function update(){
	
	if(!updateform.id.value){
	  alert("아이디를 입력하세요");
	  insertform.name.focus();
	  return false;
	}
	
	if(!updateform.passwd.value){
	  alert(" 비밀번호를 입력하세요");
	  insertform.passwd.focus();
	  return false;
	}
	
	if(!updateform.subject.value){
	  alert("제목을 입력하세요");
	  insertform.subject.focus();
	  return false;
	}
	if(!updateform.content.value){
	  alert("내용을 입력하세요");
	  insertform.content.focus();
	  return false;
	}
        


 };