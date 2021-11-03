package controller;

// 자바 라이브러리 이용하기 위한 import
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

// servlet 라이브러리 이용하기 위한 import
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 게시판 기능을 구현하는 파일을 불러오는 import
import board.BoardService;
import board.BoardServiceImpl;
import checkAll.checkMember;
import checkAll.checkMemberImpl;
import board.BoardDataBean;

// 게시판 회원가입 기능울 구현하는 파일을 불러오는 import
import member.memberServiceImpl;
import member.memberService;
import member.MyMemberDataBean;

// 게시판 댓글 기능울 구현하는 파일을 불러오는 import

import reply.MyReplyDataBean;
import reply.ReplyService;
import reply.ReplyServiceImpl;


// 게시판 컨트롤러안에 사용할 변수들을 모델화[데이터와 비즈니스 로직을 관리]하여 데이터를 저장하고 불러오는 import
import Model.Model_Member;
/*
 기능을 구현할떄 사용하는 함수기능이  중복되지만 결과값이 다른 경우 
 [ex. idCheck기능을 가지고 있지만 로그인 시 idcheck와 회원 가입 시 idcheck는 기능은 비슷하지만 결과값이 다른 경우]

 [실패] 
이때  열거형[enumerator]으로  이름 지정 후  swich나 분기문으로 반환 값을 다르게 해서 기능을 구현한다. 

 */
import Model.Session_enum;

