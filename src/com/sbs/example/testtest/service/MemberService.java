package com.sbs.example.testtest.service;

import java.util.List;

import com.sbs.example.testtest.container.Container;
import com.sbs.example.testtest.dao.MemberDao;
import com.sbs.example.testtest.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao=Container.memberDao;
	}

	public void join(String loginId, String loginPw, String name) {
		
		memberDao.add(loginId,loginPw,name);
		
	}

	public boolean beDuplicate(String loginId) {
		return memberDao.search(loginId);
	}

	public Member isIdTrue(String loginId) {
		return memberDao.isIdTrue(loginId);
	}

	public Member getMember(int id) {
		return memberDao.load(id);
	}

	public List<Member> getMembers() {
		return memberDao.loadAllOfTheMember();
	}
}
