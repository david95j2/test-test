package com.sbs.example.testtest;

import com.sbs.example.testtest.container.Container;
import com.sbs.example.testtest.controller.ArticleController;
import com.sbs.example.testtest.controller.Controller;
import com.sbs.example.testtest.controller.MemberController;

public class App {
	private MemberController memberController;
	private ArticleController articleController;
	public App() {
		memberController=Container.memberController;
		articleController=Container.articleController;
		makeTestData();
	}

	private void makeTestData() {
		Container.memberService.join("user1","user1","캥거루");
		Container.memberService.join("user2","user2","사자");
		Container.memberService.join("user3","user3","호랑이");	
		
		Container.articleService.makeBoard("공지사항");
		Container.session.selectedBoardId=1;
		
		for (int i = 0; i <= 6; i++) {
			Container.articleService.write(1, "공지사항" + (i + 1), "내용" + (i + 1), 0);
		}
		for (int i = 7; i <= 13; i++) {
			Container.articleService.write(2, "자유" + (i + 1), "내용" + (i + 1), 1);
		}
		
	}

	public void run() {
		while(true) {
		System.out.printf("명령어 : ");
		String cmd = Container.sc.nextLine();
		if(cmd.equals("system exit")) {
			System.out.println("프로그램 종료");
			break;
		}
		Controller controller =whatKindOfController(cmd);
		try {
			controller.run(cmd);
		}
		catch (NullPointerException e) {
			System.out.println("알 수 없는 명령어");
			continue;
		}
		}
	}

	private Controller whatKindOfController(String cmd) {
		if(cmd.startsWith("member ")) {
			return memberController;
		}
		if(cmd.startsWith("article ")) {
			return articleController;
		}
		return null;
	}
}
