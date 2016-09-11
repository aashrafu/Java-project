package com.learnwords.controller;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnwords.entity.Article;
import com.learnwords.service.ArticleService;
import com.learnwords.utils.Ajax;
import com.learnwords.utils.RestException;

@Controller
public class ArticleController {
	
	private static final Logger LOG = Logger.getLogger(ArticleController.class);

	@Autowired
	@Qualifier("articleService")
	private ArticleService articleService;

	@RequestMapping(value = "/persist", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> persist(@RequestParam("data") String data) throws RestException {
		try {
			if (data == null || data.equals("")) {
				return Ajax.emptyResponse();
			}

			articleService.persist(new Article(UUID.randomUUID(), data));
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
