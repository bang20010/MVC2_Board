package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import checkAll.checkMember;
import checkAll.checkMemberImpl;
import finallymethod.finalMethod;
import finallymethod.finalMethodImpl;
import member.MyMemberDataBean;

// connction을 인터페이스로 산출할수 있는가?

public class BoardServiceImpl implements BoardService{
	
	// MyBoardDBBean를 싱글톤 패턴으로 디자인
	private static BoardServiceImpl instance=new BoardServiceImpl();
	
	public static BoardServiceImpl getInstance() 
	{
		return instance;
	}
	  
	// finally에 conn,pstmt,rs를 처리하는 메소드
	 finalMethod finalmethod = new finalMethodImpl();
	
	private BoardServiceImpl() {}
	
	// DB와 연결하는 Connection 메소드
	private Connection getConnection() throws Exception  
	{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/basicjsp");
		return ds.getConnection();
	}
	  
	  // 게시판글을 작성하는 메소드
	  @Override
	  public void insertForm(BoardDataBean article) 
	  {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	    	  
	         conn = getConnection();
	         String sql="insert into board_g(num,id,subject,passwd,reg_date,content) values(seq_board_g.nextval,?,?,?,?,?)";
	         pstmt = conn.prepareStatement(sql);
	         
	         SimpleDateFormat dbdate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	         
	         System.out.println("insertForm안에 Timestamp값 "+dbdate.format(article.getReg_date()));
	         
	         pstmt.setString(1,article.getId());
	         pstmt.setString(2,article.getSubject());
	         pstmt.setString(3, article.getPasswd());
	         pstmt.setTimestamp(4,article.getReg_date());
	         pstmt.setString(5, article.getContent());
	         
	         
	         pstmt.executeUpdate();
	         
	      } 
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	      finally 
	      {
	    	finalmethod.pstmtMethod(conn, pstmt);  
	      }
	   }
	  
	  // 조회수를 증가시키는 메소드
	  @Override
	  public int count() {
		  int count = 0;
		  
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  try {
			  conn = getConnection();
			  pstmt = conn.prepareStatement("select count(*) from board_g");
			  rs = pstmt.executeQuery();
			  
			  if(rs.next()) {
				  count = rs.getInt(1);
			  }
		  	}catch (Exception e) {
			  e.printStackTrace();
		  	}finally {
		  		finalmethod.rsMethod(conn, pstmt, rs);
			}
		  
		 
		  return count;
	  }
	  
	  // 게시판 글 목록을 작성해주는 메소드
	  @Override
	  public List<BoardDataBean> getAll(int start, int end){
	         Connection conn = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         List<BoardDataBean> boardList = new ArrayList<BoardDataBean>();
	         try {
	            conn = getConnection();
	            
	            // 글 리스트를 순서대로 정리 시키는 쿼리문
	            String sql = "select * from (select rownum as rnum, a.* from (select * from board_g order by reg_date desc) a where rownum <=?) where rnum > ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, end);
	            pstmt.setInt(2, start-1);
	            rs = pstmt.executeQuery();
	            
	            while (rs.next())
	            {
	               BoardDataBean board = new BoardDataBean();
	               board.setNum(rs.getInt("num"));
	               board.setId(rs.getString("id"));
	               board.setSubject(rs.getString("subject"));
	               board.setContent(rs.getString("content"));
	               board.setPasswd(rs.getString("passwd"));
	               board.setReg_date(rs.getTimestamp("reg_date"));
	               board.setReadcount(rs.getInt("readCount"));
	               
	               boardList.add(board);
	            }
	         }
	         catch(Exception e) 
	         {
	            e.printStackTrace();
	         }
	         finally 
	         {
	        	 finalmethod.rsMethod(conn, pstmt, rs);
	         }
	         
	         return boardList;
	      }
	  
	  @Override
	  public BoardDataBean getArticle(int num) throws Exception 
	  {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardDataBean article = null;
			try 
			{
				conn = getConnection();

				pstmt = conn.prepareStatement("update board_g set readcount=readcount+1 where num = ?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();

				pstmt = conn.prepareStatement("select * from board_g where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();

				if (rs.next())
				{
					article = new BoardDataBean();
					article.setNum(rs.getInt("num"));
					article.setId(rs.getString("id"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			} 
			finally 
			{
				finalmethod.rsMethod(conn, pstmt, rs);
			}
			return article;
		}
	  
	  @Override
	  public BoardDataBean updateGetAricle(int num) throws Exception
	  {
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs =null;
		  BoardDataBean article=null;
		  
		  try 
		  {
			  conn = getConnection();
			  pstmt = conn.prepareStatement("select id,passwd,subject,content from board_g where num = ? ");
			  pstmt.setInt(1, num);
			  rs = pstmt.executeQuery();
			  
			  if(rs.next()) 
			  {
				  article = new BoardDataBean();
				  article.setId(rs.getString("id"));
				  article.setSubject(rs.getString("subject"));
				  article.setPasswd(rs.getString("passwd"));
				  article.setContent(rs.getString("content"));
			  }
			
		} 
		catch (Exception e) 
		  {
			  e.printStackTrace();
		}
		finally 
		{
			finalmethod.rsMethod(conn, pstmt, rs);
		} 
		  return article;
	  }
	  
	  @Override
	  public boolean updateArticle(BoardDataBean article) throws Exception
	  {
		  Connection conn =null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  checkMember check = checkMemberImpl.getInstance();
		  
		  System.out.println("updateArticle 저장된 아이디 : "+article.getId()); 
		  System.out.println("updateArticle 저장된 게시판 글번호 : "+article.getNum()); 
		  System.out.println("updateArticle 저장된 내용 : "+article.getContent()); 
		  System.out.println("updateArticle 저장된 비밀번호 : "+article.getPasswd());
		  System.out.println("updateArticle 저장된 제목 : "+article.getSubject());
		  
		  String pw = article.getPasswd();
		  
		  String dbpasswd = "";
		 
			 
			 
		  boolean comfrim = true;
		  
		  try 
		  {
			conn = getConnection();		// Connection객체를 DB에 연동
			pstmt = conn.prepareStatement("select passwd from board_g where num= ?"); // DB안의 쿼리문을 완성하기 위한 코드
			pstmt.setInt(1, article.getNum()); // 쿼리문을 완성 후
			rs = pstmt.executeQuery(); // Resultset에다가  완성된 쿼리문[select passwd from board_g where = ?]을 저장하는 코드이다.
			
			if(rs.next()) { // rs.next()는 위의 완성된 커리문 중에 즉, select passwd from board_g where = ? 범위 안에 있는 것을 의미 한다.
				
				dbpasswd = rs.getString("passwd");
				System.out.println("try안에 dapasswd : "+dbpasswd);
				
				boolean checkResult = check.doEqulPw(pw, dbpasswd);
				
				if(checkResult) 
				{
					
				 String	sql ="update board_g set id = ? , subject = ?, passwd =?, content = ? where num =?";
					
				   	pstmt = conn.prepareStatement(sql); 
					pstmt.setString(1, article.getId());
					pstmt.setString(2, article.getSubject());
					pstmt.setString(3, article.getPasswd());
					pstmt.setString(4, article.getContent());
					pstmt.setInt(5, article.getNum());
					pstmt.executeUpdate();
					comfrim = true;	
				}
			}
			else
			{
				comfrim = false;
			}
		}
		catch (Exception e) 
		{
		  e.printStackTrace();	
		}
		finally 
		{
			finalmethod.rsMethod(conn, pstmt, rs);
		}
		  return comfrim;
	  }
	  
	  	@Override
		public boolean deleteContent(int num, String passwd) 
	  	{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			boolean comfrim = false;
			
			String dbpasswd = "";
			try 
			{
				conn = getConnection();
				pstmt = conn.prepareStatement("select passwd from board_g where num=?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				while (rs.next()) 
				{
					dbpasswd = rs.getString("passwd");
					
					if (dbpasswd.equals(passwd)) 
					{
						pstmt = conn.prepareStatement("delete from board_g where num=?");
						pstmt.setInt(1, num);
						pstmt.executeUpdate();
						comfrim = true; // 삭제성공
					}
					else
						comfrim = false;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
			finally 
			{
				finalmethod.rsMethod(conn, pstmt, rs);
					
			}
			return comfrim;
		}
	  	
	  	@Override
		public BoardDataBean getArticleNoCnt(int num) throws Exception 
	  	{
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      BoardDataBean article = null;
		      try 
		      {
		         conn = getConnection();

		         pstmt = conn.prepareStatement("select * from board_g where num = ?");
		         pstmt.setInt(1, num);
		         rs = pstmt.executeQuery();

		         if (rs.next()) 
		         {
		            article = new BoardDataBean();
		            article.setNum(rs.getInt("num"));
		            article.setId(rs.getString("id"));
		            article.setSubject(rs.getString("subject"));
		            article.setPasswd(rs.getString("passwd"));
		            article.setReg_date(rs.getTimestamp("reg_date"));
		            article.setReadcount(rs.getInt("readcount"));
		            article.setContent(rs.getString("content"));
		         }
		      }
		      catch (Exception ex) 
		      {
		         ex.printStackTrace();
		      }
		      finally 
		      {
		    	  finalmethod.rsMethod(conn, pstmt, rs);
		      }
		      return article;
		   }

		

}
