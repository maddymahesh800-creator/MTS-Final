package com.fd.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RewardService}.
 */
@Generated
public class RewardService__BeanDefinitions {
  /**
   * Get the bean definition for 'rewardService'.
   */
  public static BeanDefinition getRewardServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RewardService.class);
    InstanceSupplier<RewardService> instanceSupplier = InstanceSupplier.using(RewardService::new);
    instanceSupplier = instanceSupplier.andThen(RewardService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
