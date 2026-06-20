package com.fd.security;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Bean definitions for {@link JwtAuthFilter}.
 */
@Generated
public class JwtAuthFilter__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'jwtAuthFilter'.
   */
  private static BeanInstanceSupplier<JwtAuthFilter> getJwtAuthFilterInstanceSupplier() {
    return BeanInstanceSupplier.<JwtAuthFilter>forConstructor(JwtUtil.class, UserDetailsService.class)
            .withGenerator((registeredBean, args) -> new JwtAuthFilter(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'jwtAuthFilter'.
   */
  public static BeanDefinition getJwtAuthFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JwtAuthFilter.class);
    beanDefinition.setInstanceSupplier(getJwtAuthFilterInstanceSupplier());
    return beanDefinition;
  }
}
