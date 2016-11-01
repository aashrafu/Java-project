package com.learnwords.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Dictionary")
public class DictionaryEntity implements Serializable {

	private static final long serialVersionUID = -5057726787681614826L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dictionary_id", unique = true, nullable = false)
	private int id;
	@Column(name = "article_id", nullable = false)
	private int article_id;
	@Column(name = "word", nullable = false, length = 100)
	private String word;
	@Column(name = "translation", nullable = false, length = 100)
	private String translation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + article_id;
		result = prime * result + id;
		result = prime * result + ((translation == null) ? 0 : translation.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DictionaryEntity other = (DictionaryEntity) obj;
		if (article_id != other.article_id)
			return false;
		if (id != other.id)
			return false;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
}
