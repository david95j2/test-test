package com.sbs.example.testtest.session;

public class Session {
	public int loginedMemberId;
	
	public boolean isLogined() {
		return loginedMemberId !=0;
	}
	public boolean isLogOuted() {
		return !isLogined();
	}
}
