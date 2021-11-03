package member;
import Model.Model_Member;

public interface memberService {
	
	public boolean login(String id,String pw);
	public boolean MemberCheckId(Model_Member model); 
	public void signUpMember(MyMemberDataBean member);
	public String searchPw(MyMemberDataBean member); 
	public String searchId(MyMemberDataBean member); 
	/*
	public MyMemberDataBean getMember(String id);
	public boolean updateMember(MyMemberDataBean member);
	public boolean deleteMember(String id, String pw);
	*/
}
