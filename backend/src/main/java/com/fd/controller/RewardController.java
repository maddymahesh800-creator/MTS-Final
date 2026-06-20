package com.fd.controller;

import com.fd.dto.ApiResponseDTO;
import com.fd.dto.RewardsDTO;
import com.fd.exception.ResourceNotFoundException;
import com.fd.service.IRewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a-api")
@CrossOrigin(origins = "http://localhost:4200")
public class RewardController {

    private final IRewardService rewardService;

    public RewardController(IRewardService rewardService) {
        this.rewardService = rewardService;
    }

    /**
     * Get total rewards for a user account
     * 
     * @param accountId The account ID
     * @return Total active reward points
     */
    @GetMapping("/secure/rewards/{accountId}/total")
    public ResponseEntity<ApiResponseDTO<Long>> getTotalRewards(@PathVariable String accountId) {
        Long totalRewards = rewardService.getTotalRewardsForUser(accountId);
        return ResponseEntity.ok(new ApiResponseDTO<>(
            totalRewards, 
            "Total rewards fetched successfully for account: " + accountId
        ));
    }

    /**
     * Get all active rewards for a user account
     * 
     * @param accountId The account ID
     * @return List of active rewards sorted by creation date (newest first)
     */
    @GetMapping("/secure/rewards/{accountId}/active")
    public ResponseEntity<ApiResponseDTO<List<RewardsDTO>>> getActiveRewards(@PathVariable String accountId) {
        List<RewardsDTO> activeRewards = rewardService.getActiveRewardsForUser(accountId);
        return ResponseEntity.ok(new ApiResponseDTO<>(
            activeRewards,
            "Active rewards fetched successfully for account: " + accountId
        ));
    }

    /**
     * Get all rewards (active and inactive) for a user account
     * 
     * @param accountId The account ID
     * @return List of all rewards
     */
    @GetMapping("/secure/rewards/{accountId}/all")
    public ResponseEntity<ApiResponseDTO<List<RewardsDTO>>> getAllRewards(@PathVariable String accountId) {
        List<RewardsDTO> allRewards = rewardService.getAllRewardsForUser(accountId);
        return ResponseEntity.ok(new ApiResponseDTO<>(
            allRewards,
            "All rewards fetched successfully for account: " + accountId
        ));
    }

    /**
     * Get reward details for a specific transaction
     * 
     * @param transactionId The transaction ID
     * @return Reward details if found
     * @throws ResourceNotFoundException if reward not found
     */
    @GetMapping("/secure/rewards/transaction/{transactionId}")
    public ResponseEntity<ApiResponseDTO<RewardsDTO>> getRewardByTransactionId(
            @PathVariable String transactionId) throws ResourceNotFoundException {
        RewardsDTO reward = rewardService.getRewardByTransactionId(transactionId);
        return ResponseEntity.ok(new ApiResponseDTO<>(
            reward,
            "Reward fetched successfully for transaction: " + transactionId
        ));
    }

    /**
     * Deactivate a reward (mark as inactive)
     * 
     * @param rewardId The reward ID
     * @return Updated reward details
     * @throws ResourceNotFoundException if reward not found
     */
    @PutMapping("/secure/rewards/{rewardId}/deactivate")
    public ResponseEntity<ApiResponseDTO<RewardsDTO>> deactivateReward(
            @PathVariable Long rewardId) throws ResourceNotFoundException {
        RewardsDTO updatedReward = rewardService.deactivateReward(rewardId);
        return ResponseEntity.ok(new ApiResponseDTO<>(
            updatedReward,
            "Reward deactivated successfully"
        ));
    }

    /**
     * Check if a reward exists for a transaction
     * 
     * @param transactionId The transaction ID
     * @return true if reward exists, false otherwise
     */
    @GetMapping("/secure/rewards/transaction/{transactionId}/exists")
    public ResponseEntity<ApiResponseDTO<Boolean>> checkRewardExists(
            @PathVariable String transactionId) {
        boolean exists = rewardService.rewardExistsForTransaction(transactionId);
        return ResponseEntity.ok(new ApiResponseDTO<>(
            exists,
            "Reward existence check completed for transaction: " + transactionId
        ));
    }
}
