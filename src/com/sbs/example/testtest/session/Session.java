package com.sbs.example.testtest.session;

public class Session {
	public int loginedMemberId;
	public int selectedBoardId;
	public Session() {
		loginedMemberId=0;
		selectedBoardId=0;
	}
	public boolean logIn() {
		return loginedMemberId !=0;
	}
	public boolean logOut() {
		return !logIn();
	}
}
