package com.learnwords.view;

import java.util.List;

public class ListModel {

	private List<String> keys;
	private List<String> values;
	
	ListModel(List<String> keys, List<String> values)
	{
		this.keys = keys;
		this.values = values;
	}
}
