package com.rjm.sfk.bank_api.core.repository.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rjm.sfk.bank_api.vo.ClientVO;
import com.rjm.sfk.bank_api.vo.PersonVO;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.rjm.sfk.bank_api.client.entity.QClientEntity.clientEntity;
import static com.rjm.sfk.bank_api.client.entity.QPersonEntity.personEntity;

/**
 * Client query repository
 *
 * @author javtronic
 */
@Lazy
@Repository
public class ClientQueryRepository implements IClientQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * Constructor
     *
     * @param em Entity manager
     */
    public ClientQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientVO findClientByCode(String clientCode) {
        return queryFactory
                .select(Projections.bean(ClientVO.class,
                        clientEntity.clientCode,
                        clientEntity.password,
                        clientEntity.clientStatus,
                        Projections.bean(PersonVO.class,
                                personEntity.personCode,
                                personEntity.name,
                                personEntity.gender,
                                personEntity.age,
                                personEntity.identification,
                                personEntity.phoneNumber,
                                personEntity.address,
                                personEntity.status
                        ).as("person")
                ))
                .from(clientEntity)
                .innerJoin(clientEntity.person, personEntity)
                .where(clientEntity.clientCode.eq(clientCode))
                .fetchOne();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientVO findClientByIdentification(String identification) {
        return queryFactory
                .select(Projections.bean(ClientVO.class,
                        clientEntity.clientCode,
                        clientEntity.password,
                        clientEntity.clientStatus,
                        Projections.bean(PersonVO.class,
                                personEntity.personCode,
                                personEntity.name,
                                personEntity.gender,
                                personEntity.age,
                                personEntity.identification,
                                personEntity.phoneNumber,
                                personEntity.address,
                                personEntity.status
                        ).as("person")
                ))
                .from(clientEntity)
                .innerJoin(clientEntity.person, personEntity)
                .where(personEntity.identification.eq(identification))
                .fetchOne();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ClientVO> findAllClients() {
        return queryFactory
                .select(Projections.bean(ClientVO.class,
                        clientEntity.clientCode,
                        clientEntity.password,
                        clientEntity.clientStatus,
                        Projections.bean(PersonVO.class,
                                personEntity.personCode,
                                personEntity.name,
                                personEntity.gender,
                                personEntity.age,
                                personEntity.identification,
                                personEntity.phoneNumber,
                                personEntity.address,
                                personEntity.status
                        ).as("person")
                ))
                .from(clientEntity)
                .innerJoin(clientEntity.person, personEntity)
                .orderBy(clientEntity.createdDate.desc())
                .fetch();
    }
}
