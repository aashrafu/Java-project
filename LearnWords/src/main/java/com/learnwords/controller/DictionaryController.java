package com.learnwords.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnwords.domain.Word;
import com.learnwords.service.ArticleService;
import com.learnwords.service.WordService;

@Controller
public class DictionaryController {

	@Autowired
	@Qualifier("articleService")
	private ArticleService articleService;
	@Autowired
	@Qualifier("wordService")
	private WordService wordService;
	
	@RequestMapping("/dictionary")
	public String dictionaryList(Map<String, Object> map) {
		
		List<Word> wordList = wordService.getAll();
		map.put("dictionaryList", wordList);

		return "dictionary";
	}
	
	@RequestMapping(value = "/removeWord", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> removeWord(@RequestParam("id") Integer id) {

		wordService.removeWord(id);
		Map<String, String> response = new HashMap<String, String>();
	    response.put("result", "success");

		return response;
	}
}
