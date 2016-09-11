package com.learnwords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.learnwords.entity.Article;
import com.learnwords.repository.ArticleRepositoryImpl;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	@Qualifier("articleRepository")
	private ArticleRepositoryImpl articleRepository;

	@Override
	public void persist(Article article) {
		articleRepository.persist(article);
	}

}
