package com.learnwords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class LearnWordsApplication {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] {LearnWordsApplication.class}, args);
	}
}
