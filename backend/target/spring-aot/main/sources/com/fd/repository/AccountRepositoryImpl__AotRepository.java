package com.fd.repository;

import com.fd.model.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import java.util.function.LongSupplier;
import org.springframework.aot.generate.Generated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.DeclaredQuery;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * AOT generated JPA repository implementation for {@link AccountRepository}.
 */
@Generated
public class AccountRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public AccountRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link AccountRepository#countAccounts()}.
   */
  public Long countAccounts() {
    String queryString = "SELECT COUNT(*) FROM Account a";
    Query query = this.entityManager.createQuery(queryString);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }

  /**
   * AOT generated implementation of {@link AccountRepository#findByAccountId(java.lang.String)}.
   */
  public Optional<Account> findByAccountId(String accountId) {
    String queryString = "SELECT a FROM Account a WHERE a.accountId = :accountId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("accountId", accountId);

    return Optional.ofNullable((Account) convertOne(query.getSingleResultOrNull(), false, Account.class));
  }

  /**
   * AOT generated implementation of {@link AccountRepository#findByHolderName(java.lang.String,org.springframework.data.domain.Pageable)}.
   */
  public Page<Account> findByHolderName(String holderName, Pageable pageable) {
    String queryString = "SELECT a FROM Account a WHERE a.holderName = :holderName";
    String countQueryString = "SELECT COUNT(a) FROM Account a WHERE a.holderName = :holderName";
    Pageable pageable_1 = pageable != null ? pageable : Pageable.unpaged();
    if (pageable_1.getSort().isSorted()) {
      DeclaredQuery declaredQuery = DeclaredQuery.jpqlQuery(queryString);
      queryString = rewriteQuery(declaredQuery, pageable_1.getSort(), Account.class);
    }
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("holderName", holderName);
    if (pageable_1.isPaged()) {
      query.setFirstResult(Long.valueOf(pageable_1.getOffset()).intValue());
      query.setMaxResults(pageable_1.getPageSize());
    }
    LongSupplier countAll = () -> {
      Query countQuery = this.entityManager.createQuery(countQueryString);
      countQuery.setParameter("holderName", holderName);
      return getCount(countQuery);
    };

    return PageableExecutionUtils.getPage((List<Account>) query.getResultList(), pageable_1, countAll);
  }
}
