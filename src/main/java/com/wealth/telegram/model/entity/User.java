package com.wealth.telegram.model.entity;

import com.wealth.telegram.model.enums.Status;
import com.wealth.telegram.model.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_type", length = 32, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(length = 32, nullable = false)
    private String username;

    @Column(name = "secret_key", length = 32, nullable = false)
    private String secretKey;

    @Column(name = "password", length = 2500, nullable = false)
    private String password;

    @Column(name = "mfa_token", length = 2500)
    private String mfaToken;

    @Column(name = "expiry_at", nullable = false)
    private Date expiryAt;

    @Column(name = "is_required_change_pwd", nullable = false)
    private boolean isRequiredChangePwd;

    @Column(name = "last_login_at")
    private Date lastLoginAt;

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

    @ManyToOne
    @JoinColumn(name = "f_merchants_id")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "f_cashiers_id")
    private Cashier cashier;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserDevice> userDevices;

}
