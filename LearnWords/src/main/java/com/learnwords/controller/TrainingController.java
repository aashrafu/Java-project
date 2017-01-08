package com.learnwords.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnwords.domain.Word;
import com.learnwords.enums.TrainingType;
import com.learnwords.service.ArticleService;
import com.learnwords.service.TrainingService;
import com.learnwords.service.WordService;
import com.learnwords.utils.ServiceException;

@Controller
public class TrainingController {

	private static final Logger LOG = LoggerFactory.getLogger(TrainingController.class);
	
	@Autowired
	@Qualifier("articleService")
	private ArticleService articleService;
	@Autowired
	@Qualifier("wordService")
	private WordService wordService;
	@Autowired
	@Qualifier("trainingService")
	private TrainingService trainingService;
	
	
	@RequestMapping("/matchingTraining")
	public String matchingTraining(Map<String, Object> map) {	
		try {
			Word word = wordService.getRandomWordByTraining(TrainingType.MATCHING_DE_RU);
			List<String> translationOptions = wordService.getRandomTranslations(word);
			Collections.shuffle(translationOptions);
			
			if(word != null) {
				map.put("translationList", translationOptions);
				map.put("word", word);
			}
		} catch (ServiceException e) {
			LOG.warn(e.getMessage());
			map.put("result", "error");
		}

		return "matchingTraining";
	}
	
	@RequestMapping(value = "/saveResult", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> saveResult(@RequestParam("word") String originalWord, @RequestParam("isCorrect") boolean isCorrect) {
		if(isCorrect == false) return null;	
		
		trainingService.removeWord(originalWord);
		Map<String, String> response = new HashMap<String, String>();
	    response.put("result", "success");
		System.out.println("Answer is " + isCorrect);
		return response;
	}
}
