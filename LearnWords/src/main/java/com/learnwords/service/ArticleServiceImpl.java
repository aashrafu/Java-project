package com.learnwords.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.learnwords.dao.ArticleDAO;
import com.learnwords.domain.Article;
import com.learnwords.entity.ArticleEntity;
import com.learnwords.repository.ArticleRepositoryImpl;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	@Qualifier("articleRepository")
	private ArticleRepositoryImpl articleRepository;
	@Autowired
	private ArticleDAO<ArticleEntity> articleDAO;

	public String makeArticleTagged(Article article) {
		List<String> words = Arrays.asList(article.getContent().split(" "));
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append("<a href=\"javascript:translate()\">").append(word).append(" ").append("</a>");
		}

		return sb.toString();
	}

	@Override
	public Article getById(int id) {

		return convertToModel(articleDAO.find(id));
	}

	@Override
	public void persist(Article article) {
		ArticleEntity entity = new ArticleEntity();
		entity.setTitle(article.getTitle());
		entity.setContent(article.getContent());
		articleDAO.save(entity);
	}

	public List<Article> getAll() {
		return convertToModel(articleDAO.findAll());
	}

	public Article convertToModel(ArticleEntity entity) {
		Article article = new Article(entity.getTitle());
		article.setId(entity.getId());
		article.setContent(entity.getContent());

		return article;
	}

	public List<Article> convertToModel(List<ArticleEntity> entities) {
		List<Article> articles = new ArrayList<>();
		for (ArticleEntity entity : entities) {
			articles.add(new Article(entity.getId(), entity.getTitle()));
		}

		return articles;
	}

	public String sendGet(String word) {
		StringBuffer response = null;
		try {
		String url = "http://api.multillect.com/translate/plain/1.0/350?method=translate/api/translate&from=deu&to=rus&text="+word+"&sig=dc699cc607cb72c247154bb90cd9af56";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response.toString();
	}

}
