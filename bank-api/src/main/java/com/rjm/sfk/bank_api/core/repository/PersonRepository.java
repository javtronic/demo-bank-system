package com.rjm.sfk.bank_api.core.repository;

import com.rjm.sfk.bank_api.client.entity.PersonEntity;
import com.rjm.sfk.bank_api.client.repository.IPersonRepository;
import com.rjm.sfk.bank_api.core.repository.configuration.ConfigJPAPersistence;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Person repository.
 *
 * @author javtronic
 */
@Lazy
@Repository
public class PersonRepository extends ConfigJPAPersistence<PersonEntity>
        implements IPersonRepository {

    /**
     * Constructor.
     */
    public PersonRepository() {
        super(PersonEntity.class);
    }
}
