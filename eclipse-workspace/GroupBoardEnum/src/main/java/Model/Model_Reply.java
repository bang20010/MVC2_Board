package Model;

import java.sql.Timestamp;

public class Model_Reply {
	private String id;
	private int num;
	private String comment;
	private Timestamp replyDate;
	private int id_reply;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
	public int getId_reply() {
		return id_reply;
	}
	public void setId_reply(int id_reply) {
		this.id_reply = id_reply;
	}
}
