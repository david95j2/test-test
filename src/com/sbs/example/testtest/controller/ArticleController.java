package com.sbs.example.testtest.controller;

import com.sbs.example.testtest.container.Container;
import com.sbs.example.testtest.dto.Article;
import com.sbs.example.testtest.dto.Member;
import com.sbs.example.testtest.service.ArticleService;
import com.sbs.example.testtest.service.MemberService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private MemberService memberService;
	public ArticleController() {
		articleService = Container.articleService;
		memberService=Container.memberService;
	}

	public void run(String cmd) {

		if (cmd.equals("article add")) {
			System.out.println("== 게시물 등록 ==");
			if (Container.session.isLogOuted()) {
				System.out.println("로그인 후 이용해주세요");
				return;
			}
			System.out.printf("제목 : ");
			String title = Container.sc.nextLine();
			System.out.printf("내용 : ");
			String body = Container.sc.nextLine();
			
			int memberId = Container.session.loginedMemberId;
			int id = articleService.add(title, body, memberId);
			System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
			
		} else if (cmd.startsWith("article list ")) {
			int page = 1;
			
			try {
				page = Integer.parseInt(cmd.split(" ")[2]);
			} catch (NumberFormatException e) {
				System.out.println("번호를 양의정수로 입력하세요");
				return;
			} catch (ArrayIndexOutOfBoundsException e) {
				page = 1;
			}
			
			if (page <= 1) {
				page = 1;
			}
			int pageList = 10;
			int startPage = articleService.articlesSize() - 1;
			startPage -= (page - 1) * pageList;
			int endPage = startPage - (pageList - 1);
			if(endPage<0) {
				endPage=0;
			}
			if(startPage<endPage) {
				System.out.println("게시물 없음");
				return;
			}
			
			System.out.println("== 게시물 조회 ==");
			System.out.println("번호 / 작성자 / 제목");
			
			for(int i=startPage;i>=endPage;i--) {
				Article article = articleService.getArticles(i);
				Member member = memberService.getMemberBySession(article.memberId);
				System.out.printf("%d / %s / %s\n",article.id,member.name,article.title);
			}
		}
		else if (cmd.startsWith("article detail ")) {
			int inputedId;
			
			try {
			inputedId=Integer.parseInt(cmd.split(" ")[2])-1;
			}
			catch(NumberFormatException e) {
				System.out.println("찾으려는 글을 양의정수로 입력해주세요");
				return;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("번호를 입력하세요.");
				return;
			}
			if(articleService.articlesSize()==0) {
				System.out.println("글이 없습니다.");
				return;
			}
			if(articleService.articlesSize()<=inputedId) {
				System.out.println("글이 없습니다.");
				return;
			}
			Article article = articleService.getArticles(inputedId);
			Member member = memberService.getMemberBySession(article.memberId);
			System.out.printf("번호 : %d번 글\n",article.id);
			System.out.printf("작성자 : %s\n",member.name);
			System.out.printf("제목 : %s\n",article.title);
			System.out.printf("내용 : %s\n",article.body);
		}
		else if (cmd.startsWith("article modify ")) {
			int inputedId;
			
			try {
			inputedId=Integer.parseInt(cmd.split(" ")[2])-1;
			}
			catch(NumberFormatException e) {
				System.out.println("글 번호를 양의정수로 입력해주세요");
				return;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("번호를 입력하세요.");
				return;
			}
			if(articleService.articlesSize()==0) {
				System.out.println("글이 없습니다.");
				return;
			}
			if(articleService.articlesSize()<inputedId) {
				System.out.println("글이 없습니다.");
				return;
			}
			System.out.printf("제목 : ");
			String title=Container.sc.nextLine();
			System.out.printf("내용 : ");
			String body=Container.sc.nextLine();
			Article article = articleService.getArticles(inputedId);
			article.title=title;
			article.body=body;
			System.out.printf("%d번 글이 수정되었습니다.\n",article.id);
		}
		else if (cmd.startsWith("article remove ")) {
			int inputedId;
			
			try {
			inputedId=Integer.parseInt(cmd.split(" ")[2])-1;
			}
			catch(NumberFormatException e) {
				System.out.println("글 번호를 양의정수로 입력해주세요");
				return;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("번호를 입력하세요.");
				return;
			}
			if(articleService.articlesSize()==0) {
				System.out.println("글이 없습니다.");
				return;
			}
			if(articleService.articlesSize()<inputedId) {
				System.out.println("글이 없습니다.");
				return;
			}
			articleService.remove(inputedId);
			System.out.printf("%d번 글이 삭제되었습니다.\n",inputedId+1);
		}
	}
}
