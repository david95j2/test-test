package com.sbs.example.testtest.dao;


import java.util.ArrayList;
import java.util.List;

import com.sbs.example.testtest.dto.Article;

public class ArticleDao {
	private int lastArticleId;
	private List<Article> articles;
	
	public ArticleDao() {
		lastArticleId=0;
		articles=new ArrayList<>();
		
		//테스트
		addTestData();
	}
	
	private void addTestData() {
		for(int i=0;i<=7;i++) {
			add("제목"+(i+1),"내용"+(i+1),1);
		}
		for(int i=8;i<16;i++) {
			add("제목"+(i+1),"내용"+(i+1),2);
		}
	}

	public int add(String title, String body, int memberId) {
		Article article = new Article();
		article.id=lastArticleId+1;
		article.title=title;
		article.body=body;
		article.memberId=memberId;
		articles.add(article);
		lastArticleId=article.id;
		return article.id;
	}

	public int sizeCheck() {
		return articles.size();
	}

	public Article getArticles(int i) {
		return articles.get(i);
	}

	public Object remove(int id) {
		return articles.remove(id);
	}

}
