package com.fd.controller;

import com.fd.service.IAccountService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AccountController}.
 */
@Generated
public class AccountController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'accountController'.
   */
  private static BeanInstanceSupplier<AccountController> getAccountControllerInstanceSupplier() {
    return BeanInstanceSupplier.<AccountController>forConstructor(IAccountService.class)
            .withGenerator((registeredBean, args) -> new AccountController(args.get(0)));
  }

  /**
   * Get the bean definition for 'accountController'.
   */
  public static BeanDefinition getAccountControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AccountController.class);
    beanDefinition.setInstanceSupplier(getAccountControllerInstanceSupplier());
    return beanDefinition;
  }
}
