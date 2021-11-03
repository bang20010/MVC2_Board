package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import checkAll.checkMember;
import checkAll.checkMemberImpl;
import connectDB.connctionImpl;
import connectDB.interConnection;
import finallymethod.finalMethod;
import finallymethod.finalMethodImpl;

import regex.regEx;

import Model.Model_Member;

public class memberServiceImpl implements memberService{
	
	// 예외 처리 후 finally코드를 처리하기 위한 클래스 [PreparedStatement까지 처리 하는 메소드와 ResultSet까지 처리하는 메소드를 가지고 있다] 
	
	finalMethod finnalMethod = new finalMethodImpl();
	interConnection connection = new connctionImpl();
	checkMember checkmember = new checkMemberImpl();
	
	
	private static memberServiceImpl instance = new memberServiceImpl();
	
	
	public static memberServiceImpl getInstance() {
		return instance;
	}
	
	private memberServiceImpl() {}
	
	
	
	
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/basicjsp");
		return ds.getConnection();
	}
	
	

	
	// 로그인 메소드  
	@Override
	public boolean login (String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginChk = false;
		
		try 
		{
			System.out.println("멤버 Impl 로그인 메소드에 값이 입력됨 :" + id + "," +pw);
			// Connection conn에 DB연동 
			conn = getConnection();
			
			// PreparedStatement pstmt에 쿼리문 완성
			pstmt = conn.prepareStatement("select id, passwd from signup_g where id=?");
			
			// 쿼리문에서 특정조건의 한 레코드나 필드를 조회 할때는 PreparedStatement에 ?을 사용하여 완성한다
			pstmt.setString(1, id);
			
			// 쿼리문에 완성하면서 쿼리문의 필드 변수를 사용하거나, 원하는 값을 비교하기 위해 사용하는 ResultSet
			rs = pstmt.executeQuery();
			
			while (rs.next()) 
			{	
				System.out.println("rs.while에 멤버 Impl 로그인 메소드에 값이 입력됨 :" + id + "," +pw);
				
			// 오라클 DB에 signup_g테이블에 id, passwd 필드를 사용하기 위해 String 변수 초기화
				String dbid = rs.getString("id");
				String dbpw = rs.getString("passwd");
			
			// login 메소드의 파라미터 String id, String pw를 dbid,dbpw랑 같은지 확인하는 메소드를 boolean checklogin에 초기화
				boolean checklogin = checkmember.checkLogin(id, pw, dbid, dbpw);
				System.out.println("체크로그인 결과 : "+checklogin);
			
				if(checklogin) 
				{
					
					loginChk = true;
				}
				else
				{
					System.out.println("아이디와 비밀번호가 다릅니다.");
					loginChk = false;
				}
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{	
			// Connection, prepareStatement, ResultSet에 사용되는 Exception들을 메소드로 불러서 처리
			finnalMethod.rsMethod(conn, pstmt, rs);
		}
		return loginChk;
	}
	
	/*회원가입 메소드*/
	@Override
	public void signUpMember(MyMemberDataBean member) {

		Connection conn = null;
		PreparedStatement pstmt = null;
	
		
		try 
		{
			System.out.println("멤버 Impl 회원가입 메소드에 값이 입력됨");	
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into signup_g values(?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getName());
			pstmt.setString(5, member.getEmail());
			pstmt.setTimestamp(6, member.getJoindate());
			pstmt.executeUpdate();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally {
			// Connection, prepareStatement에 사용되는 Exception들을 메소드로 불러서 처리
			finnalMethod.pstmtMethod(conn, pstmt);
			
		}
	}
	

	@Override
	public boolean MemberCheckId(Model_Member model) {
		
		
		
	//  모델 데이터에 저장된 id값을 불러온다
		String id = model.getID();
		
		System.out.println("memberServiceImpl에 입력받은 id 값 : "+id);
		System.out.println();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
	
		try 
		{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select id from signup_g where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			System.out.println("try id :" + id);
			
			if(rs.next()) 
			{	
				
				System.out.println();
				System.out.println("memberServiceImpl의 rs.while문의 id:" +id);
				boolean chkresult = false;

			}
			else
			{	
		
				boolean chkresult = false;
				chkresult = checkmember.checkId(id);
				
				System.out.println();
				System.out.println("memberServiceImpl의 rs.next()값에 입력된 아이디 값 :" + id);
				
				if(chkresult) 
				{				
					System.out.println();
					System.out.println("memberServiceImpl의 chekreult true값에 입력된 결과 값 :" + id);
					check = true;
				}
				else
				{	
					System.out.println();
					System.out.println("memberServiceImpl의 chekreult false값에 입력된 결과 값 :" + id);
					check = false;
					
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			finnalMethod.rsMethod(conn, pstmt, rs);
		}
		System.out.println("memberServiceImpl의 rs.next()값에 입력된 결과 값 :" + check);
		
		return check;
	}
	@Override
	public String searchId(MyMemberDataBean member) {
		
		Model_Member model = new Model_Member();
		
		String email = member.getEmail();
		
		String dbid = null;
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		
		try 
		{
			System.out.println("멤버 서비스 임플의 이메일"+email);
			conn = getConnection();
			pstmt = conn.prepareStatement("Select id from signup_g where email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
		
			
			if(rs.next())
			{
				dbid = rs.getString("id");
			}
			else 
			{
				dbid = null;
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			finnalMethod.rsMethod(conn, pstmt, rs);
		}
		
		return dbid;
	}

	
	@Override
	public String searchPw(MyMemberDataBean member) {
		
		String id = member.getId();
		String email = member.getEmail();
		
		System.out.println("serchpw의 id :"+id);
		System.out.println("serchpw의 email :"+email);
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpw =  null;
		
		try 
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("Select passwd from signup_g where id = ? and email = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
			
				dbpw = rs.getString("passwd");
				
			}
			else 
			{
				dbpw = null;
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			finnalMethod.rsMethod(conn, pstmt, rs);
		}
		
		return dbpw;
	}
/*
	@Override
	public MyMemberDataBean getMember(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MyMemberDataBean member = null;
		
		try 
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("selet id,passwd,name,email from signup_g where id =?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				member = new MyMemberDataBean();
				member.setId("id");
				member.setPw("passwd");
				member.setName("name");
				member.setEmail("email");
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			finnalMethod.rsMethod(conn, pstmt, rs);
		}
		
		return member;
	}

	@Override
	public boolean updateMember(MyMemberDataBean member) {
			
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		boolean result = false;
		
		
		try 
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("")
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			finnalMethod.rsMethod(conn, pstmt, rs);
		}
		return result;
	}

	@Override
	public boolean deleteMember(String id, String pw) {
		
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		checkMember check = checkMemberImpl.getInstance();
		
	
		
		boolean result = false;
		
		try 
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select from signup_g where id = ? and passwd = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				String dbid = rs.getString("id");
				String dbpw = rs.getString("passwd");
				
				boolean checkMember = check.checkLogin(id, pw, dbid, dbpw);
				
				if(checkMember)
				{
					pstmt = conn.prepareStatement("delete from signup_g where id =? and passwd = ?");
					pstmt.setString(1, id);
					pstmt.setString(2, pw);
					pstmt.executeUpdate();
					result = true;
				}
				else 
				{
					result = false;
				}
			}
			else 
			{
				result = false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			finnalMethod.rsMethod(conn, pstmt, rs);
		}
		return result;
	}
*/
}

