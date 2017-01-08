package com.learnwords.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnwords.dao.TrainingDAO;
import com.learnwords.dao.WordDAO;
import com.learnwords.domain.Word;
import com.learnwords.entity.TrainingEntity;
import com.learnwords.entity.WordEntity;
import com.learnwords.enums.TrainingType;
import com.learnwords.utils.DAOException;
import com.learnwords.utils.ServiceException;

@Service("wordService")
public class WordServiceImpl implements WordService {

	@Autowired
	private WordDAO wordDAO;
	@Autowired
	private TrainingDAO trainingDAO;

	@Override
	public List<String> getRandomTranslations(Word word) {
		
		return wordDAO.findRandomTranslations(word.getOriginal());
	}
	
	@Override
	public Word get(Word word) throws ServiceException {
		WordEntity entity = null;
		try {
			entity = wordDAO.findByOriginal(word.getOriginal());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return convertToModel(entity);
	}
	

	@Override
	public Word getByOriginal(String original) throws ServiceException {
		WordEntity entity = null;
		try {
			entity = wordDAO.findByOriginal(original);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return convertToModel(entity);
	}

	@Override
	public Word convertToModel(WordEntity entity) {
		if (entity == null)
			return null;

		Word word = new Word();
		word.setId(entity.getId());
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

	public void deleteWord(String original) {
		wordDAO.delete(original);
	}
	
	@Override
	public Word getRandomWordByTraining(TrainingType matchingDeRu) throws ServiceException {
		Word word = null;
		try {
			word = convertToModel(wordDAO.findRandomByTraining(matchingDeRu));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return word;
	}

	@Override
	public List<Word> getAll() {
		List<Word> wordList = new ArrayList<Word>();
		for(WordEntity word : wordDAO.findAll()) {
			wordList.add(convertToModel(word));
		}
	
		return wordList;
	}

	@Override
	public void removeWord(Integer id) {
		wordDAO.removeWord(id);
	}
}
