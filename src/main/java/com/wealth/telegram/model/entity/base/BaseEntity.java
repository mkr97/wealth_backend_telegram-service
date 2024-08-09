package com.wealth.telegram.model.entity.base;

import com.wealth.telegram.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseEntity implements Serializable {

    @Column(length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "created_by", length = 32, nullable = false)
    private String createdBy;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "modified_by", length = 32)
    private String modifiedBy;

}
