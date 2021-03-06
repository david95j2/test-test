package com.sbs.example.testtest.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.testtest.dto.Member;

public class MemberDao {
	private List<Member> members;
	private int lastMemberId;
	public MemberDao() {
		lastMemberId=0;
		members = new ArrayList<>();
		
	}

	public void add(String loginId, String loginPw, String name) {
		Member member = new Member();
		member.loginId=loginId;
		member.loginPw=loginPw;
		member.name=name;
		member.num=lastMemberId+1;
		members.add(member);
		lastMemberId=member.num;
	}

	public boolean search(String loginId) {
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	public Member isIdTrue(String loginId) {
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public Member load(int id) {
		return members.get(id);
	}

	public List<Member> loadAllOfTheMember() {
		return members;
	}
}
