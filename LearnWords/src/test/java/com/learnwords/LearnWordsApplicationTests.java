package com.learnwords;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learnwords.entity.ArticleEntity;
import com.learnwords.entity.DictionaryEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnWordsApplicationTests {
	
	public EntityManager em = Persistence.createEntityManagerFactory("general").createEntityManager();

	@Test
	public void contextLoads() {
		ArticleEntity ae = new ArticleEntity();
		ae.setTitle("Title");
		ae.setContent("Content");
		em.getTransaction().begin();
		ae = em.merge(ae);
		
		DictionaryEntity de = new DictionaryEntity();
		de.setArticle_id(ae.getId());
		de.setWord("Hello");
		de.setTranslation("Привет");
		
		em.persist(de);
        em.getTransaction().commit();
	}

}
