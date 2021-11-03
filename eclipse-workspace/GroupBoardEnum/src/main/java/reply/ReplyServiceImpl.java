package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Model.Model_Board;
import Model.Model_Reply;
import finallymethod.finalMethod;
import finallymethod.finalMethodImpl;

public class ReplyServiceImpl implements ReplyService{

	private static ReplyServiceImpl instance = new ReplyServiceImpl();
	
	finalMethod finalmethod = new finalMethodImpl();
	
	public static ReplyServiceImpl getInstance() {
		return instance;
	}

	private ReplyServiceImpl() {}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/basicjsp");
		return ds.getConnection();
	}
	
	@Override
	public void insertReply(MyReplyDataBean reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			conn = getConnection();
			String sql = "insert into reply_g values(?,?,?,?,seq_reply_g.nextval)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getId());
			pstmt.setInt(2, reply.getNum());
			pstmt.setString(3, reply.getComment());
			pstmt.setTimestamp(4, reply.getReplyDate());
			
			System.out.println(reply.getId_reply());
			
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
	
	@Override	
	public List<MyReplyDataBean> getAllReply(int num)
	{
	      List<MyReplyDataBean> replyList = new ArrayList<MyReplyDataBean>();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try 
	      {
	         conn = getConnection();
	         String sql = "select * from reply_g where num=? order by id_reply desc";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, num);
	         rs = pstmt.executeQuery(); // 오류
	         
	         while (rs.next()) {
	        	 MyReplyDataBean reply = new MyReplyDataBean();
	            reply.setSessionId(rs.getString("id"));
	            reply.setNum(rs.getInt("num"));
	            reply.setComment(rs.getString("coment"));
	            reply.setReplyDate(rs.getTimestamp("replydate"));
	            reply.setId_reply(rs.getInt("id_reply"));
	            
	            replyList.add(reply);
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
	      return replyList;
	   }
	
	@Override
	public void deleteReply(int num) 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try 
		{
			conn = getConnection();
			pstmt = conn.prepareStatement( "delete from reply_g where id_reply=?");
			pstmt.setInt(1, num);
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
	
	@Override
	public void updateReply(int id_reply, String comment) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try
	      {
	         conn = getConnection();
	         pstmt = conn.prepareStatement("update reply_g set coment=? where id_reply=?");
	         pstmt.setString(1, comment);
	         pstmt.setInt(2, id_reply);
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
}
