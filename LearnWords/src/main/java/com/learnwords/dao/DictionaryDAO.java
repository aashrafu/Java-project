package com.learnwords.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.learnwords.entity.DictionaryEntity;

@Repository
public class DictionaryDAO extends DAOGeneric<DictionaryEntity>{

	@Override
	public DictionaryEntity find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public DictionaryEntity get(String word) {
		em.getTransaction().begin();
		
		List<DictionaryEntity> dictionary = em.createQuery("SELECT d FROM Dictionary d WHERE word='" + word +"'").getResultList();

		em.getTransaction().commit();
		
		if(dictionary.size() > 0)
		{
			return dictionary.get(0);
		}
		
		return null;
	}
}
