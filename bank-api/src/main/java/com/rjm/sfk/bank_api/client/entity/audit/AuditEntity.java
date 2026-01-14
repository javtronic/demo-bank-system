package com.rjm.sfk.bank_api.client.entity.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Audit Entity.
 *
 * @author javtronic.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AuditEntity implements Serializable {

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY_USER")
    private String createdByUser;

    @Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;

    @Column(name = "LAST_MODIFIED_BY_USER")
    private String lastModifiedByUser;

    @Column(name = "CREATED_FROM_IP")
    private String createdFromIp;

    @Column(name = "UPDATED_FROM_IP")
    private String updatedFromIp;
}
