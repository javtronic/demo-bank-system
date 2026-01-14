package com.rjm.sfk.bank_api.core.repository.configuration;

import com.rjm.sfk.bank_api.client.repository.configuration.IConfigJPAPersistence;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.io.Serializable;

public abstract class ConfigJPAPersistence<T> extends QuerydslRepositorySupport implements
        IConfigJPAPersistence<T> {

    private final Class<T> domainClass;

    /**
     * Constructor.
     *
     * @param domainClass - Clase Base.
     */
    public ConfigJPAPersistence(Class<T> domainClass) {
        super(domainClass);
        this.domainClass = domainClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(T obj) {
        getEntityManager().persist(obj);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(Serializable id) {
        return getEntityManager().find(domainClass, id);
    }
}
