package com.learnwords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnwords.dao.TrainingDAO;
import com.learnwords.dao.WordDAO;
import com.learnwords.entity.WordEntity;
import com.learnwords.utils.DAOException;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingDAO trainingDAO;
	@Autowired
	private WordDAO wordDAO;
	
	@Override
	public void removeWord(String originalWord) {
		try {
			WordEntity word = wordDAO.findByOriginal(originalWord);
			trainingDAO.remove(word);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

}
