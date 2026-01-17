package com.rjm.sfk.bank_api.core.service;

import com.rjm.sfk.bank_api.client.entity.AccountEntity;
import com.rjm.sfk.bank_api.client.enums.TransactionType;
import com.rjm.sfk.bank_api.client.utils.PDFUtils;
import com.rjm.sfk.bank_api.core.repository.IAccountRepository;
import com.rjm.sfk.bank_api.core.repository.query.IClientQueryRepository;
import com.rjm.sfk.bank_api.vo.ClientVO;
import com.rjm.sfk.bank_api.vo.TransactionDetailVO;
import com.rjm.sfk.bank_api.vo.report.AccountReportVO;
import com.rjm.sfk.bank_api.vo.report.ReportVO;
import com.rjm.sfk.bank_api.vo.report.TransactionReportVO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Client service.
 *
 * @author javtronic
 */
@Lazy
@Validated
@Service
public class ReportService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private IClientQueryRepository clientQueryRepository;

    /**
     * Generates a report for a given client code and date range.
     *
     * @param identification the Identification
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a report VO object with the client code, client name, and a list of account report VO objects
     * @throws EntityNotFoundException if the client code is not found
     */
    public ReportVO generateReport(String identification, Date startDate, Date endDate) {
        ClientVO client = clientQueryRepository.findClientByIdentification(identification);
        if(client == null) {
            throw new EntityNotFoundException("Cliente no Encontrado");
        }

        List<AccountEntity> accountsEntities = accountRepository.findByClient_ClientCode(client.getClientCode());

        List<AccountReportVO> accountReportVOS = new ArrayList<>();

        for (AccountEntity accountEntity : accountsEntities) {
            List<TransactionDetailVO> transactions =
                    transactionService.findTransactionsByAccountAndRange(accountEntity.getAccountCode(),
                            startDate, endDate);

            BigDecimal totalCredits = transactions.stream()
                    .filter(transactionDetailVO ->
                            transactionDetailVO.getTransactionType() == TransactionType.CREDITO)
                    .map(TransactionDetailVO::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal totalDebits = transactions.stream()
                    .filter(transactionDetailVO ->
                            transactionDetailVO.getTransactionType() == TransactionType.DEBITO)
                    .map(TransactionDetailVO::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            List<TransactionReportVO> trxReportVO = transactions.stream()
                    .map(transactionDetailVO -> new TransactionReportVO(
                            transactionDetailVO.getTransactionDate(),
                            transactionDetailVO.getTransactionType(),
                            transactionDetailVO.getAmount(),
                            transactionDetailVO.getBalance()
                    )).toList();

            accountReportVOS.add(new AccountReportVO(
                    accountEntity.getAccountNumber(),
                    accountEntity.getAccountType(),
                    accountEntity.getInitialBalance(),
                    accountEntity.getAccountStatus(),
                    trxReportVO,
                    totalCredits,
                    totalDebits
            ));
        }

        return  new ReportVO(
                client.getClientCode(),
                client.getPerson().getName(),
                accountReportVOS
        );
    }

    /**
     * Generates a PDF report for a given client code and date range.
     *
     * @param identification the identification
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a report VO object with the client code, client name, a list of account report VO objects, and the PDF report as a base64 string
     * @throws Exception if an error occurs generating the PDF
     */
    public String generatePDF(String identification, Date startDate, Date endDate) throws Exception {
        ReportVO report = generateReport(identification, startDate, endDate);
        return PDFUtils.generatePdfBase64(report);
    }
}
