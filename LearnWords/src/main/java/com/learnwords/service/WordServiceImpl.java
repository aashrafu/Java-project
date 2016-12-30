package com.learnwords.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnwords.dao.TrainingDAO;
import com.learnwords.dao.WordDAO;
import com.learnwords.domain.Word;
import com.learnwords.entity.TrainingEntity;
import com.learnwords.entity.WordEntity;
import com.learnwords.enums.TrainingType;

@Service("wordService")
public class WordServiceImpl implements WordService {

	@Autowired
	WordDAO wordDAO;
	@Autowired
	TrainingDAO trainingDAO;

	@Override
	public List<String> getRandomTranslations(Word word) {
		
		return wordDAO.findRandomTranslations(word.getOriginal());
	}
	
	@Override
	public Word get(Word word) {
		WordEntity entity = wordDAO.findByOriginal(word.getOriginal());
		return convertToModel(entity);
	}
	

	@Override
	public Word getByOriginal(String original) {
		WordEntity entity = wordDAO.findByOriginal(original);
		
		return convertToModel(entity);
	}

	@Override
	public Word convertToModel(WordEntity entity) {
		if (entity == null)
			return null;

		Word word = new Word();
		word.setOriginal(entity.getOriginal());
		word.setTranslation(entity.getTranslation());

		return word;
	}
	
	
	public void persist(Word dictionary) {
		WordEntity entity = new WordEntity();
		entity.setArticle_id(dictionary.getArticle().getId());
		entity.setOriginal(dictionary.getOriginal());
		entity.setTranslation(dictionary.getTranslation());
		
		wordDAO.save(entity);
		
		TrainingEntity trainingEntity = new TrainingEntity();
		trainingEntity.setTrainingName(TrainingType.MATCHING_DE_RU.getName());
		trainingEntity.setWord_id(entity.getId());
		trainingDAO.save(trainingEntity);
	}

	@Override
	public Word getRandomWordByTraining(TrainingType matchingDeRu) {
		return convertToModel(wordDAO.findRandomByTraining(matchingDeRu));
	}
}
