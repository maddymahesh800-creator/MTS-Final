package com.fd.repository;

import com.fd.model.Rewards;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link RewardRepository}.
 */
@Generated
public class RewardRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public RewardRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link RewardRepository#existsByTransactionId(java.lang.String)}.
   */
  public boolean existsByTransactionId(String transactionId) {
    String queryString = "SELECT r.id id FROM Rewards r WHERE r.transactionId = :transactionId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("transactionId", transactionId);
    query.setMaxResults(1);

    return !query.getResultList().isEmpty();
  }

  /**
   * AOT generated implementation of {@link RewardRepository#findByAccountId(java.lang.String)}.
   */
  public List<Rewards> findByAccountId(String accountId) {
    String queryString = "SELECT r FROM Rewards r WHERE r.accountId = :accountId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("accountId", accountId);

    return (List<Rewards>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RewardRepository#findByAccountIdAndIsActiveTrue(java.lang.String)}.
   */
  public List<Rewards> findByAccountIdAndIsActiveTrue(String accountId) {
    String queryString = "SELECT r FROM Rewards r WHERE r.accountId = :accountId AND r.isActive = TRUE";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("accountId", accountId);

    return (List<Rewards>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RewardRepository#findByAccountIdAndIsActiveTrueOrderByRewardCreatedAtDesc(java.lang.String)}.
   */
  public List<Rewards> findByAccountIdAndIsActiveTrueOrderByRewardCreatedAtDesc(String accountId) {
    String queryString = "SELECT r FROM Rewards r WHERE r.accountId = :accountId AND r.isActive = TRUE ORDER BY r.rewardCreatedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("accountId", accountId);

    return (List<Rewards>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RewardRepository#findByTransactionId(java.lang.String)}.
   */
  public Optional<Rewards> findByTransactionId(String transactionId) {
    String queryString = "SELECT r FROM Rewards r WHERE r.transactionId = :transactionId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("transactionId", transactionId);

    return Optional.ofNullable((Rewards) convertOne(query.getSingleResultOrNull(), false, Rewards.class));
  }

  /**
   * AOT generated implementation of {@link RewardRepository#getTotalRewardsForUser(java.lang.String)}.
   */
  public Long getTotalRewardsForUser(String accountId) {
    String queryString = "SELECT COALESCE(SUM(r.points), 0) FROM Rewards r WHERE r.accountId = :accountId AND r.isActive = true";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("accountId", accountId);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }
}
