package com.fd.repository;

import com.fd.model.Product;
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
 * AOT generated JPA repository implementation for {@link ProductRepository}.
 */
@Generated
public class ProductRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ProductRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ProductRepository#countByProductName(java.lang.String)}.
   */
  public Long countByProductName(String pname) {
    String queryString = "SELECT COUNT(p) FROM Product p WHERE p.pname = :pname";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("pname", pname);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }

  /**
   * AOT generated implementation of {@link ProductRepository#findByPname(java.lang.String)}.
   */
  public Optional<Product> findByPname(String pname) {
    String queryString = "SELECT p FROM Product p WHERE p.pname = :pname";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("pname", pname);

    return Optional.ofNullable((Product) convertOne(query.getSingleResultOrNull(), false, Product.class));
  }

  /**
   * AOT generated implementation of {@link ProductRepository#findByPname(java.lang.String,org.springframework.data.domain.Pageable)}.
   */
  public Page<Product> findByPname(String pname, Pageable pageable) {
    String queryString = "SELECT p FROM Product p WHERE p.pname = :pname";
    String countQueryString = "SELECT COUNT(p) FROM Product p WHERE p.pname = :pname";
    Pageable pageable_1 = pageable != null ? pageable : Pageable.unpaged();
    if (pageable_1.getSort().isSorted()) {
      DeclaredQuery declaredQuery = DeclaredQuery.jpqlQuery(queryString);
      queryString = rewriteQuery(declaredQuery, pageable_1.getSort(), Product.class);
    }
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("pname", pname);
    if (pageable_1.isPaged()) {
      query.setFirstResult(Long.valueOf(pageable_1.getOffset()).intValue());
      query.setMaxResults(pageable_1.getPageSize());
    }
    LongSupplier countAll = () -> {
      Query countQuery = this.entityManager.createQuery(countQueryString);
      countQuery.setParameter("pname", pname);
      return getCount(countQuery);
    };

    return PageableExecutionUtils.getPage((List<Product>) query.getResultList(), pageable_1, countAll);
  }
}
