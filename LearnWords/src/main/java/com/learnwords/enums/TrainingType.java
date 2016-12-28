package com.learnwords.enums;

public enum TrainingType {
	MATCHES_DE_RU("MatchesDeRu"), MATCHES_RU_DE("MatchesRuDe");
	
	private String name;
	
	TrainingType(String name) {
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
