package com.learnwords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learnwords.config.JpaConfig;
import com.learnwords.controller.ArticleController;

@SpringBootApplication
public class LearnWordsApplication {

	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] {LearnWordsApplication.class, JpaConfig.class}, args);
	}
}
