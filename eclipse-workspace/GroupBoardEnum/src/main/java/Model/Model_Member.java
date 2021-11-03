package Model;

import java.security.Timestamp;

import regex.regEx;

public class Model_Member 
{
	
	private String ID;
	private String PW;
	private String PHONE;
	private String NAME;
	private String EMAIL;
	private Timestamp JOINDATE;
	
	regEx regex = new regEx();
	
	public Model_Member() 
	{
			ID = new String();
			PW = new String();
			PHONE = new String();
			NAME = new String();
			EMAIL = new String();
	}
	
	public boolean SetCheckId(String id)
	{	
		ID = id;
		if(ID == null)
		{	
			return false;
		}
		else if(ID == "")
		{
			return false;
		}
		/*
		 정규식이 작동하지 않음
		else if(!regex.isSpace(ID))
		{
			System.out.println("모델 아이디에 입력받은 값에 빈칸이 있습니다.");
			return false;
		}
		*/
		else
		{
			return true;
		}		
	}
	public boolean SetCheckPw(String pw) 
	{
		PW = pw;
		if(PW == null)
		{
			return false;
		}
		else if(PW == "")
		{
			return false;
		}
		else 
		{
			return true;	
		}	
	}
	public boolean SetCheckName(String name) 
	{
		NAME = name;
		if(NAME == null)
		{
			return false;
		}
		else if(NAME == "")
		{
			return false;
		}
		else 
		{
			return true;	
		}	
		
	}
	public boolean SetCheckPhone(String phone) 
	{
		PHONE = phone;
		if(PHONE == null)
		{
			return false;
		}
		else if(PHONE == "")
		{
			return false;
		}
		else 
		{
			return true;	
		}	
	}
	public boolean SetCheckEmail(String email) 
	{
		EMAIL = email;
		if(EMAIL == null)
		{
			return false;
		}
		else if(EMAIL == "")
		{
			return false;
		}
		else 
		{
			return true;	
		}	
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public Timestamp getJOINDATE() {
		return JOINDATE;
	}

	public void setJOINDATE(Timestamp joindate) {
		JOINDATE = joindate;
	}
	public String getPw() 
	{
		return PW;
	}
}
