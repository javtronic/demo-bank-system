package com.rjm.sfk.bank_api.client.entity;

import com.rjm.sfk.bank_api.client.entity.audit.AuditEntity;
import com.rjm.sfk.bank_api.client.enums.GenderType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;

/**
 * Person Entity.
 *
 * @author javtronic.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SBATPERSON")
public class PersonEntity extends AuditEntity {

    @Serial
    private static final long serialVersionUID = -5431935616642689940L;

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "PERSON_CODE", nullable = false)
    private String personCode;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = false)
    private GenderType gender;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @Column(name = "IDENTIFICATION", nullable = false, unique = true)
    private String identification;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "ADDRESS", length = 100)
    private String address;
}
