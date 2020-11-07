package com.sbs.example.testtest.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.testtest.dto.Article;

public class ArticleDao {
	private List<Article> articles;
	private int lastArticleId;
	private List<Article> newArticles;
	public ArticleDao() {
		lastArticleId = 0;
		articles = new ArrayList<>();
		newArticles= new ArrayList<>();
		// 테스트
		getTestArticles();
	}

	private void getTestArticles() {
		for (int i = 0; i <= 6; i++) {
			write("제목" + (i + 1), "내용" + (i + 1), 0);
		}
		for (int i = 7; i <= 13; i++) {
			write("제목" + (i + 1), "내용" + (i + 1), 1);
		}
	}

	public int write(String title, String body, int id) {
		Article article = new Article();
		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		article.memberId = id;
		articles.add(article);
		lastArticleId = article.id;
		return article.id;
	}

	public int articlesSize() {
		return articles.size();
	}

	public List<Article> getArticles() {
		return articles;
	}

	public Article getArticle(int id) {
		return articles.get(id);
	}

	public int modify(String title, String body, int id) {
		Article article = articles.get(id);
		article.title=title;
		article.body=body;
		return article.id;
	}

	public void remove(int id) {
		articles.remove(id);
	}

	public void valueOfArticles(String value) {
		for(Article article : articles) {
			if(article.title.contains(value)) {
				newArticles.add(article);
			}
		}
	}

	public int newArticlesSize() {
		return newArticles.size();
	}

	public List<Article> newGetArticles() {
		return newArticles;
	}
}
