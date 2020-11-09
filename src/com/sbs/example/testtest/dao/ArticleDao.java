package com.sbs.example.testtest.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbs.example.testtest.dto.Article;
import com.sbs.example.testtest.dto.Board;

public class ArticleDao {
	private List<Article> articles;
	private int lastArticleId;
	private List<Article> newArticles;

	private List<Board> boards;
	private int lastBoardId;

	

	public ArticleDao() {
		lastArticleId = 0;
		articles = new ArrayList<>();
		newArticles = new ArrayList<>();
		boards = new ArrayList<>();

		lastBoardId = 0;
	}

	public int write(int boardId, String title, String body, int id) {
		Article article = new Article();
		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		article.memberId = id;
		article.boardId = boardId;
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
		article.title = title;
		article.body = body;
		return article.id;
	}

	public void remove(int id) {
		articles.remove(id);
	}

	public void valueOfArticles(String value) {
		for (Article article : articles) {
			if (article.title.contains(value)) {
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

	public int makeBoard(String name) {
		Board board = new Board();
		board.boardId = lastBoardId + 1;
		board.boardName = name;
		boards.add(board);
		lastBoardId = board.boardId;
		return board.boardId;
	}

	public Board getBoardById(int index) {
		for(Board board : boards) {
			if(board.boardId==index) {
				return board;
			}
		}
		return null;
	}

	public List<Article> articleOfBoard(int boardId) {
		List<Article> articleOfBoard= new ArrayList<>();
		for(Article article : articles) {
			if(article.boardId==boardId) {
				articleOfBoard.add(article);
			}
		}
		Collections.reverse(articleOfBoard);
		return articleOfBoard;
	}
}
