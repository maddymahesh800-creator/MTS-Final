package com.fd;

import java.lang.Override;
import org.springframework.aot.generate.Generated;
import org.springframework.boot.EnvironmentPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Configure the environment with AOT optimizations.
 */
@Generated
public class Application__EnvironmentPostProcessor implements EnvironmentPostProcessor {
  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment,
      SpringApplication application) {
    environment.addActiveProfile("prod");
  }
}
