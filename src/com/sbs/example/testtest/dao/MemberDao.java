package com.sbs.example.testtest.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.testtest.dto.Member;

public class MemberDao {
	private int lastMemberId;
	private List<Member> members;

	public MemberDao() {
		lastMemberId = 0;
		members = new ArrayList<>();
		
		//테스트
		addtestMember();
		
	}
	private void addtestMember() {
		add("user1", "user1", "캥거루");
		add("user2", "user2", "코끼리");
		add("user3", "user3", "개미");
	}

	public int add(String loginId, String loginPw, String name) {
		Member member = new Member();
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;
		member.num = lastMemberId + 1;
		members.add(member);
		lastMemberId = member.num;
		return member.num;
	}

	public boolean search(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	public Member getMember(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public Member getMemberBySession(int id) {
		for (Member member : members) {
			if (member.num == id) {
				return member;
			}
		}
		return null;
	}

}
