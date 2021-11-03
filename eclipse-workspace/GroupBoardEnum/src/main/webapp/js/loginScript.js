 function checkLogin(){
   
   if(!loginForm.id.value){
     alert("ID를 입력하세요");
     loginForm.id.focus();
     return false;
   }
   
   if(!loginForm.passwd.value){
     alert(" 비밀번호를 입력하세요");
     loginForm.passwd.focus();
     return false;
   }
    if(!loginForm.email.value){
     alert("이메일를 입력하세요");
     loginForm.id.focus();
     return false;
   }
 };