package com.fd.repository;

import com.fd.model.TransactionLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link TransactionLogRepository}.
 */
@Generated
public class TransactionLogRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public TransactionLogRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link TransactionLogRepository#findByFromAccountIdOrToAccountIdOrderByCreatedOnDesc(java.lang.String,java.lang.String)}.
   */
  public List<TransactionLog> findByFromAccountIdOrToAccountIdOrderByCreatedOnDesc(
      String fromAccountId, String toAccountId) {
    String queryString = "SELECT t FROM TransactionLog t WHERE t.fromAccountId = :fromAccountId OR t.toAccountId = :toAccountId ORDER BY t.createdOn desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("fromAccountId", fromAccountId);
    query.setParameter("toAccountId", toAccountId);

    return (List<TransactionLog>) query.getResultList();
  }
}
