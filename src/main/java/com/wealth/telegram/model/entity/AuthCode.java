package com.wealth.telegram.model.entity;

import com.wealth.telegram.model.enums.Status;
import com.wealth.telegram.model.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "auth_codes")
public class AuthCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", length = 32, nullable = false)
    private String userId;

    @Column(name = "user_type", length = 32, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "code_type", length = 32, nullable = false)
    private String codeType;

    @Column(name = "code_value", length = 5000, nullable = false)
    private String codeValue;

    @Column(name = "code_signature", length = 5000, nullable = false)
    private String codeSignature;

    @Column(name = "expiry_at", nullable = false)
    private Date expiryAt;

    @Column(name = "is_user", nullable = false)
    private boolean isUser;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "parent_id")
    private Long parentId;

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
