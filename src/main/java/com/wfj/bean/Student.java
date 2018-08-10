package com.wfj.bean;

import java.sql.Date;

public class Student {
	private int sid;
	private String sname;
	private Date sbrith;
	private String sadress;
	private String ssex;
	private String slikes;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Date getSbrith() {
		return sbrith;
	}
	public void setSbrith(Date sbrith) {
		this.sbrith = sbrith;
	}
	public String getSadress() {
		return sadress;
	}
	public void setSadress(String sadress) {
		this.sadress = sadress;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getSlikes() {
		return slikes;
	}
	public void setSlikes(String slikes) {
		this.slikes = slikes;
	}
	public Student(int sid, String sname, Date sbrith, String sadress,
			String ssex, String slikes) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sbrith = sbrith;
		this.sadress = sadress;
		this.ssex = ssex;
		this.slikes = slikes;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
