package com.rjm.sfk.bank_api.core.repository.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rjm.sfk.bank_api.vo.AccountVO;
import com.rjm.sfk.bank_api.vo.ClientVO;
import com.rjm.sfk.bank_api.vo.PersonVO;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.rjm.sfk.bank_api.client.entity.QAccountEntity.accountEntity;
import static com.rjm.sfk.bank_api.client.entity.QClientEntity.clientEntity;
import static com.rjm.sfk.bank_api.client.entity.QPersonEntity.personEntity;

/**
 * Account query repository.
 *
 * @author javtronic
 */
@Lazy
@Repository
public class AccountQueryRepository implements IAccountQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * Constructor
     *
     * @param em Entity manager
     */
    public AccountQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountVO> findAllAccounts() {
        return queryFactory
                .select(Projections.bean(AccountVO.class,
                        accountEntity.accountCode,
                        accountEntity.accountNumber,
                        accountEntity.accountType,
                        accountEntity.accountStatus,
                        accountEntity.initialBalance,
                        Projections.bean(ClientVO.class,
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
                        ).as("client")))
                .from(accountEntity)
                .innerJoin(accountEntity.client, clientEntity)
                .innerJoin(clientEntity.person, personEntity)
                .orderBy(accountEntity.createdDate.desc())
                .fetch();
    }
}
