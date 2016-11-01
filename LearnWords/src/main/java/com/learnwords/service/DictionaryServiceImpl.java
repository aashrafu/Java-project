package com.learnwords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnwords.dao.DictionaryDAO;
import com.learnwords.domain.Dictionary;
import com.learnwords.entity.DictionaryEntity;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	DictionaryDAO dictionaryDAO;

	@Override
	public Dictionary get(Dictionary dictionary) {
		DictionaryEntity entity = dictionaryDAO.get(dictionary.getWord());
		return convertToModel(entity);
	}

	@Override
	public Dictionary convertToModel(DictionaryEntity entity) {
		if (entity == null)
			return null;

		Dictionary dictionary = new Dictionary();
		dictionary.setWord(entity.getWord());
		dictionary.setTranslation(entity.getTranslation());

		return dictionary;
	}
	
	
	public void persist(Dictionary dictionary) {
		DictionaryEntity entity = new DictionaryEntity();
		entity.setArticle_id(dictionary.getArticle().getId());
		entity.setWord(dictionary.getWord());
		entity.setTranslation(dictionary.getTranslation());
		
		dictionaryDAO.save(entity);
	}
}
