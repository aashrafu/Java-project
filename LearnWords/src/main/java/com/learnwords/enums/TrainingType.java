package com.learnwords.enums;

public enum TrainingType {
	MATCHING_DE_RU("MatchingDeRu"), MATCHING_RU_DE("MatchingRuDe");
	
	private String name;
	
	TrainingType(String name) {
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
