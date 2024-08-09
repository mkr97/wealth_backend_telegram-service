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
@Table(name = "actions")
public class Action implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 32, nullable = false)
    private String command;

    @Column(name = "message", length = 5000, nullable = false)
    private String message;

    @Column(name = "message_kh", length = 5000)
    private String messageKh;

    @Column(name = "parent_id")
    private Long parentId;

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
