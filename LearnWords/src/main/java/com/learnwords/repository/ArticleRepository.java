package com.learnwords.repository;

import com.learnwords.entity.DomainObject;

public interface ArticleRepository<V extends DomainObject> {

    void persist(V object);

    void delete(V object);

}