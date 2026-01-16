package com.rjm.sfk.bank_api.core.repository.query;

import com.rjm.sfk.bank_api.vo.TransactionDetailVO;

import java.util.Date;
import java.util.List;

/**
 * Interface Transaction query interface.
 */
public interface ITransactionQueryRepository {

    /**
     * Finds a list of transactions for the given account code and date range.
     *
     * @param accountCode the account code
     * @param startDate   the start date of the range
     * @param endDate     the end date of the range
     * @return a list of transaction VO objects
     */
    List<TransactionDetailVO> findTransactionsByAccountAndRange(String accountCode,
                                                                Date startDate, Date endDate);

}
