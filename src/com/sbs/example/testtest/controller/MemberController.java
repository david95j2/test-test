package com.sbs.example.testtest.controller;

import com.sbs.example.testtest.container.Container;
import com.sbs.example.testtest.dto.Member;
import com.sbs.example.testtest.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public void run(String cmd) {
		if (cmd.equals("member join")) {
			String loginId = "";
			String loginPw;
			String name;
			int loginCount = 0;
			int loginMaxCount = 3;
			boolean isIdFine = false;
			System.out.println("== 회원 가입 ==");

			while (true) {
				if (loginCount >= loginMaxCount) {
					System.out.println("가입을 취소합니다.");
					break;
				}
				System.out.printf("Id : ");
				loginId = Container.sc.nextLine().trim();
				if (loginId.length() == 0) {
					System.out.println("아이디를 입력하세요.");
					loginCount++;
					continue;
				}
				if (memberService.beDuplicate(loginId) == false) {
					System.out.println("이미 사용중인 아이디");
					loginCount++;
					continue;
				}
				isIdFine = true;
				break;
			}
			if (isIdFine == false) {
				return;
			}
			while (true) {
				System.out.printf("Pw : ");
				loginPw = Container.sc.nextLine().trim();
				if (loginPw.length() == 0) {
					System.out.println("비밀번호를 입력하세요.");
					continue;
				}
				break;
			}
			while (true) {
				System.out.printf("name : ");
				name = Container.sc.nextLine().trim();
				if (name.length() == 0) {
					System.out.println("이름을 입력하세요.");
					continue;
				}
				break;
			}
			memberService.join(loginId, loginPw, name);
			System.out.printf("[%s님] : 가입되었습니다.\n", name);
		}
		else if(cmd.equals("member login")) {
			if(Container.session.logIn()) {
				System.out.println("로그인 되어있습니다.");
				return;
			}
			String loginId = "";
			String loginPw;
			int loginCount = 0;
			int loginMaxCount = 3;
			boolean isIdFine = false;
			Member member = null;
			System.out.println("== 로그인 ==");

			while (true) {
				if (loginCount >= loginMaxCount) {
					System.out.println("로그인을 취소합니다.");
					break;
				}
				System.out.printf("Id : ");
				loginId = Container.sc.nextLine().trim();
				if (loginId.length() == 0) {
					System.out.println("아이디를 입력하세요.");
					loginCount++;
					continue;
				}
				member = memberService.isIdTrue(loginId);
				if (member == null) {
					System.out.println("아이디가 없습니다.");
					loginCount++;
					continue;
				}
				isIdFine = true;
				break;
			}
			if (isIdFine == false) {
				return;
			}
			int loginPwCount = 0;
			int loginPwMaxCount = 3;
			boolean isPwFine = false;
			while (true) {
				if(loginPwCount>=loginPwMaxCount) {
					System.out.println("로그인 취소");
					break;
				}
				System.out.printf("Pw : ");
				loginPw = Container.sc.nextLine().trim();
				if (loginPw.length() == 0) {
					System.out.println("비밀번호를 입력하세요.");
					loginPwCount++;
					continue;
				}
				if(member.loginPw.equals(loginPw)==false) {
					System.out.println("비밀번호가 일치하지 않습니다.");
					loginPwCount++;
					continue;
				}
				isPwFine=true;
				break;
			}
			if(isPwFine==false) {
				return;
			}
			System.out.printf("[%s님] : 로그인되었습니다.\n",member.name);
			Container.session.loginedMemberId=member.num;
		}
		if(cmd.equals("member whoami")) {
			if(Container.session.logOut()) {
				System.out.println("로그인 후 이용가능");
				return;
			}
			int id = Container.session.loginedMemberId-1;
			Member member = memberService.getMember(id);
			System.out.println("== 회원정보 ==");
			System.out.printf("회원번호 : %d번\n",member.num);
			System.out.printf("아이디 : %s\n",member.loginId);
			System.out.printf("이름 : %s\n",member.name);
		}
		if(cmd.equals("member logout")) {
			if(Container.session.logOut()) {
				System.out.println("로그인 되어있지 않습니다.");
				return;
			}
			int id = Container.session.loginedMemberId-1;
			Member member = memberService.getMember(id);
			System.out.printf("[%s님] : 로그아웃되었습니다.\n",member.name);
			Container.session.loginedMemberId=0;
		}
	}
}
