package com.sbs.example.testtest.service;

import java.util.List;

import com.sbs.example.testtest.container.Container;
import com.sbs.example.testtest.dao.ArticleDao;
import com.sbs.example.testtest.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService()	{
		articleDao=Container.articleDao;
	}

	public int write(String title, String body, int id) {
		return articleDao.write(title,body,id);
	}

	public int articlesSize() {
		return articleDao.articlesSize();
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public Article getArticle(int inputedId) {
		return articleDao.getArticle(inputedId);
	}

	public int modifyArticle(String title, String body, int id) {
		return articleDao.modify(title,body,id);
	}

	public void remove(int id) {
		articleDao.remove(id);
	}

	public void valueOfArticles(String value) {
		articleDao.valueOfArticles(value);
		
	}

	public int newArticlesSize() {
		return articleDao.newArticlesSize();
	}

	public List<Article> newGetArticles() {
		return articleDao.newGetArticles();
	}
}
