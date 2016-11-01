package com.learnwords.repository;

import com.learnwords.domain.DomainModel;

public interface ArticleRepository<V extends DomainModel> {

    void persist(V object);

    void delete(V object);

}