package com.rjm.sfk.bank_api.core.repository.query;

import com.rjm.sfk.bank_api.vo.AccountVO;

import java.util.List;

public interface IAccountQueryRepository {

    /**
     * Finds all accounts.
     *
     * @return a list of account VO objects
     */
    List<AccountVO> findAllAccounts();
}
