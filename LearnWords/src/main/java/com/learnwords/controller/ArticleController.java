package com.learnwords.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnwords.domain.Article;
import com.learnwords.domain.Dictionary;
import com.learnwords.service.ArticleService;
import com.learnwords.service.DictionaryService;
import com.learnwords.utils.Ajax;
import com.learnwords.utils.RestException;

@Controller
public class ArticleController {

	@Autowired
	@Qualifier("articleService")
	private ArticleService articleService;
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
	
	@RequestMapping("/index")
	public String articleList(Map<String, Object> map) {
		
		List<Article> articles = articleService.getAll();
		map.put("articleList", articles);
		
		return "articleList";
	}
	
	@RequestMapping("/newArticle")
	public String newArticle(Map<String, Object> map) {	
		
		return "newArticle";
	}
	
	@RequestMapping("/matchesTraining")
	public String matchesTraining(Map<String, Object> map) {	
		
		return "matchesTraining";
	}
	
	@RequestMapping(value = "/viewArticle", method = RequestMethod.GET)
	public String viewArticle(@RequestParam("id") int id, Map<String, Object> map) throws RestException {
        
        Article article = articleService.getById(id);
        String content = articleService.makeArticleTagged(article);

        map.put("title", article.getTitle());
        map.put("content", content);
        
		return "viewArticle";
	}

	@RequestMapping(value = "/translate", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> translate(@RequestParam("word") String word, @RequestParam("article") int article) throws RestException {
		try {
						
			Dictionary dictionary = new Dictionary();
			dictionary.setWord(word);
			dictionary.setArticle(articleService.getById(article));
			Dictionary existingDictionary = dictionaryService.get(dictionary);
			if(existingDictionary != null) 
			{
				dictionary.setTranslation(existingDictionary.getTranslation());
			} else {
				String translation = articleService.sendGet(word);
				dictionary.setTranslation(translation);
				dictionaryService.persist(dictionary);
			}			
			
			Map<String, String> response = new HashMap<String, String>();
	        response.put("result", "success");
	        response.put("translation", dictionary.getTranslation());
			
			return response;
		} catch (Exception e) {
			throw new RestException(e);
		}
	}
	
	@RequestMapping(value = "/persist", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> persist(@RequestParam("title") String title, @RequestParam("content") String content) throws RestException {
		try {
			if (title == null || title.equals("")) {
				return Ajax.emptyResponse();
			}
			Article article = new Article(title);
			article.setContent(content);
			
			articleService.persist(article);
			return Ajax.emptyResponse();
		} catch (Exception e) {
			throw new RestException(e);
		}
	}
}
