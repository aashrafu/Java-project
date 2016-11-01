package com.learnwords.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

abstract public class DAOGeneric<T> {

	protected EntityManager em = Persistence.createEntityManagerFactory("general").createEntityManager();

	public void save(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}

	abstract public T find(int id);
}
