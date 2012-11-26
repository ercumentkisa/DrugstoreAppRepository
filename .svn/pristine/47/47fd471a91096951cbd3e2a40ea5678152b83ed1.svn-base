package com.drugstore.server.repository;

import com.drugstore.server.domain.Entity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 20.11.2011
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public interface EntityRepository<T extends Entity> {
    T find(Long key);

    void delete(T object);

    T persist(T object);

    List<T> listAll(String orderClause);
}
