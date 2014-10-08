package io.pivotal.pcf.demo.services;

import io.pivotal.pcf.demo.redis.GenericRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sgupta on 10/1/14.
 */
@Configuration
@ComponentScan(basePackageClasses = {GenericRepository.class})
public class RepositoryConfig {
}
