package com.learnwords.domain;

public class Dictionary implements DomainModel{
	
	private static final long serialVersionUID = -6666006756470880013L;
	
	private int id;
	private Article article;
	private String word;
	private String translation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}

	
}
