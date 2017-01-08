package com.learnwords.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.learnwords.entity.WordEntity;
import com.learnwords.enums.TrainingType;
import com.learnwords.utils.DAOException;

@Repository
public class WordDAO extends DAOGeneric<WordEntity>{

	@Override
	public WordEntity find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public WordEntity findByOriginal(String original) throws DAOException {
		em.getTransaction().begin();
		
		List<WordEntity> words = em.createQuery("SELECT w FROM word w WHERE original='" + original +"'").getResultList();

		em.getTransaction().commit();
		
		if(words.size() == 0)
		{
			throw new DAOException("Word entity by original " + original + " was not found!");
		}
		
		return words.get(0);
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
	public WordEntity findRandomByTraining(TrainingType matchingDeRu) throws DAOException {
		em.getTransaction().begin();

		List<String> originals = em.createNativeQuery(
				"SELECT w.original FROM word w, training t WHERE w.id=t.word_id and t.training_name='"
						+ matchingDeRu.getName() + "' order by random() limit 1")
				.getResultList();

		em.getTransaction().commit();

		if (originals.size() == 0) {
			throw new DAOException("Word table is empty! No one row was returned.");
		}

		return findByOriginal(originals.get(0));
	}

	public void delete(String original) {
		em.getTransaction().begin();
		
		em.createQuery("DELETE FROM word w WHERE original='" + original +"'");

		em.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<WordEntity> findAll() {
		
		em.getTransaction().begin();
		
		List<WordEntity> words = em.createQuery("SELECT w FROM word w").getResultList();

		em.getTransaction().commit();
		
		return words;
	}

	public void removeWord(Integer id) {
		
		em.getTransaction().begin();
		
		em.createQuery("DELETE FROM word w WHERE id='" + id +"'").executeUpdate();

		em.getTransaction().commit();
	}
}
