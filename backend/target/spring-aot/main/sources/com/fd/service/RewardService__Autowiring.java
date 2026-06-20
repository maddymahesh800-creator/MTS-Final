package com.fd.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link RewardService}.
 */
@Generated
public class RewardService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static RewardService apply(RegisteredBean registeredBean, RewardService instance) {
    AutowiredFieldValueResolver.forRequiredField("rewardRepository").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
