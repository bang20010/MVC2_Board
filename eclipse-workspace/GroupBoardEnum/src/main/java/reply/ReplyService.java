package reply;
import java.util.List;

import Model.Model_Reply;

public interface ReplyService {
	
	public void insertReply(MyReplyDataBean reply);
	public List<MyReplyDataBean> getAllReply(int num);
	public void deleteReply(int num);
	public void updateReply(int id_reply, String comment);
}
