package com.learnwords.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learnwords.domain.Word;
import com.learnwords.enums.TrainingType;
import com.learnwords.service.ArticleService;
import com.learnwords.service.WordService;

@Controller
public class TrainingController {

	@Autowired
	@Qualifier("articleService")
	private ArticleService articleService;
	@Autowired
	@Qualifier("wordService")
	private WordService wordService;
	
	
	@RequestMapping("/matchingTraining")
	public String matchesTraining(Map<String, Object> map) {	
		Word word = wordService.getRandomWordByTraining(TrainingType.MATCHING_DE_RU);
		
		List<String> translationOptions = wordService.getRandomTranslations(word);
		Collections.shuffle(translationOptions);
		
		if(word != null) {
			map.put("translationList", translationOptions);
			map.put("word", word);
		}
		
		return "matchingTraining";
	}
}
