package com.cisco.cre.bean;

import org.springframework.beans.factory.annotation.Value;

import com.cisco.cre.util.LogUtil;

public class My {

	@Value( "${my.name}" )
	private String myname;
	
	@Value( "${my.age}" )
	private String myage;
	
	
	public My() {
		LogUtil.debug(this, "************** Loading Now ******************");
	}
	
	public String getMyname() {
		return myname;
	}
	public void setMyname(String myname) {
		this.myname = myname;
	}
	public String getMyage() {
		return myage;
	}
	public void setMyage(String myage) {
		this.myage = myage;
	}
	@Override
	public String toString() {
		return "My [myname=" + myname + ", myage=" + myage + "]";
	}
	
	
}
