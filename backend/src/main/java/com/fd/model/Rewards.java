package com.fd.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="reward_table")
public class Rewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="account_id", nullable=false)
    private String accountId;

    @Column(name="points", nullable=false)
    private long points;

    @Column(name="transaction_id", nullable=false)
    private String transactionId;

    @Column(name="transaction_amount", nullable=false)
    private Long transactionAmount;

    @Column(name="reward_created_at", nullable=false)
    private LocalDateTime rewardCreatedAt;

    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable=false)
    private boolean isActive;

    public Rewards() {
        super();
    }

    // Constructor for creating rewards with essential fields
    public Rewards(String accountId, long points, String transactionId, Long transactionAmount) {
        this.accountId = accountId;
        this.points = points;
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.rewardCreatedAt = LocalDateTime.now();
        this.isActive = true;
    }

    public Rewards(long id, String accountId, long points, String transactionId, Long transactionAmount,
            LocalDateTime rewardCreatedAt, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isActive) {
        this.id = id;
        this.accountId = accountId;
        this.points = points;
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.rewardCreatedAt = rewardCreatedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (rewardCreatedAt == null) {
            rewardCreatedAt = LocalDateTime.now();
        }
        isActive = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDateTime getRewardCreatedAt() {
        return rewardCreatedAt;
    }

    public void setRewardCreatedAt(LocalDateTime rewardCreatedAt) {
        this.rewardCreatedAt = rewardCreatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}