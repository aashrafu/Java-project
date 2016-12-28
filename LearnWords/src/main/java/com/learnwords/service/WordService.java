package com.learnwords.service;

import java.util.List;

import com.learnwords.domain.Word;
import com.learnwords.entity.WordEntity;

public interface WordService {

	public Word get(Word word);
	public Word convertToModel(WordEntity entity);
	public void persist(Word word);
	public List<String> getRandomTranslations(Word word);
	public Word getByOriginal(String original);
}