@WebServlet("/controller") 
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	/*컨트롤러의 명령을 수행하는 메소드*/
		doPostProcess(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	/*컨트롤러의 명령을 수행하는 메소드*/	
		doPostProcess(request, response);
	}

	
	private void doPostProcess(HttpServletRequest request, HttpServletResponse response) throws IOException
	{	
		/*doProcess의 request,response파라미터에 한글테이터를 사용하게 함*/
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		/*form문의 command를 통해 게시판의 명령을 받는 메소드*/
		String sessionName = request.getParameter("command");
		
		try {
		
			/*form문의 */
			Session_enum sessionCase = getSessionName(sessionName);
			
			
		/*
		 열거형으로 session 확인이 안되고 있기 때문에 이 열거형을 문자열로 변환하여 사용하는 방법을 생각하자 
		 */
			switch (sessionCase) 
			{
			case no_session:
				/*커맨드 값이 없을떄(초기로드, 회원가입 후)*/
				String path = request.getContextPath();
		        response.sendRedirect(path +"/memberLogin/Mylogin.jsp");
		        break;
			case check_id:
				/*check_id 값이 없을떄(초기로드, 회원가입 후)*/
				doCheckId(request, response);
				break;
			case login:
				doLogin(request, response);
				break;
			case search_id:
				doSearchId(request, response);
				break;
			case search_pw:
				doSearchPw(request, response);
				break;
			case sign_up:
				dosignUp(request, response);
				break;
			case logout:
				doLogout(request, response);
				break;	
			case contents:
				doContents(request, response);
				break;	
			case member_update:
				doSearchId(request, response);
				break;	
			case member_delete:
				doSearchPw(request, response);
				break;	
			case content_list:
				doBoardList(request, response);
				break;	
			case content_write:
				doBoardWrite(request, response);
				break;
			case content_getupdate:
				doBoardGetUpdate(request, response);
				break;	
			case content_update:
				doBoardUpdate(request, response);
				break;	
			case content_delet:
				doBoardDelete(request, response);
				break;	
			case reply_write:
				doWriteReply(request, response);
				break;	
			case reply_update:
				doUpdateReply(request, response);
				break;	
			case reply_delete:
				doDeleteReply(request, response);
				break;	
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	  private Session_enum getSessionName (String sessionname) 
	  {
		
		if(sessionname == null)
		{	
			return Session_enum.no_session;
		}
		else if (sessionname.equals("login"))
		{	
		
			return Session_enum.login;	
		}
		else if(sessionname.equals("checkId"))
		{	
		
			return Session_enum.check_id;
		}
		else if(sessionname.equals("signup"))
		{
			return Session_enum.sign_up;
		}
		else if (sessionname.equals("logout"))
		{
			return Session_enum.logout;
		}
		else if (sessionname.equals("searchId"))
		{
			return Session_enum.search_id;
		}
		else if (sessionname.equals("searchPw"))
		{
			return Session_enum.search_pw;
		}
		else if (sessionname.equals("list"))
		{
			return Session_enum.content_list;
		}
		else if (sessionname.equals("contents"))
		{
			return Session_enum.contents;
		}
		else if (sessionname.equals("content_write"))
		{
			return Session_enum.content_write;
		}
		else if (sessionname.equals("content_getupdate"))
		{
			return Session_enum.content_getupdate;
		}
		else if (sessionname.equals("content_update"))
		{
			return Session_enum.content_update;
		}
		else if (sessionname.equals("content_delet"))
		{
			return Session_enum.content_delet;
		}
		else if (sessionname.equals("reply_write"))
		{
			return Session_enum.reply_write;
		}
		else if (sessionname.equals("reply_update"))
		{
			return Session_enum.reply_update;
		}
		else if (sessionname.equals("reply_delete"))
		{
			return Session_enum.reply_delete;
		}
		else 
		{
		return Session_enum.no_session;
		}
	} 
	
	private boolean doLogin(HttpServletRequest request, HttpServletResponse response)
	{
		// 로그인 기능을 값을 반환하는 반환값
		boolean result = false;
		
		try {
			// 로그인 기능을 구현하기 위한 멤버DB와 메소드를 불러오기 위한 변수	
			MyMemberDataBean member = new MyMemberDataBean();
			memberService memberservice = memberServiceImpl.getInstance();
			
			// 파일의 경로를 지정하기 위한 변수
			String path = request.getContextPath();
			
			// Mylogin.jsp에서 form에 입력된 값을 가지고 오는 변수
			String id = request.getParameter("id");
			String pw = request.getParameter("passwd");
			
			// 값을 전달하기 위한 Model 클래스 생성
			Model_Member memberModel = new Model_Member();
			
			// model클래스로 값을 전송
			memberModel.SetCheckId(id);
			memberModel.SetCheckPw(pw);
		
			// PrintWriter에서 File(String), OutputStream, Writer 등의 객체를 인수로 받아 더 간편하게 스트림을 연결하는 메소드
			PrintWriter out = response.getWriter();
			
			
			
			boolean checkId = memberservice.MemberCheckId(memberModel);
			
			
			/*ID가 valid 할떄*/
			if(!checkId)
			{
				boolean login = memberservice.login(id, pw);
				
				/*로그인 성공 시*/
				if(login)
				{
					
					HttpSession session = request.getSession();
					request.setAttribute("login",login); 
					session.setAttribute("id", id);
					response.sendRedirect(path+"/boardContent/Mylist.jsp");
				}
				/*로그인 실패 시*/
				else 
				{
					/*로그인 페이지 반환*/
					out.println("<script>alert('아이디 혹은 패스워드 오류입니다.');</script>");
					response.sendRedirect(path+"/memberLogin/Mylogin.jsp");
				}
			}
			/*아이디가 Invalid 할 떄*/
			else 
			{
				out.println("<script>alert('아이디 혹은 패스워드 오류입니다.');</script>");
				response.sendRedirect(path+"/memberLogin/Mylogin.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 아이디 확인 기능을 가진 메소드
	private boolean doCheckId(HttpServletRequest request, HttpServletResponse response)
	{	
		// 로그인 기능을 값을 반환하는 반환값
		boolean check = false;
		
		try 
		{
			MyMemberDataBean member = new MyMemberDataBean();
			memberService memberservice = memberServiceImpl.getInstance();
			Model_Member model = new Model_Member();
			String path = request.getContextPath();
		
		
			String id = request.getParameter("id");
		
			model.setID(id);

			boolean checkId = memberservice.MemberCheckId(model);
		
			if(checkId)
				{
					request.setAttribute("checkId",checkId); 
					RequestDispatcher dispatcher = request.getRequestDispatcher("memberLogin/MyIdCheck.jsp"); // 서블릿 내에서 요청을 제어하기 때문에 path로 url(경로)를 따로 지정 하지 않아도 된다
					dispatcher.forward(request, response);
					check = true;
				}
			else
				{
					request.setAttribute("checkId",checkId);
					RequestDispatcher dispatcher = request.getRequestDispatcher("memberLogin/MyIdCheck.jsp"); // 서블릿 내에서 요청을 제어하기 때문에 path로 url(경로)를 따로 지정 하지 않아도 된다
					dispatcher.forward(request, response);
					check = false;
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return check;
	}
	
	private void dosignUp(HttpServletRequest request, HttpServletResponse response)
	{
		
		boolean result = false;
		
		try 
		{
		    PrintWriter out = response.getWriter();
		    
			
			MyMemberDataBean member = new MyMemberDataBean();
			memberService memberservice = memberServiceImpl.getInstance();
		
			String path = request.getContextPath();
		
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
		
			member.setId(id);
			member.setPw(pw);
			member.setName(name);
			member.setPhone(phone);
			member.setEmail(email);
			member.setJoindate(new Timestamp(System.currentTimeMillis()));
		
			memberservice.signUpMember(member);
			out.println("<script>('회원가입이 완료 되었습니다.');</script>");
			response.sendRedirect(path+"/memberLogin/Mylogin.jsp");
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	private void doLogout(HttpServletRequest request, HttpServletResponse response) 
	{	
		
		try
		{
			HttpSession session = request.getSession();
			String path = request.getContextPath();
			session = request.getSession();
			session.invalidate();
			response.sendRedirect(path+"/boardContent/Mylogin.jsp");
		}
		catch (Exception e) 
		{
				e.printStackTrace();

		}
	}
	private boolean doSearchId (HttpServletRequest request, HttpServletResponse response)
	{
		boolean check = false;

		try 
		{
			MyMemberDataBean member = new MyMemberDataBean();
			
			Model_Member model = new Model_Member();
			
			memberService memberservice = memberServiceImpl.getInstance();
			
			String path = request.getContextPath();
			String email = request.getParameter("email");
		
			member.setEmail(email);
			
			
			String searchid = memberservice.searchId(member);
			
			
			if(searchid != null)
			{	
				
				request.setAttribute("searchid",searchid);
				check = true;
				RequestDispatcher dispatcher = request.getRequestDispatcher("memberLogin/searchIdResult.jsp"); 
				dispatcher.forward(request, response);
			}
			else 
			{
				request.setAttribute("searchid",searchid);
				check = false;
				RequestDispatcher dispatcher = request.getRequestDispatcher("memberLogin/searchIdResult.jsp"); 
				dispatcher.forward(request, response);
			}		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return check;
	}
	private boolean doSearchPw (HttpServletRequest request, HttpServletResponse response)
	{
	boolean check = false;
		
		try 
		{
			MyMemberDataBean model = new MyMemberDataBean();
			memberService memberservice = memberServiceImpl.getInstance();
			String path = request.getContextPath();
			
			String email = request.getParameter("email");
			String id = request.getParameter("id");
		
			model.setEmail(email);
			model.setId(id);
			String searchpw = memberservice.searchPw(model);
			
			if(searchpw!=null)
			{	
				request.setAttribute("searchpw",searchpw);
				RequestDispatcher dispatcher = request.getRequestDispatcher("memberLogin/searchPwResult.jsp"); // 서블릿 내에서 요청을 제어하기 때문에 path로 url(경로)를 따로 지정 하지 않아도 된다
				dispatcher.forward(request, response);
				
			}
			else 
			{
				request.setAttribute("searchpw",searchpw);
				RequestDispatcher dispatcher = request.getRequestDispatcher("memberLogin/searchPwResult.jsp"); // 서블릿 내에서 요청을 제어하기 때문에 path로 url(경로)를 따로 지정 하지 않아도 된다
				dispatcher.forward(request, response);
			}		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return check;
	}
	
	public void doBoardList(HttpServletRequest request, HttpServletResponse response) 
	{
		String path = request.getContextPath();
		
		try 
		{
			response.sendRedirect(path + "/boardContent/Mylist.jsp");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
	
	private void doContents(HttpServletRequest request, HttpServletResponse response) 
	{
		
		
		String path = request.getContextPath();
		
		
		HttpSession session = request.getSession();
		
		BoardService boardservice = BoardServiceImpl.getInstance();
		
		ReplyService replyService = ReplyServiceImpl.getInstance();
		
		
			try 
			{
					int num = Integer.parseInt(request.getParameter("num"));

					BoardDataBean content = null;
					
					if ("yes".equals(session.getAttribute("readCount"))) 
					{
						content = boardservice.getArticle(num);
						session.removeAttribute("readCount");
					}
					else 
					{
						content = boardservice.getArticleNoCnt(num);
					}
					request.setAttribute("content", content);
					request.setAttribute("num", num); // 추가
					request.setAttribute("sessionId", session.getAttribute("id")); // 추가

					// 댓글 조회
					List<MyReplyDataBean> replyList = replyService.getAllReply(num);
					request.setAttribute("replyList", replyList);

					RequestDispatcher dispatcher = request.getRequestDispatcher("boardContent/Mycontent.jsp");
					dispatcher.forward(request, response);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	} 
	
	   private void doBoardWrite(HttpServletRequest request, HttpServletResponse response)
	   {
		   String path = request.getContextPath();
		   
		   HttpSession session = request.getSession();
			
		   BoardService boardservice = BoardServiceImpl.getInstance();
		   
		   BoardDataBean board = new BoardDataBean();
	      try 
	      {  
	    	  String id = request.getParameter("id");
	    	  String pw = request.getParameter("pw");
	    	  String subject = request.getParameter("subject");
	    	  String content = request.getParameter("content");
	    	  
	    	  board.setId(id);
	    	  board.setPasswd(pw);
	    	  board.setSubject(subject);
	    	  board.setContent(content);;
	    	  board.setReg_date(new Timestamp(System.currentTimeMillis()));
	    	  
	    	  boardservice.insertForm(board);
	    	  
	    	  response.sendRedirect(path + "/boardContent/Mylist.jsp");
	      }
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	   }

	   private void doBoardContents(HttpServletRequest request, HttpServletResponse response)
	   {   
	      BoardDataBean article = new BoardDataBean();
	      
	      BoardService boardPro = BoardServiceImpl.getInstance();
	      
	      String path = request.getContextPath();
	      
	      ReplyService replyPro = ReplyServiceImpl.getInstance();
	      
	      HttpSession session = request.getSession();
	      
	      try 
	      {
	         int num = Integer.parseInt(request.getParameter("num"));
	         
	         BoardDataBean content = null;
	      
	      if ("yes".equals(session.getAttribute("readCount"))) 
	      {
	         content = boardPro.getArticle(num);
	         session.removeAttribute("readCount");
	      } else 
	      {
	         content = boardPro.getArticleNoCnt(num);
	      }
	      request.setAttribute("content", content);
	      request.setAttribute("num", num); // 추가
	      request.setAttribute("sessionId", session.getAttribute("id")); // 추가

	      // 댓글 조회
	      List<MyReplyDataBean> replyList = replyPro.getAllReply(num);
	      request.setAttribute("replyList", replyList);
  

	      }
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	   }
	   private void doBoardGetUpdate(HttpServletRequest request, HttpServletResponse response)
	   {
	      BoardService boardPro= BoardServiceImpl.getInstance();
	      
	      String path = request.getContextPath();
	   
	      HttpSession session = request.getSession();
	      
	      try 
	      {
	         int num = Integer.parseInt(request.getParameter("num"));
	         
	         request.setAttribute("num", num);
	         
	         BoardDataBean getUpdate = boardPro.updateGetAricle(num);
	         
	         request.setAttribute("getUpdate", getUpdate);

	         RequestDispatcher dispatcher = request.getRequestDispatcher("boardContent/MyupdateForm.jsp");
	         dispatcher.forward(request, response);
	      } 
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	   }
	   
	   
	   private void doBoardUpdate(HttpServletRequest request, HttpServletResponse response) 
	   {
	      
	      BoardDataBean article = new BoardDataBean();
	      
	      BoardService boardPro= BoardServiceImpl.getInstance();
	      
	      String path = request.getContextPath();
   	      
	      HttpSession session = request.getSession();
	      
	
	      
	      try 
	      {
	         PrintWriter out = response.getWriter();
	    	  
	         int num = Integer.parseInt(request.getParameter("num"));
	         String id = request.getParameter("id");
	         String subject = request.getParameter("subject");
	         String content = request.getParameter("content");
	         String passwd = request.getParameter("passwd");

	         
	   
	         
	         article.setNum(num);
	         article.setId(id);
	         article.setSubject(subject);
	         article.setContent(content);
	         article.setPasswd(passwd);
	       

	         boolean updateChk = boardPro.updateArticle(article);

	         if (updateChk == true) {
	            out.println("<script>alert('수정되었습니다.'); location.href='controller?command=list'</script>");
	         } else if (updateChk == false) {
	            out.println("<script>alert('비밀번호가 틀렸습니다.'); window.history.back();</script>");
	         }
	      } 
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	   }
	   
	 

	   private void doBoardDelete(HttpServletRequest request, HttpServletResponse response)
	   {
	      BoardService boardPro= BoardServiceImpl.getInstance();
	      
	      String path = request.getContextPath();
	   
	      HttpSession session = request.getSession();
	      
	      try 
	      {
	         
	         PrintWriter out = response.getWriter();
	         
	         int num = Integer.parseInt(request.getParameter("num"));
	         String passwd = request.getParameter("passwd");
	         
	         System.out.println("boardDelete num :" + num);
	         System.out.println("boardDelete passwd :" + passwd);
	         
	         boolean deleteChk = boardPro.deleteContent(num, passwd);

	         if (deleteChk == true) 
	         {
	            out.println("<script>alert('삭제되었습니다.'); location.href='controller?command=list'</script>");
	         } 
	         else if (deleteChk == false) 
	         {
	            out.println("<script>alert('비밀번호가 틀렸습니다.'); window.history.back();</script>");
	         }
	      } 
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	   }
	   
	   public void doWriteReply(HttpServletRequest request, HttpServletResponse response)
	   {
		   
	      String path = request.getContextPath();
	   
	      HttpSession session = request.getSession();
	
	      MyReplyDataBean reply = new MyReplyDataBean();
	      
	      ReplyService replyPro = ReplyServiceImpl.getInstance();
	        
	      try 
	      {
	    	  
	         String comment = request.getParameter("comment");
	         
	         System.out.println(comment);
	         
	         int num = Integer.parseInt(request.getParameter("num"));
	         
	         String id = request.getParameter("sessionId");

	         
	         System.out.println("id 값 :"+id);
	         
	         System.out.println("doWriteReply에 들어오는 num의 값 : "+num);
	         
	         reply.setComment(comment);
	         reply.setReplyDate(new Timestamp(System.currentTimeMillis()));
	         reply.setId(id);
	         reply.setNum(num);
	   
	         
	         System.out.println(reply.getId());
	         
	         replyPro.insertReply(reply); 
	      }
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	      
	   }
	   public void doUpdateReply(HttpServletRequest request, HttpServletResponse response)
	   {
		  System.out.println("doUpdateReply에 진입함");
		   
	      String path = request.getContextPath();
	      
	      HttpSession session = request.getSession();
	      
	      MyReplyDataBean reply = new MyReplyDataBean();

	      ReplyService replyPro = ReplyServiceImpl.getInstance();
	      
	      try 
	      {
	         PrintWriter out = response.getWriter();
	         
	         int id_reply = Integer.parseInt(request.getParameter("id_reply"));
	         String replyModify = request.getParameter("replyModify");

	         replyPro.updateReply(id_reply, replyModify);
	         out.println(
	               "<script>alert('댓글이 수정되었습니다.'); opener.document.location.reload(); window.close();</script>");

	         out.flush();
	      } 
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	   }
	   
	   public void doDeleteReply(HttpServletRequest request, HttpServletResponse response)
	   {

	      String path = request.getContextPath();
	      
	      HttpSession session = request.getSession();
	      
	      MyReplyDataBean reply = new MyReplyDataBean();

	      ReplyService replyPro = ReplyServiceImpl.getInstance();
	      
	      System.out.println("dodelte메소드에 입력됨");
	      
	      try 
	      {
	      PrintWriter out = response.getWriter();
	      
	      String snum = request.getParameter("id_reply");	      
	      System.out.println("dodelte :"+snum);
	      
	      String tcomemet = request.getParameter("comment");	      
	      System.out.println("comment :"+snum);
	      
	      int contentNum = Integer.parseInt(request.getParameter("contentNum"));
	      int num = Integer.parseInt(request.getParameter("id_reply"));
	      replyPro.deleteReply(num);

	      out.println("<script>alert('정상적으로 삭제되었습니다.'); location.href='controller?command=contents&num="
	            + contentNum + "';</script>");
	      }
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	   
	   }
}


