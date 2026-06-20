package com.fd.repository;

import java.util.List;
import java.util.Optional;

import com.fd.model.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Rewards, Long> {

    List<Rewards> findByAccountId(String accountId);

    List<Rewards> findByAccountIdAndIsActiveTrue(String accountId);

    Optional<Rewards> findByTransactionId(String transactionId);

    boolean existsByTransactionId(String transactionId);

    @Query("SELECT COALESCE(SUM(r.points), 0) FROM Rewards r WHERE r.accountId = :accountId AND r.isActive = true")
    Long getTotalRewardsForUser(@Param("accountId") String accountId);

    List<Rewards> findByAccountIdAndIsActiveTrueOrderByRewardCreatedAtDesc(String accountId);

} 

