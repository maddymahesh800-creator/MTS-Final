package com.fd.controller;

import com.fd.service.IProductService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ProductController}.
 */
@Generated
public class ProductController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'productController'.
   */
  private static BeanInstanceSupplier<ProductController> getProductControllerInstanceSupplier() {
    return BeanInstanceSupplier.<ProductController>forConstructor(IProductService.class)
            .withGenerator((registeredBean, args) -> new ProductController(args.get(0)));
  }

  /**
   * Get the bean definition for 'productController'.
   */
  public static BeanDefinition getProductControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProductController.class);
    beanDefinition.setInstanceSupplier(getProductControllerInstanceSupplier());
    return beanDefinition;
  }
}
