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
			boolean beFine = false;
			System.out.println("== 회원가입 ==");
			while (true) {
				if (loginCount >= loginMaxCount) {
					System.out.println("회원가입을 취소합니다");
					break;
				}
				System.out.printf("Id : ");
				loginId = Container.sc.nextLine().trim();
				if (loginId.length() == 0) {
					System.out.println("아이디를 입력해주세요");
					loginCount++;
					continue;
				}
				if (memberService.isItTrue(loginId) == false) {
					System.out.println("사용중인 아이디");
					loginCount++;
					continue;
				}
				beFine = true;
				break;
			}
			if (beFine == false) {
				return;
			}
			while (true) {
				System.out.printf("Pw : ");
				loginPw = Container.sc.nextLine().trim();
				if (loginPw.length() == 0) {
					System.out.println("비밀번호를 입력해주세요");
					continue;
				}
				break;
			}
			while (true) {
				System.out.printf("name : ");
				name = Container.sc.nextLine().trim();
				if (name.length() == 0) {
					System.out.println("이름을 입력해주세요");
					continue;
				}
				break;
			}
			int id = memberService.join(loginId, loginPw, name);
			System.out.printf("%d번 회원으로 가입되었습니다. 환영합니다\n", id);
		}
		else if (cmd.equals("member login")) {
			String loginId = "";
			String loginPw;
			int loginCount = 0;
			int loginMaxCount = 3;
			boolean beFine = false;
			Member member = null;
			System.out.println("== 로그인 ==");
			while (true) {
				if (loginCount >= loginMaxCount) {
					System.out.println("회원가입을 취소합니다");
					break;
				}
				System.out.printf("Id : ");
				loginId = Container.sc.nextLine().trim();
				if (loginId.length() == 0) {
					System.out.println("아이디를 입력해주세요");
					loginCount++;
					continue;
				}
				member = memberService.getMember(loginId);
				if (member== null) {
					System.out.println("아이디가 없습니다.");
					loginCount++;
					continue;
				}
				beFine = true;
				break;
			}
			if (beFine == false) {
				return;
			}
			boolean beFinePw=false;
			while (true) {
				if(loginCount>=loginMaxCount) {
					System.out.println("로그인을 취소합니다.");
					break;
				}
				System.out.printf("Pw : ");
				loginPw = Container.sc.nextLine().trim();
				if (loginPw.length() == 0) {
					System.out.println("비밀번호를 입력해주세요");
					loginCount++;
					continue;
				}
				if(member.loginPw.equals(loginPw)==false) {
					System.out.println("비밀번호가 틀렸습니다.");
					loginCount++;
					continue;
				}
				beFinePw=true;
				break;
			}
			if(beFinePw==false) {
				return;
			}
			Container.session.loginedMemberId=member.num;
			System.out.printf("[%s님] : 로그인되었습니다.\n", member.name);
		}
		else if(cmd.equals("member whoami")) {
			if(Container.session.isLogOuted()) {
				System.out.println("로그인 되어있지 않습니다.");
				return;
			}
			int id = Container.session.loginedMemberId;
			Member member = memberService.getMemberBySession(id);
			System.out.println("== 회원 정보 ==");
			System.out.printf("회원번호  : %d\n",member.num);
			System.out.printf("아이디  : %s\n",member.loginId);
			System.out.printf("이름  : %s\n",member.name);
		}
		else if(cmd.equals("member logout")) {
			if(Container.session.isLogOuted()) {
				System.out.println("로그인 되어있지 않습니다.");
				return;
			}
			int id = Container.session.loginedMemberId;
			Member member = memberService.getMemberBySession(id);
			System.out.printf("[%s님] : 로그아웃 되었습니다.\n",member.name);
			Container.session.loginedMemberId=0;
		}
	}

}
