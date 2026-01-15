package com.rjm.sfk.bank_api.core.repository.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rjm.sfk.bank_api.vo.TransactionDetailVO;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.rjm.sfk.bank_api.client.entity.QAccountEntity.accountEntity;
import static com.rjm.sfk.bank_api.client.entity.QClientEntity.clientEntity;
import static com.rjm.sfk.bank_api.client.entity.QPersonEntity.personEntity;
import static com.rjm.sfk.bank_api.client.entity.QTransactionEntity.transactionEntity;

/**
 * Transaction query repository.
 *
 * @author javtronic
 */
@Lazy
@Repository
public class TransactionQueryRepository implements ITransactionQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * Constructor
     *
     * @param em Entity manager
     */
    public TransactionQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TransactionDetailVO> findTransactionsByAccountAndRange(String accountCode,
                                                                       Date startDate, Date endDate) {
        return queryFactory.select(
                        Projections.bean(TransactionDetailVO.class,
                                transactionEntity.transactionCode,
                                transactionEntity.transactionDate,
                                transactionEntity.transactionType,
                                transactionEntity.amount,
                                transactionEntity.balance,
                                accountEntity.accountCode,
                                accountEntity.accountNumber,
                                accountEntity.accountType,
                                accountEntity.initialBalance,
                                accountEntity.accountStatus,
                                personEntity.name
                        ))
                .from(transactionEntity)
                .innerJoin(transactionEntity.account, accountEntity)
                .innerJoin(accountEntity.client, clientEntity)
                .innerJoin(clientEntity.person, personEntity)
                .where(accountEntity.accountCode.eq(accountCode),
                        transactionEntity.transactionDate.between(startDate, endDate))
                .orderBy(transactionEntity.transactionDate.asc())
                .fetch();
    }
}
