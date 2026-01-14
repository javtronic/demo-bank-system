package com.rjm.sfk.bank_api.client.repository.configuration;

import java.io.Serializable;

public interface IConfigJPAPersistence<T> {

    /**
     * Save entity
     *
     * @param obj entity.
     */
    void save(T obj);


    /**
     * Find by ID.
     *
     * @param id id
     * @return Entity
     */
    T findById(Serializable id);

}
