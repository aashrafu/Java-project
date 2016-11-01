package com.learnwords.domain;

public class Article implements DomainModel{

	private static final long serialVersionUID = 7791002358967424073L;
	
	private int id;
	private String title;
	private String content;
	
	public Article(String title) {
		super();
		this.title = title;
	}
	
	public Article(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return title;
	}
}
