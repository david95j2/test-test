package com.sbs.example.testtest.service;

import com.sbs.example.testtest.container.Container;
import com.sbs.example.testtest.dao.MemberDao;
import com.sbs.example.testtest.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao=Container.memberDao;
	}
	
	public int join(String loginId, String loginPw, String name) {
		return memberDao.add(loginId,loginPw,name);
	}

	public boolean isItTrue(String loginId) {
		return memberDao.search(loginId);
	}

	public Member getMember(String loginId) {
		return memberDao.getMember(loginId);
	}

	public Member getMemberBySession(int id) {
		return memberDao.getMemberBySession(id);
	}
}
