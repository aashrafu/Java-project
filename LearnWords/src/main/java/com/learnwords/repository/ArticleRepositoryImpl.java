package com.learnwords.repository;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.learnwords.domain.Article;

@Repository("articleRepository")
public class ArticleRepositoryImpl implements ArticleRepository<Article>{

	@Autowired
	protected JdbcOperations jdbcOperations;

	@Override
	public void persist(Article object) {
		Object[] params = new Object[] { object.getId(), object.getTitle(), object.getContent() };
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR };

		jdbcOperations.update("INSERT INTO article(\n" + "            article_id, title, content)\n"
				+ "    VALUES (?, ?, ?);", params, types);
	}

	@Override
	public void delete(Article object) {
		jdbcOperations.update("DELETE FROM yourapp_data\n" + " WHERE data_id = '" + object.getId() + "';");
		
	}


}
