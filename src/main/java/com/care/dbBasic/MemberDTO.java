package com.care.dbBasic;
/*
CREATE TABLE session_exam(
id varchar2(20),
pw varchar2(20),
name varchar2(21),
email varchar2(200),
PRIMARY KEY(id)
);
*/
public class MemberDTO {

	private String id;
	private String pw;
	private String name;
	private String email;
	
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

	
}