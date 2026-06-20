package com.fd.service;

import com.fd.dto.RewardsDTO;
import com.fd.exception.ResourceNotFoundException;
import com.fd.model.Rewards;
import com.fd.repository.RewardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardService implements IRewardService {
    private static final Logger logger = LoggerFactory.getLogger(RewardService.class);

    // Reward eligibility rules
    private static final double MINIMUM_TRANSACTION_AMOUNT = 100.0;
    private static final double POINTS_PER_HUNDRED = 1.0;

    @Autowired
    private RewardRepository rewardRepository;

    @Override
    public RewardsDTO awardRewardsForTransaction(String transactionId, String fromAccountId,
                                                 String toAccountId, double amount, Boolean status) {
        logger.info("Processing reward for transaction: {} from: {} to: {} amount: {}", 
                   transactionId, fromAccountId, toAccountId, amount);

        // Check if reward already exists for this transaction
        if (rewardExistsForTransaction(transactionId)) {
            logger.warn("Reward already exists for transaction: {}", transactionId);
            return null;
        }

        // Check eligibility
        if (!isEligibleForReward(fromAccountId, toAccountId, amount, status)) {
            logger.info("Transaction {} is not eligible for rewards", transactionId);
            return null;
        }

        // Calculate reward points
        long rewardPoints = calculateRewardPoints(amount);

        if (rewardPoints == 0) {
            logger.info("Calculated reward points is 0 for transaction amount: {}", amount);
            return null;
        }

        // Create and save reward
        Rewards reward = new Rewards(fromAccountId, rewardPoints, transactionId, (long) amount);
        Rewards savedReward = rewardRepository.save(reward);

        logger.info("Reward created for transaction: {} with points: {}", transactionId, rewardPoints);
        return RewardsDTO.toDTO(savedReward);
    }

    @Override
    public Long getTotalRewardsForUser(String accountId) {
        logger.info("Fetching total rewards for user: {}", accountId);
        return rewardRepository.getTotalRewardsForUser(accountId);
    }

    @Override
    public List<RewardsDTO> getActiveRewardsForUser(String accountId) {
        logger.info("Fetching active rewards for user: {}", accountId);
        return rewardRepository.findByAccountIdAndIsActiveTrueOrderByRewardCreatedAtDesc(accountId)
                .stream()
                .map(RewardsDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RewardsDTO> getAllRewardsForUser(String accountId) {
        logger.info("Fetching all rewards for user: {}", accountId);
        return rewardRepository.findByAccountId(accountId)
                .stream()
                .map(RewardsDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RewardsDTO getRewardByTransactionId(String transactionId) throws ResourceNotFoundException {
        logger.info("Fetching reward for transaction: {}", transactionId);
        Rewards reward = rewardRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reward not found for transaction: " + transactionId));
        return RewardsDTO.toDTO(reward);
    }

    @Override
    public RewardsDTO deactivateReward(Long rewardId) throws ResourceNotFoundException {
        logger.info("Deactivating reward: {}", rewardId);
        Rewards reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new ResourceNotFoundException("Reward not found with id: " + rewardId));

        reward.setIsActive(false);
        Rewards updatedReward = rewardRepository.save(reward);
        logger.info("Reward deactivated: {}", rewardId);
        return RewardsDTO.toDTO(updatedReward);
    }

    @Override
    public boolean rewardExistsForTransaction(String transactionId) {
        return rewardRepository.existsByTransactionId(transactionId);
    }

    /**
     * Checks if a transaction is eligible for rewards
     * 
     * Eligibility Rules:
     * 1. Transaction status must be SUCCESS (true)
     * 2. Transaction amount must be greater than 100
     * 3. Sender and receiver must be different users
     * 4. Transaction must not be self-transfer
     * 
     * @param fromAccountId Sender account ID
     * @param toAccountId Receiver account ID
     * @param amount Transaction amount
     * @param status Transaction status (true = SUCCESS)
     * @return true if transaction is eligible, false otherwise
     */
    private boolean isEligibleForReward(String fromAccountId, String toAccountId, double amount, Boolean status) {
        logger.debug("Checking reward eligibility for transaction");

        // Rule 1: Transaction status must be SUCCESS
        if (status == null || !status) {
            logger.debug("Transaction status is not SUCCESS");
            return false;
        }

        // Rule 2: Transaction amount must be greater than 100
        if (amount <= MINIMUM_TRANSACTION_AMOUNT) {
            logger.debug("Transaction amount {} is not greater than {}", amount, MINIMUM_TRANSACTION_AMOUNT);
            return false;
        }

        // Rule 3 & 4: Sender and receiver must be different (not self-transfer)
        if (fromAccountId == null || toAccountId == null || fromAccountId.equals(toAccountId)) {
            logger.debug("Transaction is a self-transfer or has null account IDs");
            return false;
        }

        logger.debug("Transaction is eligible for rewards");
        return true;
    }

    /**
     * Calculates reward points based on transaction amount
     * 
     * Formula: 1 reward point per 100 transferred, rounded down
     * Examples:
     * - 250 → 2 points
     * - 199 → 1 point
     * - 99 → 0 points (but won't be called if amount <= 100)
     * 
     * @param amount The transaction amount
     * @return The calculated reward points (rounded down)
     */
    private long calculateRewardPoints(double amount) {
        long points = (long) (amount / 100.0);
        logger.debug("Calculated reward points: {} for amount: {}", points, amount);
        return points;
    }
}
