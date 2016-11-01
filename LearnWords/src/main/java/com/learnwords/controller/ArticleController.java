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
import com.learnwords.view.components.ListForm;

@Controller
public class ArticleController {
	
	private static final Logger LOG = Logger.getLogger(ArticleController.class);

	@Autowired
	@Qualifier("articleService")
	private ArticleService articleService;
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
	
	@RequestMapping(value = "/article", method = RequestMethod.GET)
    public String showArticle(@RequestParam("id") String id, Model model) {
		model.addAttribute("aaa", "aaa");
        return "article";
    }
	
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
    public String articles(Model model) {
        return "articles";
    }
	
	@RequestMapping(value = "/addArticle", method = RequestMethod.GET)
    public String addArticle(Model model) {
        return "addArticle";
    }
	
	@RequestMapping(value = "/getArticle", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> getArticle(@RequestParam("id") int id) throws RestException {
		try {
	        Map<String, String> response = new HashMap<String, String>();
	        
	        Article article = articleService.getById(id);
	        response.put("result", "success");
	        response.put("title", article.getTitle());
	        String content = articleService.makeArticleTagged(article);
	        response.put("content", content);
	        
			return response;
		} catch (Exception e) {
			throw new RestException(e);
		}
	}
	
	@RequestMapping(value = "/allArticles", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> allArticles() throws RestException {
		try {
			List<Article> result = articleService.getAll();

			ListForm<Article> listForm = new ListForm<Article>(result);
			return Ajax.successResponse(listForm.createForm());
		} catch (Exception e) {
			throw new RestException(e);
		}
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

	@RequestMapping(value = "/getRandomData", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getRandomData() throws RestException {
		try {
			Set<String> result = null;
			return Ajax.successResponse(result);
		} catch (Exception e) {
			throw new RestException(e);
		}
	}
}
