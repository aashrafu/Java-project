package com.learnwords.entity;

import java.util.UUID;

public class Dictionary implements DomainObject{
	
	private static final long serialVersionUID = -6666006756470880013L;
	
	private UUID id;
	private Article articleId;
	private String originalWord;
	private String translation;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Article getArticleId() {
		return articleId;
	}
	public void setArticleId(Article articleId) {
		this.articleId = articleId;
	}
	public String getOriginalWord() {
		return originalWord;
	}
	public void setOriginalWord(String originalWord) {
		this.originalWord = originalWord;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}

	
}
