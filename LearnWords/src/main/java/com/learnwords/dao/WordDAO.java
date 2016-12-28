package com.learnwords.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.learnwords.entity.WordEntity;

@Repository
public class WordDAO extends DAOGeneric<WordEntity>{

	@Override
	public WordEntity find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public WordEntity findByOriginal(String original) {
		em.getTransaction().begin();
		
		List<WordEntity> words = em.createQuery("SELECT w FROM word w WHERE original='" + original +"'").getResultList();

		em.getTransaction().commit();
		
		if(words.size() > 0)
		{
			return words.get(0);
		}
		
		return null;
	}
	
	public List<String> findRandomTranslations(String original)
	{
		em.getTransaction().begin();
		
		List<String> translations = em.createNativeQuery("SELECT translation FROM word w WHERE original='"+ original +"' UNION ALL (SELECT translation FROM word w WHERE original!='"+ original +"' order by random() limit 3)").getResultList();

		em.getTransaction().commit();
		
		return translations;
	}
}
