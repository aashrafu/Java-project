package com.learnwords.service;

import java.util.List;

import com.learnwords.domain.Article;

public interface ArticleService {

	public void persist(Article article);
	public List<Article> getAll();
	public Article getById(int id);
	public String makeArticleTagged(Article article);
	public String sendGet(String word);
	public String translateStub(String word);
}
