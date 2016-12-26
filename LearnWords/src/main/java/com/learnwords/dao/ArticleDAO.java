package com.learnwords.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.learnwords.entity.ArticleEntity;

@Repository
public class ArticleDAO<T> extends DAOGeneric<ArticleEntity>{

	@Override
	public ArticleEntity find(int id) {
		em.getTransaction().begin();
		ArticleEntity article = em.find(ArticleEntity.class, id);
		em.getTransaction().commit();
		
		return article;
	}

	@SuppressWarnings("unchecked")
	public List<ArticleEntity> findAll() {
		em.getTransaction().begin();
		
		List<ArticleEntity> articles = em.createQuery("SELECT a  FROM Article a").getResultList();
		em.getTransaction().commit();
		
		return articles;
	}
}
