package com.learnwords.view.components;

import java.util.List;

import com.learnwords.domain.DomainModel;

public class ListForm<T extends DomainModel> {

	private List<T> models; 
	
	public ListForm(List<T> models)
	{
		this.models = models;
	}
	
	public String createForm()
	{
		StringBuilder sb = new StringBuilder();
		for(T model : models)
		{
			sb.append("<a href='/view_article?id=").append(model.getId()).append("'>").append(model.toString()).append("</a><br>");
		}
		
		return sb.toString();
	}
}
