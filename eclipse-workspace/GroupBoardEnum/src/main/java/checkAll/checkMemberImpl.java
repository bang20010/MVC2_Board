package checkAll;

 
import regex.regEx;
import Model.Model_Member;

public class checkMemberImpl implements checkMember
	{
		
		private static checkMemberImpl instance = new checkMemberImpl();
		
		public static checkMemberImpl getInstance() 
		{
			return instance;
		}
	
		regEx regex = new regEx();	

	
// 	자바에서 공백이 입력을 금지하는 메소드	
	 

		private static boolean isBlank(String str) {
			int strLen; if (str == null || (strLen = str.length()) == 0) {
				return true; 
			} 
			for (int i = 0; i < strLen; i++) {
				if ((!Character.isWhitespace(str.charAt(i)))) {
					return false; 
				} 
			} 
			return true; 
		}
	
	@Override
	public Boolean checkLogin(String id, String pw, String dbid, String dpdw) {
		
		boolean confrim = false;
			
		if(id.equals(dbid))
		{
			if(pw.equals(dpdw))
			{
				confrim = true;
			}
		}
		else 
		{
			System.out.println("아이디와 비밀번호를 확인해 주세요.");
			confrim = false;
		}
		
		return confrim;
	}
	@Override
	public Boolean checkId(String id) 
	{	
		boolean check = false;
		
		
		System.out.println();
		System.out.println("checkId의 파라미터 id :"+id);
		System.out.println();
		
		// 빈칸 입력 후 문자를 입력하면 아이디 생성이 가능하며 글자수가 적을때도 아이디 체크 기능이 작동하지 않는다.
		if(id == null)
		{
			System.out.println("checkId의 파라미터 id null :"+id);
			check = false;
		}
		else if(id =="")
		{
			System.out.println("checkId의 파라미터 id \"\":"+id);
			check = false;
		}
		else if(id.length()<5)
		{
			System.out.println("checkId의 파라미터 id가 빈칸 :"+id);
			check = false;
		}
		else
		{
			System.out.println("체크ID 결과값 true: "+check);
			check = true;
		}
		System.out.println("체크ID 결과값 : "+check);
		System.out.println();
		return check;
		
	}

	@Override
	public Boolean doEqulId(String id, String dbid) {
		
		boolean result = false;
		
		if(id.equals(dbid))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		
		return result;
	}

	@Override
	public Boolean doEqulPw(String pw, String dbpw) {
		
		boolean result = false;
		
		if(pw.equals(dbpw))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		
		return result;
	}
}
	/*
	@Override
	public int checkJoin(String id, String pw, String name, String phonenum, String email) {
		
		regEx regex = new regEx();
		boolean idCheck = regex.isId(id);
		boolean pwCheck = regex.isPw(pw);
		boolean nameCheck = regex.isKorean(name);
		boolean phoneCheck = regex.isMob(phonenum);
		boolean emailCheck = regex.isEmail(email);
		
		System.out.println("check맴버의 Join 아이디 :"+id+" 비밀번호 :"+pw+" 이름 :"+name+" 핸드폰번호 : "+phonenum+" 이메일 "+email);
		
		int checkjoin = 0;
		
		if(idCheck) 
			{
				checkjoin = 1;
			
				if(pwCheck)
				{
					checkjoin = 2;
					
					if(nameCheck)
					{
						checkjoin = 3;
					
						if(phoneCheck)
						{
							checkjoin = 4;
						
							if(emailCheck)
								{
								checkjoin = 5;
								}
						}
					}
				}
			
			}
		else 
			{
				checkjoin = 0;
			}
		
		
		
		return checkjoin;
	}
	*/
// check id를 변경해야함




