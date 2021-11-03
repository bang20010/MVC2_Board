package checkAll;

import Model.Model_Member;

public interface checkMember {
	
	Boolean checkLogin(String id, String pw, String dbid, String dpdw);  // 로그인 메소드에서 활용
//	int checkJoin(String id, String pw, String name, String phonenum ,String email); // int 반환값으로 확인이 안된 메소드 확인
	Boolean checkId(String id);// idcheck 메소드
	Boolean doEqulId(String id, String dbid);
	Boolean doEqulPw(String pw,String dbpw);
}
