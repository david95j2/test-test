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
