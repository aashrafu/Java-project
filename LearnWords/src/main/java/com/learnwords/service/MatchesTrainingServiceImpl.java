package com.learnwords.service;

import java.util.Arrays;
import java.util.List;

public class MatchesTrainingServiceImpl implements MatchesTrainingSerivce {

	@Override
	public List<String> getRandomTranslations() {
		
		return Arrays.asList("first", "second", "third");
	}

}
