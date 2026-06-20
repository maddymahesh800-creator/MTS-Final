package com.fd.service;

import com.fd.dto.RewardsDTO;
import com.fd.exception.ResourceNotFoundException;
import java.util.List;

public interface IRewardService {

    /**
     * Calculate and award rewards for a successful transaction if eligible
     * 
     * @param transactionId The transaction ID
     * @param fromAccountId The sender's account ID
     * @param toAccountId The receiver's account ID
     * @param amount The transaction amount
     * @param status The transaction status (true = SUCCESS, false = FAILED)
     * @return RewardsDTO if reward was created, null otherwise
     */
    RewardsDTO awardRewardsForTransaction(String transactionId, String fromAccountId, 
                                         String toAccountId, double amount, Boolean status);

    /**
     * Get total rewards points for a user
     * 
     * @param accountId The account ID
     * @return Total active reward points
     */
    Long getTotalRewardsForUser(String accountId);

    /**
     * Get all active rewards for a user
     * 
     * @param accountId The account ID
     * @return List of active rewards sorted by creation date
     */
    List<RewardsDTO> getActiveRewardsForUser(String accountId);

    /**
     * Get all rewards for a user (active and inactive)
     * 
     * @param accountId The account ID
     * @return List of all rewards
     */
    List<RewardsDTO> getAllRewardsForUser(String accountId);

    /**
     * Get reward by transaction ID
     * 
     * @param transactionId The transaction ID
     * @return RewardsDTO if found
     * @throws ResourceNotFoundException if reward not found
     */
    RewardsDTO getRewardByTransactionId(String transactionId) throws ResourceNotFoundException;

    /**
     * Deactivate a reward
     * 
     * @param rewardId The reward ID
     * @return Updated RewardsDTO
     * @throws ResourceNotFoundException if reward not found
     */
    RewardsDTO deactivateReward(Long rewardId) throws ResourceNotFoundException;

    /**
     * Check if a reward already exists for a transaction
     * 
     * @param transactionId The transaction ID
     * @return true if reward exists, false otherwise
     */
    boolean rewardExistsForTransaction(String transactionId);
}
