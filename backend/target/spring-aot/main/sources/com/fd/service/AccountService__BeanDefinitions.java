package com.fd.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AccountService}.
 */
@Generated
public class AccountService__BeanDefinitions {
  /**
   * Get the bean definition for 'accountService'.
   */
  public static BeanDefinition getAccountServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AccountService.class);
    InstanceSupplier<AccountService> instanceSupplier = InstanceSupplier.using(AccountService::new);
    instanceSupplier = instanceSupplier.andThen(AccountService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
