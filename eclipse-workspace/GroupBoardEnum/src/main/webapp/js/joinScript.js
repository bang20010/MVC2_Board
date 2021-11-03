function join_check() 
{	
	// 회원가입에 필요한 정규식 변수를 초기화
	var checkId = "^[a-zA-Z0-9{4,12}]*$";
	var checkPw = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,14}$";
	var checkName = "[가-힣]*$";
	var checkEmail = "^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$";
	var checkPhone = "^\\d{2,3}\\d{3,4}\\d{4}$";
	
	// MyIdchek.jsp의 form안의 값을 가져오는 변수 
	var id = joinform.id.value;
	var pw = joinform.pw.value;
	var confrimpw =joinform.confrimpw.value;
	var name = joinform.name.value;
	var phone = joinform.phone.value;
	var email = joinform.email.value;
	
	var text = "";

	if (!id || id == null) 
	{
		alert("아이디를 입력하세요");
		joinform.id.focus();
		return false;
	}
	else if (checkId.test(id) == true)
	{
        text += "ID : " + id + "\n";
    }
	else
	{
		alert("잘못된 아이디 형식입니다 아이디는 영어 숫자 포함 4~12자를 입력하세요");
		joinform.id.focus();
		return false;
	}
	if (!pw || pw == null) 
	{
		alert("비밀번호를 입력하세요");
		joinform.pw.focus();
		return false;
	}
	if(checkPw.test(pw) == true)
	{
    
    }
	else
	{
		alert("잘못된 비밀번호 형식입니다 영어 숫자 특수문자 포함 8~16자를 입력하세요");
		joinform.pw.focus();
		return false;
	}
	if (!confrimpw || confrimpw == null) 
	{
		alert("비밀번호 확인을 입력하세요");
		joinform.pw.focus();
		return false;
	}
	if (pw == confrimpw)
	{
		alert("비밀번호와 비밀번호 확인이 동일하지 않습니다. 다시 입력하세요");
		joinform.pw.focus();
		return false;
	}

	if (!name || name == null) 
	{
		alert("이름을 입력하세요");
		joinform.name.focus();
		return false;
	}
	
	
	if (!phone || phone == null) 
	{
		alert("올바른 핸드폰 번호를 입력하세요");
		joinform.phone.focus();
		return false;
	}

	if (!email || email == null) {
		alert("이메일을 입력하세요");
		joinform.email.focus();
		return false;
	}
	
	function checkValue(value,check)
	{
		var check = false;
		
		if(value == null)
		{
			check;	
		}
		else if (value == "")
		{
			check;
		}
		else if(value.test(check))
		{
			check = true;
		}
		else
		{
			check = false;	
		}
		
		return check;
	}
	
	// 입력값이 아이디가 없을때를 가정했기 때문에 대입값을 다르게 만들어야한다.
	/*
		if (!check(id, checkId)) {
			alert("아이디는 4~12자의 영문 대소문자와 숫자만으로 입력하세요");
			id.focus();
			return false;
		}
			if (!check(pw, checkId)) {
			alert("비밀번호는 8~14자의 영문 대소문자와 숫자 특수기호로 입력하세요");
			pw.focus();
			return false;
		}
			if (!check(name, checkName)) {
			alert("이름은 한글로 입력하세요");
			name.focus();
			return false;
		}
			if (!check(phone, checkPhone)) {
			alert("올바른 핸드폰 번호를 입력하세요");
			phone.focus();
			return false;
		}
		
		if (!check(name, checkName)) {
			alert("이름은 한글로 입력하세요");
			name.focus();
			return false;
		}
			if (!check(email, checkEmail)) {
			alert("올바른 이메일 주소를 입력하세요");
			phone.focus();
			return false;
		}
	*/	
};