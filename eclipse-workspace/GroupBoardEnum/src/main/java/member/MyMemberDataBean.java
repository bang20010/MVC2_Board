package member;

import java.sql.Timestamp;

public class MyMemberDataBean {
   private String id;
   private String pw;
   private String phone;
   private String name;
   private String email;
   private Timestamp joindate;
   
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPw() {
      return pw;
   }
   public void setPw(String pw) {
      this.pw = pw;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public Timestamp getJoindate() {
      return joindate;
   }
   public void setJoindate(Timestamp joindate) {
      this.joindate = joindate;
   }
   
   
}