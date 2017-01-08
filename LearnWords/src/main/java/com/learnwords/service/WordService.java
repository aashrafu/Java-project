package com.learnwords.service;

import java.util.List;

import com.learnwords.domain.Word;
import com.learnwords.entity.WordEntity;
import com.learnwords.enums.TrainingType;
import com.learnwords.utils.ServiceException;

public interface WordService {

	public Word get(Word word) throws ServiceException;
	public Word convertToModel(WordEntity entity);
	public void persist(Word word);
	public List<String> getRandomTranslations(Word word);
	public Word getByOriginal(String original) throws ServiceException;
	public Word getRandomWordByTraining(TrainingType matchesDeRu) throws ServiceException;
	public List<Word> getAll();
	public void removeWord(Integer id);
}
