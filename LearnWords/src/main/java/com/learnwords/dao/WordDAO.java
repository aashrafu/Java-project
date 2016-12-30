package com.learnwords.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.learnwords.entity.WordEntity;
import com.learnwords.enums.TrainingType;

@Repository
public class WordDAO extends DAOGeneric<WordEntity>{

	@Override
	public WordEntity find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	public List<String> findRandomTranslations(String original)
	{
		em.getTransaction().begin();
		
		List<String> translations = em.createNativeQuery("SELECT translation FROM word w WHERE original='" + original
				+ "' UNION ALL (SELECT translation FROM word w WHERE original!='" + original
				+ "' order by random() limit 3)").getResultList();

		em.getTransaction().commit();
		
		return translations;
	}

	@SuppressWarnings("unchecked")
	public WordEntity findRandomByTraining(TrainingType matchingDeRu) {
		em.getTransaction().begin();

		List<String> originals = em.createNativeQuery(
				"SELECT w.original FROM word w, training t WHERE w.id=t.word_id and t.training_name='"
						+ matchingDeRu.getName() + "' order by random() limit 1")
				.getResultList();

		em.getTransaction().commit();

		if (originals.size() > 0) {
			return findByOriginal(originals.get(0));
		}

		return null;
	}
}
