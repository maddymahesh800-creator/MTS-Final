package com.fd.controller;

import com.fd.service.IRewardService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RewardController}.
 */
@Generated
public class RewardController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'rewardController'.
   */
  private static BeanInstanceSupplier<RewardController> getRewardControllerInstanceSupplier() {
    return BeanInstanceSupplier.<RewardController>forConstructor(IRewardService.class)
            .withGenerator((registeredBean, args) -> new RewardController(args.get(0)));
  }

  /**
   * Get the bean definition for 'rewardController'.
   */
  public static BeanDefinition getRewardControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RewardController.class);
    beanDefinition.setInstanceSupplier(getRewardControllerInstanceSupplier());
    return beanDefinition;
  }
}
