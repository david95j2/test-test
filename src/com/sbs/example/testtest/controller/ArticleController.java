package com.sbs.example.testtest.controller;

import java.util.List;

import com.sbs.example.testtest.container.Container;
import com.sbs.example.testtest.dto.Article;
import com.sbs.example.testtest.dto.Member;
import com.sbs.example.testtest.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void run(String cmd) {
		if (cmd.equals("article add")) {
			if (Container.session.logOut()) {
				System.out.println("로그인 후 이용가능");
				return;
			}
			System.out.println("== 글 작성 ==");
			System.out.printf("제목 : ");
			String title = Container.sc.nextLine();
			System.out.printf("내용 : ");
			String body = Container.sc.nextLine();
			int id = Container.session.loginedMemberId - 1;
			int num = articleService.write(title, body, id);
			System.out.printf("%d번 글이 등록되었습니다.\n", num);
		} else if (cmd.equals("article list")) {

			int size = articleService.articlesSize();
			if (size == 0) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}
			System.out.println("== 글 전체목록 ==");
			System.out.println("번호 / 작성자 / 제목");

			List<Article> articles = articleService.getArticles();
			List<Member> members = Container.memberService.getMembers();
			for (int i = size - 1; i >= 0; i--) {
				Article article = articles.get(i);
				Member member = members.get(article.memberId);
				System.out.printf("%d / %s / %s\n", article.id, member.name, article.title);
			}

		} else if (cmd.startsWith("article list ")) {
			int page = 1;
			try {
				page = Integer.parseInt(cmd.split(" ")[2]);
			} catch (NumberFormatException e) {
				System.out.println("페이지번호를 숫자로만 입력해주세요.");
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
			if (endPage <= 0) {
				endPage = 0;
			}
			if (startPage <= endPage) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}
			System.out.println("== 글 목록 ==");
			System.out.println("번호 / 작성자 / 제목");
			List<Article> articles = articleService.getArticles();
			List<Member> members = Container.memberService.getMembers();
			for (int i = startPage; i >= endPage; i--) {
				Article article = articles.get(i);
				Member member = members.get(article.memberId);
				System.out.printf("%d / %s / %s\n", article.id, member.name, article.title);
			}
		} else if (cmd.startsWith("article detail ")) {
			int inputedId = 1;
			try {
				inputedId = Integer.parseInt(cmd.split(" ")[2]) - 1;
			} catch (NumberFormatException e) {
				System.out.println("찾는 글의 번호를 명령어와 숫자로만 입력해주세요.");
				return;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("띄어쓰기 잘해주세요~:)");
				return;
			}
			int size = articleService.articlesSize();
			if (size == 0 || inputedId > size - 1) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}
			if (inputedId < 0) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}

			Article article = articleService.getArticle(inputedId);
			Member member = Container.memberService.getMember(article.memberId);
			System.out.println("== 글 정보 ==");
			System.out.printf("번호 : %d번\n", article.id);
			System.out.printf("작성자 : %s\n", member.name);
			System.out.printf("제목 : %s\n", article.title);
			System.out.printf("내용 : %s\n", article.body);
		} else if (cmd.startsWith("article modify ")) {
			if (Container.session.logOut()) {
				System.out.println("로그인 후 이용가능");
				return;
			}
			int inputedId = 1;
			try {
				inputedId = Integer.parseInt(cmd.split(" ")[2]) - 1;
			} catch (NumberFormatException e) {
				System.out.println("찾는 글의 번호를 명령어와 숫자로만 입력해주세요.");
				return;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("띄어쓰기 잘해주세요~:)");
				return;
			}
			int session = Container.session.loginedMemberId - 1;
			Article article = articleService.getArticle(inputedId);
			if (article.memberId != session) {
				System.out.println("다른 사용자의 게시물입니다.");
				return;
			}
			int size = articleService.articlesSize();
			if (size == 0 || inputedId > size - 1) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}
			if (inputedId < 0) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}

			System.out.println("== 글 수정 ==");
			System.out.printf("제목 : ");
			String title = Container.sc.nextLine();
			System.out.printf("제목 : ");
			String body = Container.sc.nextLine();
			int id = articleService.modifyArticle(title, body, inputedId);
			System.out.printf("%d번 글이 수정되었습니다.\n", id);
		}
		else if (cmd.startsWith("article remove ")) {
			if (Container.session.logOut()) {
				System.out.println("로그인 후 이용가능");
				return;
			}
			int inputedId = 1;
			try {
				inputedId = Integer.parseInt(cmd.split(" ")[2]) - 1;
			} catch (NumberFormatException e) {
				System.out.println("삭제하려는 글의 번호를 명령어와 숫자로만 입력해주세요.");
				return;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("띄어쓰기 잘해주세요~:)");
				return;
			}
			int session = Container.session.loginedMemberId - 1;
			Article article = articleService.getArticle(inputedId);
			if (article.memberId != session) {
				System.out.println("다른 사용자의 게시물입니다.");
				return;
			}
			int size = articleService.articlesSize();
			if (size == 0 || inputedId > size - 1) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}
			if (inputedId < 0) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}

			System.out.println("== 글 삭제 ==");
			articleService.remove(inputedId);
			System.out.printf("%d번 글이 삭제되었습니다.\n", inputedId+1);
		}
		else if (cmd.startsWith("article search ")) {
			int page = 1;
			String[] cmdBits=cmd.split(" ");
			try {
				page = Integer.parseInt(cmdBits[3]);
			} catch (NumberFormatException e) {
				System.out.println("페이지번호를 숫자로만 입력해주세요.");
				return;
			} 
			catch (ArrayIndexOutOfBoundsException e) {
				page=1;
			}
			if (page <= 1) {
				page = 1;
			}
			String value=cmdBits[2];
			articleService.valueOfArticles(value);
			int pageList = 10;
			int startPage = articleService.newArticlesSize() - 1;
			startPage -= (page - 1) * pageList;
			int endPage = startPage - (pageList - 1);
			if (endPage <= 0) {
				endPage = 0;
			}
			if (startPage <= endPage) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}
			System.out.println("== 글 목록 ==");
			System.out.println("번호 / 작성자 / 제목");
			List<Article> articles = articleService.newGetArticles();
			List<Member> members = Container.memberService.getMembers();
			for (int i = startPage; i >= endPage; i--) {
				Article article = articles.get(i);
				Member member = members.get(article.memberId);
				System.out.printf("%d / %s / %s\n", article.id, member.name, article.title);
			}
		} 
	}
}
