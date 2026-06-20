package com.fd.dto;

import java.time.LocalDateTime;

public class RewardsDTO {

    private long id;
    private String accountId;
    private long points;
    private String transactionId;
    private Long transactionAmount;
    private LocalDateTime rewardCreatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;

    public RewardsDTO() {
        super();
    }

    public RewardsDTO(long id, String accountId, long points, String transactionId, Long transactionAmount,
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

    // Conversion from entity to DTO
    public static RewardsDTO toDTO(com.fd.model.Rewards reward) {
        if (reward == null) {
            return null;
        }
        return new RewardsDTO(
            reward.getId(),
            reward.getAccountId(),
            reward.getPoints(),
            reward.getTransactionId(),
            reward.getTransactionAmount(),
            reward.getRewardCreatedAt(),
            reward.getCreatedAt(),
            reward.getUpdatedAt(),
            reward.isActive()
        );
    }

    // Conversion from DTO to entity
    public static com.fd.model.Rewards fromDTO(RewardsDTO dto) {
        if (dto == null) {
            return null;
        }
        com.fd.model.Rewards reward = new com.fd.model.Rewards(
            dto.getAccountId(),
            dto.getPoints(),
            dto.getTransactionId(),
            dto.getTransactionAmount()
        );
        reward.setId(dto.getId());
        reward.setRewardCreatedAt(dto.getRewardCreatedAt());
        reward.setCreatedAt(dto.getCreatedAt());
        reward.setUpdatedAt(dto.getUpdatedAt());
        reward.setIsActive(dto.isActive());
        return reward;
    }
}