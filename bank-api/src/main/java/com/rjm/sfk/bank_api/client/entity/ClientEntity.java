package com.rjm.sfk.bank_api.client.entity;

import com.rjm.sfk.bank_api.client.entity.audit.AuditEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;

/**
 * Client Entity.
 *
 * @author javtronic.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SBATCLIENT")
public class ClientEntity extends AuditEntity {

    @Serial
    private static final long serialVersionUID = -7373534129971049719L;

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "CLIENT_CODE", nullable = false)
    private String clientCode;


    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "CLIENT_STATUS", nullable = false)
    private Boolean clientStatus;

    /**
     * FK Person
     */
    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_CODE", nullable = false)
    private PersonEntity person;
}
