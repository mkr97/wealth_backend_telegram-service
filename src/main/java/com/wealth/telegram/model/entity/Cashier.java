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
@Table(name = "cashiers")
public class Cashier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cashier_id", nullable = false)
    private long cashierId;

    @Column(name = "chat_id", nullable = false)
    private long chatId;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "phone_number", length = 12, nullable = false)
    private String phoneNumber;

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

    @ManyToOne
    @JoinColumn(name = "f_merchants_id", nullable = false)
    private Merchant merchant;

    @OneToMany(mappedBy = "cashier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

}
