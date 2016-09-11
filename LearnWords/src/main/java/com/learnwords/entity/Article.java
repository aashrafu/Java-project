package com.learnwords.entity;

import java.util.UUID;

public class Article implements DomainObject{

	private static final long serialVersionUID = 7791002358967424073L;
	
	private UUID id;
	private String title;
	private String content;
	
	public Article(UUID id, String name) {
		super();
		this.id = id;
		this.title = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
