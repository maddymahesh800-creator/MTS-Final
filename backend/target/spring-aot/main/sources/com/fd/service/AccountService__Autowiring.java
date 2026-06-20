package com.fd.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link AccountService}.
 */
@Generated
public class AccountService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static AccountService apply(RegisteredBean registeredBean, AccountService instance) {
    instance.accountRepo = AutowiredFieldValueResolver.forRequiredField("accountRepo").resolve(registeredBean);
    instance.transactionLogRepo = AutowiredFieldValueResolver.forRequiredField("transactionLogRepo").resolve(registeredBean);
    instance.rewardService = AutowiredFieldValueResolver.forRequiredField("rewardService").resolve(registeredBean);
    return instance;
  }
}
