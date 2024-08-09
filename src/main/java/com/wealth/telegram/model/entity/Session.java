package com.wealth.telegram.model.entity;

import com.wealth.telegram.model.entity.base.BaseEntity;
import com.wealth.telegram.model.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "sessions")
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "expiry_at", nullable = false)
    private Date expiryAt;

    @Column(name = "system_remark", length = 2500)
    private String systemRemark;

    @Column(nullable = false)
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
