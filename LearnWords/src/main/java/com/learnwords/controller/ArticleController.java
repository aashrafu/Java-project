package com.learnwords.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnwords.domain.Article;
import com.learnwords.domain.Word;
import com.learnwords.service.ArticleService;
import com.learnwords.service.WordService;
import com.learnwords.utils.RestException;
import com.learnwords.utils.ServiceException;
import com.learnwords.utils.StringModifier;

@Controller
public class ArticleController {

	@Autowired
	@Qualifier("articleService")
	private ArticleService articleService;
	@Autowired
	@Qualifier("wordService")
	private WordService wordService;
	
	@RequestMapping("/index")
	public String articleList(Map<String, Object> map) {
		
		List<Article> articles = articleService.getAll();
		map.put("articleList", articles);
		
		
		return "articleList";
	}
	
	@RequestMapping("/newArticle")
	public String newArticle(Map<String, Object> model) {	
		model.put("article", new Article());
		
		return "newArticle";
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
	public @ResponseBody Map<String, String> translate(@RequestParam("word") String originalWord, @RequestParam("article") int articleId) {

			Article article = articleService.getById(articleId);
			originalWord = StringModifier.removeSymbols(originalWord);
			Word word;
			try {
				word = wordService.getByOriginal(originalWord);
			} catch (ServiceException e) {
				word = new Word();
				word.setOriginal(originalWord);
				word.setTranslation(articleService.translateStub(originalWord));
				word.setArticle(article);
				wordService.persist(word);
			}			
			
			Map<String, String> response = new HashMap<String, String>();
	        response.put("result", "success");
	        response.put("translation", word.getTranslation());
			
			return response;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addArticle(@ModelAttribute("contact") Article article,
		BindingResult result) throws RestException {

		articleService.persist(article);
		
		return "redirect:/index";
	}
}
