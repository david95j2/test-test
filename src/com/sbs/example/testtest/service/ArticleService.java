package com.sbs.example.testtest.service;

import com.sbs.example.testtest.container.Container;
import com.sbs.example.testtest.dao.ArticleDao;
import com.sbs.example.testtest.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int add(String title, String body, int memberId) {
		return articleDao.add(title, body, memberId);
	}

	public int articlesSize() {
		return articleDao.sizeCheck();
	}

	public Article getArticles(int i) {
		return articleDao.getArticles(i);
	}

	public void remove(int id) {
		articleDao.remove(id);
	}

}
