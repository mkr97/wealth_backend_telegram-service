package com.wealth.telegram.model.entity;

import com.wealth.telegram.model.enums.Status;
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
@Table(name = "user_actions")
public class UserAction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "chat_id", nullable = false)
    private long chatId;

    @Column(name = "is_bot", nullable = false)
    private boolean isBot;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "from_date_at", nullable = false)
    private Date fromDateAt;

    @Column(name = "to_date_at", nullable = false)
    private Date toDateAt;

    @Column(name = "retry_count")
    private int retryCount;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    @Column(name = "system_remark", length = 2500)
    private String systemRemark;

    @Column(length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "created_by", length = 32, nullable = false)
    private String  createdBy;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "modified_by", length = 32)
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "f_actions_id", nullable = false)
    private Action action;

}
