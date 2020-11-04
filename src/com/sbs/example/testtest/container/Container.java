package com.sbs.example.testtest.container;

import java.util.Scanner;

import com.sbs.example.testtest.controller.ArticleController;
import com.sbs.example.testtest.controller.MemberController;
import com.sbs.example.testtest.dao.ArticleDao;
import com.sbs.example.testtest.dao.MemberDao;
import com.sbs.example.testtest.service.ArticleService;
import com.sbs.example.testtest.service.MemberService;
import com.sbs.example.testtest.session.Session;

public class Container {
	public static Scanner sc;
	public static Session session;
	
	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	
	public static MemberService memberService;
	public static ArticleService articleService;
	
	public static MemberController memberController;
	public static ArticleController articleController;
	
	static {
		sc=new Scanner(System.in);
		session=new Session();
		
		memberDao=new MemberDao();
		articleDao=new ArticleDao();
		
		memberService=new MemberService();
		articleService=new ArticleService();
		
		memberController=new MemberController();
		articleController=new ArticleController();
		
	}
}
