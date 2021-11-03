package board;

import java.util.List;

import member.MyMemberDataBean;

public interface BoardService {
	
	public void insertForm(BoardDataBean article);
	public int count();
	public List<BoardDataBean> getAll(int start, int end);
	public BoardDataBean getArticle(int num) throws Exception;
	public BoardDataBean updateGetAricle(int num) throws Exception;
	public boolean updateArticle(BoardDataBean article) throws Exception;
	public boolean deleteContent(int num, String passwd);
	public BoardDataBean getArticleNoCnt(int num) throws Exception;
}
