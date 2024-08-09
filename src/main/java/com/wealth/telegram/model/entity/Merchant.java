package com.wealth.telegram.model.entity;

import com.wealth.telegram.model.enums.Status;
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
@Table(name = "merchants")
public class Merchant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "merchant_id", nullable = false)
    private long merchantId;

    @Column(name = "chat_id", nullable = false)
    private long chatId;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "phone_number", length = 12, nullable = false)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "biz_name")
    private String bizName;

    @Column(name = "biz_address", length = 1000)
    private String bizAddress;

    @Column(name = "is_activated", nullable = false)
    private boolean isActivated;

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

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cashier> cashiers;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

}
