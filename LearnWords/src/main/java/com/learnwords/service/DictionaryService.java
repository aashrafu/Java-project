package com.learnwords.service;

import com.learnwords.domain.Dictionary;
import com.learnwords.entity.DictionaryEntity;

public interface DictionaryService {

	public Dictionary get(Dictionary dictionary);
	public Dictionary convertToModel(DictionaryEntity entity);
	public void persist(Dictionary dictionary);
}
