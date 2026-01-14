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

/**
 * Account Entity.
 *
 * @author javtronic.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SBATACCOUNT")
public class AccountEntity extends AuditEntity {

    @Serial
    private static final long serialVersionUID = -4017839746579335088L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "ACCOUNT_CODE", nullable = false)
    private String accountCode;

    @Column(name = "CLIENT_CODE",  insertable = false, updatable = false)
    private String clientCode;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private String accountType;

    @Column(name = "INITIAL_BALANCE", nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "ACCOUNT_STATUS", nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_CODE", referencedColumnName = "CLIENT_CODE", nullable = false, updatable = false)
    private ClientEntity client;
}
