package com.rjm.sfk.bank_api.client.entity;

import com.rjm.sfk.bank_api.client.entity.audit.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Transaction Entity.
 *
 * @author javtronic.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SBATTRANSACTION")
public class TransactionEntity extends AuditEntity {

    @Serial
    private static final long serialVersionUID = -1234567890123456789L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "TRANSACTION_CODE", nullable = false)
    private String transactionCode;

    @Column(name = "ACCOUNT_CODE",  insertable = false, updatable = false)
    private String accountCode;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    private Date transactionDate;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private String transactionType;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_CODE", referencedColumnName = "ACCOUNT_CODE", nullable = false, updatable = false)
    private AccountEntity account;
}
